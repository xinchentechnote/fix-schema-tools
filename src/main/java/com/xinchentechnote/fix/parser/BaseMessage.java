package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BaseMessage {

  @XmlElement(name = "field")
  private List<BaseField> baseFields = new ArrayList<>();

  @XmlElement(name = "component")
  private List<CompositeField> componentFields = new ArrayList<>();

  @XmlElement(name = "group")
  private List<CompositeField> groupFields = new ArrayList<>();
}
