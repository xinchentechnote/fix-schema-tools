package com.xinchentechnote.fix.parser.v2;

import lombok.Data;

@Data
public final class ComponentEntry implements Entry {
  private String name;
  private boolean required;
  private ComponentDef def;
}
