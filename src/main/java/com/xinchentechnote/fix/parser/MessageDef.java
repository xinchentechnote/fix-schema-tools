package com.xinchentechnote.fix.parser;

import java.util.List;
import lombok.Data;

@Data
public class MessageDef implements StructuredDef {
  String name;
  String msgType;
  List<Entry> entries;
}
