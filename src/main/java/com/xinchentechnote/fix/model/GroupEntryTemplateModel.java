package com.xinchentechnote.fix.model;

import com.xinchentechnote.fix.gen.MsgType;

public class GroupEntryTemplateModel extends BaseTemplateModel {

  public GroupEntryTemplateModel(String name, MsgType msgType, String parentName) {
    super(name, msgType, parentName);
  }

  public static GroupEntryTemplateModel buildTemplateModel(
      GroupEntry entry, MsgType msgType, String parentName) {
    return new GroupEntryTemplateModel(entry.name, msgType, parentName);
  }
}
