package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class MsgField {
  @XmlAttribute private String name;
  @XmlAttribute private String required;
}
