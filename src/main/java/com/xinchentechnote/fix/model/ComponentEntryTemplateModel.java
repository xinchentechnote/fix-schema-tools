package com.xinchentechnote.fix.model;

import com.xinchentechnote.fix.gen.MsgType;
import com.xinchentechnote.fix.parser.ComponentEntry;

public class ComponentEntryTemplateModel extends BaseTemplateModel {
  public ComponentEntryTemplateModel(String name, MsgType msgType, String parentName) {
    super(name, msgType, parentName);
  }

  public static ComponentEntryTemplateModel buildTemplateModel(
      ComponentEntry entry, MsgType msgType, String parentName) {
    return new ComponentEntryTemplateModel(entry.getName(), msgType, parentName);
  }
}
