package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class Fields {
  @XmlElement(name = "field")
  private List<Field> fields;
}
