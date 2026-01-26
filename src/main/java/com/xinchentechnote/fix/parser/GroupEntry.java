package com.xinchentechnote.fix.parser;

import java.util.List;
import lombok.Data;

@Data
public final class GroupEntry implements Entry {
  String name;
  boolean required;
  GroupDef def;

  public static GroupEntry build(String name, boolean required, List<Entry> entries) {
    GroupEntry groupEntry = new GroupEntry();
    groupEntry.setName(name);
    groupEntry.setRequired(required);
    GroupDef def = new GroupDef();
    def.setName(name);
    def.setEntries(entries);
    groupEntry.setDef(def);
    return groupEntry;
  }
}
