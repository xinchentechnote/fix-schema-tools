package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class Message {
  @XmlAttribute private String name;
  @XmlAttribute private String msgtype;
  @XmlAttribute private String msgcat;
}
