package com.xinchentechnote.fix.ir;

import java.util.List;

public class MessageDef {

  private String name;
  private String msgType;
  private MessageCategory category;

  private List<MessageField> bodyFields;
  private List<GroupDef> groups;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public MessageCategory getCategory() {
    return category;
  }

  public void setCategory(MessageCategory category) {
    this.category = category;
  }

  public List<MessageField> getBodyFields() {
    return bodyFields;
  }

  public void setBodyFields(List<MessageField> bodyFields) {
    this.bodyFields = bodyFields;
  }

  public List<GroupDef> getGroups() {
    return groups;
  }

  public void setGroups(List<GroupDef> groups) {
    this.groups = groups;
  }

  @Override
  public String toString() {
    return "MessageDef [name="
        + name
        + ", msgType="
        + msgType
        + ", category="
        + category
        + ", bodyFields="
        + bodyFields
        + ", groups="
        + groups
        + "]";
  }
}
