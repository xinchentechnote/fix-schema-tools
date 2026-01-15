package com.xinchentechnote.fix.parser.v2;

import com.xinchentechnote.fix.gen.MsgType;
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
    def.setDelimiter(entries.get(0).getName());
    def.setEntries(entries);
    groupEntry.setDef(def);
    return groupEntry;
  }

  public TemplateModel buildTemplateModel(MsgType msgType, String parentName) {
    return new TemplateModel(name, msgType, parentName);
  }

  public static class TemplateModel extends BaseTemplateModel {

    public TemplateModel(String name, MsgType msgType, String parentName) {
      super(name, msgType, parentName);
    }
  }
}
