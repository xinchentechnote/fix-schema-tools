package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Messages {
  @XmlElement(name = "message")
  private List<Message> messages = new ArrayList<>();
}
