package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class Components {
  @XmlElement(name = "component")
  List<Component> components;
}
