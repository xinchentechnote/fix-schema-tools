package com.xinchentechnote.fix.parser;

import com.xinchentechnote.fix.gen.MsgType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import org.apache.commons.text.CaseUtils;

@Data
public class CompositeField extends BaseMessage {
  @XmlAttribute private String name;
  @XmlAttribute private String required;

  public boolean isRequired() {
    return "Y".equals(required);
  }

  public Info getInfo(MsgType type, String parentName) {
    Info info = new Info(name);
    info.setParentName(parentName);
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
    private String parentName;
    private String parentUpperName;
    private String headerOrTrailer = "";

    public void setParentName(String parentName) {
      this.parentName = parentName;
      this.parentUpperName = CaseUtils.toCamelCase(parentName, true, '_');
    }

    public Info(String name) {
      this.name = name;
    }
  }
}
