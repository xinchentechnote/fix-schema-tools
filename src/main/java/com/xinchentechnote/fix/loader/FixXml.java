package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "fix")
public class FixXml {

  @XmlElement(name = "fields")
  private Fields fields;

  @XmlElement(name = "messages")
  private Messages messages;
}
