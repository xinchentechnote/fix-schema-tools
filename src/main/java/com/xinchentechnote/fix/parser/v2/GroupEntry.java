package com.xinchentechnote.fix.parser.v2;

import java.util.List;
import lombok.Data;

@Data
public final class GroupEntry implements Entry {
  String name;
  boolean required;
  List<Entry> entries;
}
