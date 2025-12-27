package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class Field {
  @XmlAttribute private int number;
  @XmlAttribute private String name;
  @XmlAttribute private String type;
}
