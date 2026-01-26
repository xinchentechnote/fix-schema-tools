package com.xinchentechnote.fix.parser;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class MessageDefTemplateModel {
  private String name;
  private String instanceName;

  public MessageDefTemplateModel(String name) {
    this.name = name;
    this.instanceName = StringUtils.uncapitalize(name);
  }

  public static MessageDefTemplateModel buildTemplateModel(MessageDef messageDef) {
    return new MessageDefTemplateModel(messageDef.name);
  }
}
