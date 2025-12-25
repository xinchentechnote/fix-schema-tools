package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class BaseMessage {

  @XmlElement(name = "field")
  private List<BaseField> baseFields;

  @XmlElement(name = "component")
  private List<CompositeField> componentFields;

  @XmlElement(name = "group")
  private List<CompositeField> groupFields;
}
