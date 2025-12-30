package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
@XmlRootElement(name = "fix")
public class Fix {

  @XmlAttribute private String major;
  @XmlAttribute private String minor;

  @XmlElement(name = "header")
  private BaseMessage header;

  @XmlElement(name = "trailer")
  private BaseMessage trailer;

  @XmlElement(name = "fields")
  private Fields fields;

  @XmlElement(name = "messages")
  private Messages messages;

  @XmlElement(name = "components")
  private Components components;

  public void postProcess() {
    Map<String, FieldDef> fieldDefMap =
        fields.getFields().stream().collect(Collectors.toMap(FieldDef::getName, f -> f));
    postProcessMessage(header, fieldDefMap);
    postProcessMessage(trailer, fieldDefMap);
    for (Message msg : messages.getMessages()) {
      postProcessMessage(msg, fieldDefMap);
    }
  }

  private void postProcessMessage(BaseMessage msg, Map<String, FieldDef> fieldDefMap) {
    msg.getBaseFields()
        .forEach(
            f -> {
              FieldDef fieldDef = fieldDefMap.get(f.getName());
              if (null == fieldDef) {
                throw new RuntimeException("Field definition missing: " + f.getName());
              }

              f.setFieldDef(fieldDef);
            });
    msg.getGroupFields()
        .forEach(
            group -> {
              postProcessMessage(group, fieldDefMap);
            });
    msg.getComponentFields()
        .forEach(
            comp -> {
              postProcessMessage(comp, fieldDefMap);
            });
  }
}
