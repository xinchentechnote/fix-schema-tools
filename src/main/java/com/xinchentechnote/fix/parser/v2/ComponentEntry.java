package com.xinchentechnote.fix.parser.v2;

import lombok.Data;

@Data
public final class ComponentEntry implements Entry {
  String name;
  boolean required;
}
