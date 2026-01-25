package com.xinchentechnote.fix.parser.v2;

import lombok.Data;

@Data
public final class ComponentEntry implements Entry {
  private String name;
  private boolean required;
  private ComponentDef def;

  public static ComponentEntry build(String name, boolean required) {
    ComponentEntry componentEntry = new ComponentEntry();
    componentEntry.setName(name);
    componentEntry.setRequired(required);
    return componentEntry;
  }
}
