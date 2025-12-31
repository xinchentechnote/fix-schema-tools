package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
public class FieldDef {
  @XmlAttribute private int number;
  @XmlAttribute private String name;
  @XmlAttribute private String type;

  public boolean isUtc() {
    return FixType.isUtc(this.type);
  }
}
