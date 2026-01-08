package com.xinchentechnote.fix.parser.v2;

import lombok.Data;

@Data
public class FieldDef {
  int number;
  String name;
  FixType type;
}
