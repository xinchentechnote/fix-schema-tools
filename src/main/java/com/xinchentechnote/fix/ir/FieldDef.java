package com.xinchentechnote.fix.ir;

public class FieldDef {

  private int tag;
  private String name;
  private FixFieldType type;

  private String comment;

  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FixFieldType getType() {
    return type;
  }

  public void setType(FixFieldType type) {
    this.type = type;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "FieldDef [tag="
        + tag
        + ", name="
        + name
        + ", type="
        + type
        + ", comment="
        + comment
        + "]";
  }
}
