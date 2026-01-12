package com.xinchentechnote.fix.parser.v2;

import com.xinchentechnote.fix.gen.MsgType;
import java.util.List;
import lombok.Data;

@Data
public final class GroupEntry implements Entry {
  String name;
  boolean required;
  List<Entry> entries;

  public TemplateModel buildTemplateModel(MsgType msgType, String parentName) {
    return null;
  }

  public class TemplateModel {}
}
