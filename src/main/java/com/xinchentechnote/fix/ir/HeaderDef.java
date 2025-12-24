package com.xinchentechnote.fix.ir;

import java.util.List;

public class HeaderDef {
  private List<MessageField> fields;

  public List<MessageField> getFields() {
    return fields;
  }

  public void setFields(List<MessageField> fields) {
    this.fields = fields;
  }

  @Override
  public String toString() {
    return "HeaderDef [fields=" + fields + "]";
  }
}
