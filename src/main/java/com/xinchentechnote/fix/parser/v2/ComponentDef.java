package com.xinchentechnote.fix.parser.v2;

import java.util.List;
import lombok.Data;

@Data
public class ComponentDef implements StructuredDef {
  String name;
  List<Entry> entries;
}
