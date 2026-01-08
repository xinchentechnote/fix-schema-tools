package com.xinchentechnote.fix.parser.v2;

import lombok.Data;

@Data
public final class FieldEntry implements Entry {
  String name;
  boolean required;
}
