package com.xinchentechnote.fix.gen.v2;

import com.xinchentechnote.fix.gen.MsgType;
import com.xinchentechnote.fix.parser.v2.*;
import com.xinchentechnote.fix.type.JavaTypeMapping;
import com.xinchentechnote.fix.type.TypeInfo;
import com.xinchentechnote.fix.type.TypeMapping;
import com.xinchentechnote.fix.utils.StringTemplateHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JavaFixJsonCodecGenerator implements CodeGenerator {
  private final TypeMapping typeMapping = new JavaTypeMapping();

  private static final Set<String> DECODE_SKIP_FIELDS = Set.of("CheckSum", "BodyLength", "MsgType");

  @Override
  public List<String> encodeEntry(MsgType msgType, String parentName, FieldEntry entry) {
    List<String> codes = new ArrayList<>();
    FieldDef fieldDef = entry.getDef();
    TypeInfo typeInfo = typeMapping.getType(fieldDef.getType().name());
    FieldEntry.TemplateModel model = entry.buildTemplateModel(msgType, parentName);
    model.setFixGetMethod(typeInfo.getFixGetMethod());
    model.setAfterGetMethod(typeInfo.getAfterGetMethod());
    String code =
        StringTemplateHelper.render(
            "${parentName}Node.put(\"${name}\", ${parentFixName}.${fixGetMethod}(${name}.FIELD)${afterGetMethod});",
            model);
    if (!entry.isRequired()) {
      codes.add(
          StringTemplateHelper.render("if (${parentFixName}.isSetField(${name}.FIELD)) {", model));
      codes.add("  " + code);
      codes.add("}");
    } else {
      codes.add(code);
    }
    return codes;
  }

  @Override
  public List<String> encodeEntry(MsgType msgType, String parentName, ComponentEntry entry) {
    List<String> codes = new ArrayList<>();
    ComponentEntry.TemplateModel model = entry.buildTemplateModel(msgType, parentName);
    codes.add(
        StringTemplateHelper.render(
            "ObjectNode ${parentName}${name}Node = MAPPER.createObjectNode();", model));
    codes.add(StringTemplateHelper.render("FieldMap ${parentName}${name} = ${parentName};", model));
    for (Entry subEntry : entry.getDef().getEntries()) {
      codes.addAll(encodeEntry(MsgType.COMPONENT, parentName + entry.getName(), subEntry));
    }
    codes.add(
        StringTemplateHelper.render(
            "${parentName}Node.set(\"${name}\", ${parentName}${name}Node);", model));
    return codes;
  }

  @Override
  public List<String> encodeEntry(MsgType msgType, String parentName, GroupEntry entry) {
    List<String> codes = new ArrayList<>();

    GroupEntry.TemplateModel model = entry.buildTemplateModel(msgType, parentName);
    if (!entry.isRequired()) {
      codes.add(
          StringTemplateHelper.render("if (${parentFixName}.isSetField(${name}.FIELD)) {", model));
    }
    GroupDef.TemplateModel groupInfo = entry.getDef().buildTemplateModel();
    String newGroup =
        StringTemplateHelper.render(
            "new Group(${name}.FIELD,${delimiter}.FIELD,new int[]{${fieldList},0});", groupInfo);
    codes.add(StringTemplateHelper.render("Group ${parentName}${name}Group = " + newGroup, model));
    codes.add(
        StringTemplateHelper.render(
            "ArrayNode ${parentName}${name}Node = MAPPER.createArrayNode();", model));
    codes.add(
        StringTemplateHelper.render(
            "for(int i = 1;i <= ${parentFixName}.getGroupCount(${name}.FIELD);i++) {", model));
    codes.add(
        StringTemplateHelper.render(
            "${parentFixName}.getGroup(i, ${parentName}${name}Group);", model));
    codes.add(
        StringTemplateHelper.render(
            "ObjectNode ${parentName}${name}GroupNode = MAPPER.createObjectNode();", model));
    for (Entry entryEntry : entry.getDef().getEntries()) {
      codes.addAll(encodeEntry(MsgType.GROUP, parentName + entry.getName() + "Group", entryEntry));
    }
    codes.add(
        StringTemplateHelper.render(
            "${parentName}${name}Node.add(${parentName}${name}GroupNode);", model));
    codes.add("}");
    codes.add(
        StringTemplateHelper.render(
            "${parentName}Node.set(\"${name}\", ${parentName}${name}Node);", model));
    if (!entry.isRequired()) {
      codes.add("}");
    }
    return codes;
  }

  @Override
  public List<String> decodeEntry(MsgType msgType, String parentName, FieldEntry entry) {
    if (DECODE_SKIP_FIELDS.contains(entry.getName())) {
      return Collections.emptyList();
    }
    List<String> codes = new ArrayList<>();
    FieldDef fieldDef = entry.getDef();
    TypeInfo typeInfo = typeMapping.getType(fieldDef.getType().name());
    FieldEntry.TemplateModel model = entry.buildTemplateModel(msgType, parentName);
    model.setFixGetMethod(typeInfo.getFixGetMethod());
    model.setAfterSetMethod(typeInfo.getAfterSetMethod());
    model.setAfterGetMethod(typeInfo.getAfterGetMethod());

    String code =
        StringTemplateHelper.render(
            "${parentFixName}.setField(new ${name}(${parentName}Node.get(\"${name}\")${afterSetMethod}));",
            model);
    if (fieldDef.isUtc()) {
      code =
          StringTemplateHelper.render(
              "${parentFixName}.setField(new ${name}(newLocalDateTime(${parentName}Node.get(\"${name}\")${afterSetMethod})));",
              model);
    }
    if (!entry.isRequired()) {
      codes.add(StringTemplateHelper.render("if (${parentName}Node.has(\"${name}\")) {", model));
      codes.add("  " + code);
      codes.add("}");
    } else {
      codes.add(code);
    }
    return codes;
  }

  @Override
  public List<String> decodeEntry(MsgType msgType, String parentName, ComponentEntry entry) {

    List<String> codes = new ArrayList<>();
    ComponentEntry.TemplateModel model = entry.buildTemplateModel(msgType, parentName);
    codes.add(StringTemplateHelper.render("if (${parentName}Node.has(\"${name}\")) {", model));
    codes.add(
        StringTemplateHelper.render(
            "ObjectNode ${parentName}${name}Node = (ObjectNode) ${parentName}Node.get(\"${name}\");",
            model));
    codes.add(StringTemplateHelper.render("FieldMap ${parentName}${name} = ${parentName};", model));
    ComponentDef componentDef = entry.getDef();
    for (Entry componentDefEntry : componentDef.getEntries()) {
      codes.addAll(decodeEntry(MsgType.COMPONENT, parentName + entry.getName(), componentDefEntry));
    }
    codes.add("}");
    return codes;
  }

  @Override
  public List<String> decodeEntry(MsgType msgType, String parentName, GroupEntry entry) {

    List<String> codes = new ArrayList<>();
    GroupEntry.TemplateModel model = entry.buildTemplateModel(msgType, parentName);
    codes.add(StringTemplateHelper.render("if (${parentName}Node.has(\"${name}\")) {", model));
    codes.add(
        StringTemplateHelper.render(
            "ArrayNode ${parentName}${name}GroupNodes = (ArrayNode)${parentName}Node.get(\"${name}\");",
            model));
    codes.add(
        StringTemplateHelper.render(
            "for (JsonNode ${parentName}${name}GroupNode : ${parentName}${name}GroupNodes) {",
            model));
    GroupDef.TemplateModel groupInfo = entry.getDef().buildTemplateModel();
    String newGroup =
        StringTemplateHelper.render(
            "new Group(${name}.FIELD,${delimiter}.FIELD,new int[]{${fieldList},0});", groupInfo);
    codes.add(StringTemplateHelper.render("Group ${parentName}${name}Group = " + newGroup, model));
    for (Entry groupEntry : entry.getDef().getEntries()) {
      codes.addAll(decodeEntry(MsgType.GROUP, parentName + entry.getName() + "Group", groupEntry));
    }
    codes.add(
        StringTemplateHelper.render(
            "${parentFixName}.addGroup(${parentName}${name}Group);", model));
    codes.add("}");
    codes.add("}");
    return codes;
  }
}
