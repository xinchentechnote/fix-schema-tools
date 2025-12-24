package com.xinchentechnote.fix.ir;

public class MessageField {
  private int tag;
  private boolean required;

  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  @Override
  public String toString() {
    return "MessageField [tag=" + tag + ", required=" + required + "]";
  }
}
