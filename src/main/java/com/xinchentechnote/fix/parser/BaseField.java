package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class BaseField {
  @XmlAttribute private String name;
  @XmlAttribute private String required;
  private FieldDef fieldDef;

  public boolean isRequired() {
    return "Y".equals(required);
  }

  public Info getInfo() {
    Info info = new Info();
    info.setName(name);
    info.setRequired(isRequired());
    info.setType(fieldDef.getType());
    return info;
  }

  @Data
  public static class Info {
    private String name;
    private String type;
    private boolean required;
  }
}
