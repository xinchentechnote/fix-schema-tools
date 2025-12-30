package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Fields {
  @XmlElement(name = "field")
  private List<FieldDef> fields = new ArrayList<>();
}
