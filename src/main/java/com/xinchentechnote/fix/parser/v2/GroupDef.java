package com.xinchentechnote.fix.parser.v2;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class GroupDef implements StructuredDef {
  String name;
  String delimiter;
  List<Entry> entries;

  @Data
  public static class TemplateModel {
    String name;
    String delimiter;
    String fieldList;
  }

  public TemplateModel buildTemplateModel() {
    TemplateModel templateModel = new TemplateModel();
    templateModel.name = this.name;
    templateModel.delimiter = this.delimiter;
    templateModel.fieldList =
        entries.stream().map(field -> field.getName() + ".FIELD").collect(Collectors.joining(","));
    return templateModel;
  }
}
