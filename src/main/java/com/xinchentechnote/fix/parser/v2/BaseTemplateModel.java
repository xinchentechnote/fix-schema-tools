package com.xinchentechnote.fix.parser.v2;

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
        this.headerOrTrailer = ".getTrailer()";
        break;
      case HEADER:
        this.headerOrTrailer = ".getHeader()";
        break;
    }
  }

  private String name;
  private String parentName;
  private String parentUpperName;
  private String headerOrTrailer = "";

  public void setParentName(String parentName) {
    this.parentName = parentName;
    this.parentUpperName = CaseUtils.toCamelCase(parentName, true, '_');
  }
}
