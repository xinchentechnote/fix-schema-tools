package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class BaseMessage {

  @XmlElement(name = "field")
  private List<MsgField> fields;
}
