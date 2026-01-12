package com.xinchentechnote.fix.parser.v2;

import com.xinchentechnote.fix.gen.MsgType;
import lombok.Data;

@Data
public final class ComponentEntry implements Entry {
  private String name;
  private boolean required;
  private ComponentDef def;

  public ComponentEntry.TemplateModel buildTemplateModel(MsgType type, String parentName) {
    return null;
  }

  public static class TemplateModel {}
}
