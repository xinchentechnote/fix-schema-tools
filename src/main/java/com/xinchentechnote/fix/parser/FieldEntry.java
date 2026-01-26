package com.xinchentechnote.fix.parser;

import lombok.Data;

@Data
public final class FieldEntry implements Entry {
  private String name;
  private boolean required;
  private FieldDef def;

  public static FieldEntry build(String name, boolean required, int number, FixType type) {
    FieldEntry entry = new FieldEntry();
    entry.setName(name);
    entry.setRequired(required);
    FieldDef def = new FieldDef();
    def.setNumber(number);
    def.setName(name);
    def.setType(type);
    entry.setDef(def);
    return entry;
  }
}
