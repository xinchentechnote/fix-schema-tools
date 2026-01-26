package com.xinchentechnote.fix.model;

import com.xinchentechnote.fix.gen.MsgType;
import com.xinchentechnote.fix.parser.GroupEntry;

public class GroupEntryTemplateModel extends BaseTemplateModel {

  public GroupEntryTemplateModel(String name, MsgType msgType, String parentName) {
    super(name, msgType, parentName);
  }

  public static GroupEntryTemplateModel buildTemplateModel(
      GroupEntry entry, MsgType msgType, String parentName) {
    return new GroupEntryTemplateModel(entry.getName(), msgType, parentName);
  }
}
