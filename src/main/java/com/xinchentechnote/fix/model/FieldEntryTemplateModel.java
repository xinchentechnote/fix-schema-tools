package com.xinchentechnote.fix.model;

import com.xinchentechnote.fix.gen.MsgType;
import com.xinchentechnote.fix.parser.FieldEntry;
import lombok.Data;

@Data
public class FieldEntryTemplateModel {
  private String name;
  private String parentName = "";
  private String parentFixName = "";
  private String afterSetMethod = "";
  private String fixGetMethod = "";
  private String afterGetMethod = "";

  public static FieldEntryTemplateModel buildTemplateModel(
      FieldEntry entry, MsgType msgType, String parentName) {
    FieldEntryTemplateModel model = new FieldEntryTemplateModel();
    model.setName(entry.getName());
    model.setParentName(parentName);
    switch (msgType) {
      case TRAILER:
        model.parentFixName = "trailer";
        break;
      case HEADER:
        model.parentFixName = "header";
        break;
      default:
        model.parentFixName = parentName;
    }
    return model;
  }
}
