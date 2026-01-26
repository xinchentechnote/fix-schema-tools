package com.xinchentechnote.fix.parser;

import com.xinchentechnote.fix.parser.FixType;
import lombok.Data;

@Data
public class FieldDef {
  int number;
  String name;
  FixType type;

  public boolean isUtc() {
    return FixType.isUtc(type.name());
  }
}
