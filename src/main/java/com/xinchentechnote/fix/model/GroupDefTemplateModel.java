package com.xinchentechnote.fix.model;

import com.xinchentechnote.fix.parser.GroupDef;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class GroupDefTemplateModel {
  String name;
  String delimiter;
  String fieldList;

  public static GroupDefTemplateModel buildTemplateModel(GroupDef groupDef) {
    GroupDefTemplateModel templateModel = new GroupDefTemplateModel();
    templateModel.name = groupDef.getName();
    templateModel.delimiter = groupDef.getDelimiter();
    templateModel.fieldList =
        groupDef.getEntries().stream()
            .map(field -> field.getName() + ".FIELD")
            .collect(Collectors.joining(","));
    return templateModel;
  }
}
