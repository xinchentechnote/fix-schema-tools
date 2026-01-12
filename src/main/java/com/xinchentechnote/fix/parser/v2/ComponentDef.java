package com.xinchentechnote.fix.parser.v2;

import java.util.List;
import lombok.Data;

@Data
public class ComponentDef implements StructuredDef {
  String name;
  List<Entry> entries;

  public static ComponentDef build(String name, List<Entry> entries) {
    ComponentDef componentDef = new ComponentDef();
    componentDef.setName(name);
    componentDef.setEntries(entries);
    return componentDef;
  }
}
