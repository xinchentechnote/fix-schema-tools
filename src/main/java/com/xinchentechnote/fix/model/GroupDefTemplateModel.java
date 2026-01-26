package com.xinchentechnote.fix.model;

import java.util.stream.Collectors;
import lombok.Data;

@Data
public class GroupDefTemplateModel {
  String name;
  String delimiter;
  String fieldList;

  public static GroupDefTemplateModel buildTemplateModel(GroupDef groupDef) {
    GroupDefTemplateModel templateModel = new GroupDefTemplateModel();
    templateModel.name = groupDef.name;
    templateModel.delimiter = groupDef.delimiter;
    templateModel.fieldList =
        groupDef.entries.stream()
            .map(field -> field.getName() + ".FIELD")
            .collect(Collectors.joining(","));
    return templateModel;
  }
}
