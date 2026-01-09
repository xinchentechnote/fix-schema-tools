package com.xinchentechnote.fix.parser.v2;

import java.util.Map;
import lombok.Data;

@Data
public class FixSchema {
  private String version;
  private MessageDef header;
  private MessageDef trailer;
  private Map<String, FieldDef> fields;
  private Map<String, ComponentDef> components;
  private Map<String, MessageDef> messages;
}
