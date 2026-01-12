package com.xinchentechnote.fix.parser.v2;

import java.util.List;
import lombok.Data;

@Data
public class MessageDef implements StructuredDef {
  String name;
  String msgType;
  List<Entry> entries;

  public TemplateModel buildTemplateModel() {
    return new TemplateModel();
  }

  public static class TemplateModel {}
}
