package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "fix")
public class Fix {

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

  @XmlElement(name = "components")
  private Components components;
}
