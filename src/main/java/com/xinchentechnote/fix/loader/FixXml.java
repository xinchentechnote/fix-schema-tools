package com.xinchentechnote.fix.loader;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "fix")
public class FixXml {

  @XmlAttribute private String major;
  @XmlAttribute private String minor;

  @XmlElement(name = "header")
  private BaseMessage header;

  @XmlElement(name = "trailer")
  private BaseMessage trailer;

  @XmlElement(name = "fields")
  private Fields fields;

  @XmlElement(name = "messages")
  private Messages messages;
}
