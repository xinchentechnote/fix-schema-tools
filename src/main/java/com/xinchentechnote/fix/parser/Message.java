package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Message extends BaseMessage {
  @XmlAttribute private String name;
  @XmlAttribute private String msgtype;
  @XmlAttribute private String msgcat;

  public Info getInfo() {
    return new Info(name);
  }

  @Data
  public static class Info {
    private String name;
    private String instanceName;

    public Info(String name) {
      this.name = name;
      this.instanceName = StringUtils.uncapitalize(name);
    }
  }
}
