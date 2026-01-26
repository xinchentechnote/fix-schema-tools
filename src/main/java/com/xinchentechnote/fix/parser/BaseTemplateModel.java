package com.xinchentechnote.fix.parser;

import com.xinchentechnote.fix.gen.MsgType;
import lombok.Data;
import org.apache.commons.text.CaseUtils;

@Data
public class BaseTemplateModel {
  public BaseTemplateModel(String name) {
    this.name = name;
  }

  public BaseTemplateModel(String name, MsgType msgType, String parentName) {
    this.name = name;
    setParentName(parentName);
    switch (msgType) {
      case TRAILER:
        this.parentFixName = "trailer";
        break;
      case HEADER:
        this.parentFixName = "header";
        break;
      default:
        this.parentFixName = parentName;
    }
  }

  private String name;
  private String parentName;
  private String parentFixName;
  private String parentUpperName;

  public void setParentName(String parentName) {
    this.parentName = parentName;
    this.parentUpperName = CaseUtils.toCamelCase(parentName, true, '_');
  }
}
