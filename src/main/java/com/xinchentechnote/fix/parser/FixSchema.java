package com.xinchentechnote.fix.parser;

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
      linkDef(message.getEntries());
    }
    for (ComponentDef component : components.values()) {
      linkDef(component.getEntries());
    }
    linkDef(header.getEntries());
    linkDef(trailer.getEntries());

    for (MessageDef message : messages.values()) {
      typeMessageMaps.put(message.getMsgType(), message);
      fillGroup(message.getEntries());
    }
    for (ComponentDef component : components.values()) {
      fillGroup(component.getEntries());
    }
    fillGroup(header.getEntries());
    fillGroup(trailer.getEntries());
  }

  private void fillGroup(List<Entry> entries) {
    if (entries.isEmpty()) {
      return;
    }
    for (Entry entry : entries) {
      if (entry instanceof GroupEntry) {
        GroupEntry groupEntry = (GroupEntry) entry;
        GroupDef def = groupEntry.getDef();
        FieldDef firstField = getFirstField(def.getEntries());
        FieldDef fieldDef = fields.get(def.getName());
        if (fieldDef != null) {
          def.setNumber(fieldDef.getNumber());
        }
        if (firstField != null) {
          def.setDelimiter(firstField.getName());
          def.setDelimiterNumber(firstField.getNumber());
        }
        fillGroup(def.getEntries());
      } else if (entry instanceof ComponentEntry) {
        ComponentEntry componentEntry = (ComponentEntry) entry;
        ComponentDef def = componentEntry.getDef();
        if (null != def) {
          fillGroup(def.getEntries());
        }
      }
    }
  }

  private void linkDef(List<Entry> entries) {
    for (Entry entry : entries) {
      if (entry instanceof FieldEntry) {
        FieldEntry fieldEntry = (FieldEntry) entry;
        FieldDef def = fields.get(fieldEntry.getName());
        fieldEntry.setDef(def);
      } else if (entry instanceof GroupEntry) {
        GroupEntry groupEntry = (GroupEntry) entry;
        GroupDef def = groupEntry.getDef();
        linkDef(def.getEntries());
      } else if (entry instanceof ComponentEntry) {
        ComponentEntry componentEntry = (ComponentEntry) entry;
        ComponentDef def = components.get(componentEntry.getName());
        componentEntry.setDef(def);
        if (null != def) {
          linkDef(def.getEntries());
        }
      }
    }
  }

  public FieldDef getFirstField(List<Entry> entries) {
    Entry entry = entries.get(0);
    if (entry instanceof GroupEntry) {
      return getFirstField(((GroupEntry) entry).getDef().getEntries());
    } else if (entry instanceof ComponentEntry) {
      return getFirstField(((ComponentEntry) entry).getDef().getEntries());
    }
    return ((FieldEntry) entry).getDef();
  }

  public MessageDef getMessage(String msgType) {
    return typeMessageMaps.get(msgType);
  }
}
