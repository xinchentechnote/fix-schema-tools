package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class Field {
  @XmlAttribute private int number;
  @XmlAttribute private String name;
  @XmlAttribute private String type;
}
