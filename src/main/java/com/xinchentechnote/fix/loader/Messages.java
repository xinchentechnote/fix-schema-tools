package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class Messages {
  @XmlElement(name = "message")
  private List<Message> messages;
}
