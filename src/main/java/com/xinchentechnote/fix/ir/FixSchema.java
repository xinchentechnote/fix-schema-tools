package com.xinchentechnote.fix.ir;

import java.util.Map;

public class FixSchema {
  private String name;
  private String version;
  private Map<String, FieldDef> fields;
  private Map<String, MessageDef> messages;
  private HeaderDef header;
  private TrailerDef trailer;

  public void setName(String name) {
    this.name = name;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setFields(Map<String, FieldDef> fields) {
    this.fields = fields;
  }

  public void setMessages(Map<String, MessageDef> messages) {
    this.messages = messages;
  }

  public void setHeader(HeaderDef header) {
    this.header = header;
  }

  public void setTrailer(TrailerDef tailer) {
    this.trailer = tailer;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public Map<String, FieldDef> getFields() {
    return fields;
  }

  public Map<String, MessageDef> getMessages() {
    return messages;
  }

  public HeaderDef getHeader() {
    return header;
  }

  public TrailerDef getTrailer() {
    return trailer;
  }

  @Override
  public String toString() {
    return "FixSchema [name="
        + name
        + ", version="
        + version
        + ", fields="
        + fields
        + ", messages="
        + messages
        + ", header="
        + header
        + ", trailer="
        + trailer
        + "]";
  }
}
