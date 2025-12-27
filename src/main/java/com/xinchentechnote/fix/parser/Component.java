package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class Component extends BaseMessage {
  @XmlAttribute private String name;
}
