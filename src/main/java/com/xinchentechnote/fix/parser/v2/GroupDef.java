package com.xinchentechnote.fix.parser.v2;

import java.util.List;
import lombok.Data;

@Data
public class GroupDef implements StructuredDef {
  String name;
  int number;
  String delimiter;
  int delimiterNumber;
  List<Entry> entries;
}
