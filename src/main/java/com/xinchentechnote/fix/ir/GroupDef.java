package com.xinchentechnote.fix.ir;

import java.util.List;

public class GroupDef {

  private int numInGroupTag;
  private List<MessageField> fields;
  private List<GroupDef> subGroups;

  public int getNumInGroupTag() {
    return numInGroupTag;
  }

  public void setNumInGroupTag(int numInGroupTag) {
    this.numInGroupTag = numInGroupTag;
  }

  public List<MessageField> getFields() {
    return fields;
  }

  public void setFields(List<MessageField> fields) {
    this.fields = fields;
  }

  public List<GroupDef> getSubGroups() {
    return subGroups;
  }

  public void setSubGroups(List<GroupDef> subGroups) {
    this.subGroups = subGroups;
  }

  @Override
  public String toString() {
    return "GroupDef [numInGroupTag="
        + numInGroupTag
        + ", fields="
        + fields
        + ", subGroups="
        + subGroups
        + "]";
  }
}
