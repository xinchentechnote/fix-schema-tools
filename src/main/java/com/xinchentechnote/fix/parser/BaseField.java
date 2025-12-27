package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class BaseField {
  @XmlAttribute private String name;
  @XmlAttribute private String required;
}
