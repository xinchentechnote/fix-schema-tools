package com.xinchentechnote.fix.parser.v2;

import java.util.HashMap;
import java.util.List;
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
  private Map<String, MessageDef> typeMessageMaps = new HashMap<>();

  public void postProcess() {
    // Link FieldEntry and ComponentEntry to their definitions
    for (MessageDef message : messages.values()) {
      typeMessageMaps.put(message.getMsgType(), message);
      linkEntries(message.getEntries());
    }
    for (ComponentDef component : components.values()) {
      linkEntries(component.getEntries());
    }
    linkEntries(header.getEntries());
    linkEntries(trailer.getEntries());
  }

  private void linkEntries(List<Entry> entries) {
    for (Entry entry : entries) {
      if (entry instanceof FieldEntry) {
        FieldEntry fieldEntry = (FieldEntry) entry;
        FieldDef def = fields.get(fieldEntry.getName());
        //        assert def != null;
        fieldEntry.setDef(def);
      } else if (entry instanceof GroupEntry) {
        GroupEntry groupEntry = (GroupEntry) entry;
        linkEntries(groupEntry.getDef().getEntries());
      } else if (entry instanceof ComponentEntry) {
        ComponentEntry componentEntry = (ComponentEntry) entry;
        ComponentDef def = components.get(componentEntry.getName());
        //        assert def != null;
        componentEntry.setDef(def);
      }
    }
  }

  public MessageDef getMessage(String msgType) {
    return typeMessageMaps.get(msgType);
  }
}
