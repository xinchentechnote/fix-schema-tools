package com.xinchentechnote.fix.parser.v2;

import com.xinchentechnote.fix.gen.MsgType;
import lombok.Data;

@Data
public final class FieldEntry implements Entry {
  private String name;
  private boolean required;
  private FieldDef def;

  public TemplateModel buildTemplateModel(MsgType msgType, String parentName) {
    return null;
  }

  public static class TemplateModel {

    private String fixGetMethod;
    private String afterGetMethod;

    public void setFixGetMethod(String fixGetMethod) {
      this.fixGetMethod = fixGetMethod;
    }

    public String getFixGetMethod() {
      return fixGetMethod;
    }

    public void setAfterGetMethod(String afterGetMethod) {
      this.afterGetMethod = afterGetMethod;
    }

    public String getAfterGetMethod() {
      return afterGetMethod;
    }

    public void setAfterSetMethod(String afterSetMethod) {}
  }
}
