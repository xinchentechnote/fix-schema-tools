package com.xinchentechnote.fix.parser;

import com.xinchentechnote.fix.gen.MsgType;
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

  public Info getInfo(MsgType type, String parenName) {
    Info info = new Info();
    info.setParenName(parenName);
    info.setName(name);
    info.setRequired(isRequired());
    info.setType(fieldDef.getType());
    switch (type) {
      case TRAILER:
        info.setHeaderOrTrailer(".getTrailer()");
        break;
      case HEADER:
        info.setHeaderOrTrailer(".getHeader()");
        break;
    }
    return info;
  }

  @Data
  public static class Info {
    private String name;
    private String parenName;
    private String type;
    private boolean required;
    private String headerOrTrailer = "";
    private String fixGetMethod = "";
    private String afterGetMethod = "";
    private String afterSetMethod = "";
  }
}
