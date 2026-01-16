package com.xinchentechnote.fix.gen;

import com.xinchentechnote.fix.parser.BaseField;
import com.xinchentechnote.fix.parser.BaseMessage;
import com.xinchentechnote.fix.parser.CompositeField;
import com.xinchentechnote.fix.parser.FieldDef;
import com.xinchentechnote.fix.type.JavaTypeMapping;
import com.xinchentechnote.fix.type.TypeInfo;
import com.xinchentechnote.fix.type.TypeMapping;
import com.xinchentechnote.fix.utils.StringTemplateHelper;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class JavaFixJsonCodecGenerator implements CodeGenerator {
  private final TypeMapping typeMapping = new JavaTypeMapping();

  @Override
  public List<String> encodeMessage(MsgType type, String instanceName, BaseMessage msg) {
    List<String> codes = new ArrayList<>();

    for (BaseField baseField : msg.getBaseFields()) {
      FieldDef fieldDef = baseField.getFieldDef();
      TypeInfo typeInfo = typeMapping.getType(fieldDef.getType());
      BaseField.Info info = baseField.getInfo(type, instanceName);
      info.setFixGetMethod(typeInfo.getFixGetMethod());
      info.setAfterGetMethod(typeInfo.getAfterGetMethod());
      String code =
          StringTemplateHelper.render(
              "${parentName}Node.put(\"${name}\", ${parentName}${headerOrTrailer}.${fixGetMethod}(${name}.FIELD)${afterGetMethod});",
              info);
      if (!baseField.isRequired()) {
        codes.add(
            StringTemplateHelper.render(
                "if (${parentName}${headerOrTrailer}.isSetField(${name}.FIELD)) {", info));
        codes.add("  " + code);
        codes.add("}");
      } else {
        codes.add(code);
      }
    }

    for (CompositeField groupField : msg.getGroupFields()) {
      CompositeField.Info info = groupField.getInfo(type, instanceName);
      if (!groupField.isRequired()) {
        codes.add(
            StringTemplateHelper.render(
                "if (${parentName}${headerOrTrailer}.isSetField(${name}.FIELD)) {", info));
      }
      codes.add(
          StringTemplateHelper.render(
              "${parentUpperName}.${name} ${parentName}${name}Group = new ${parentUpperName}.${name}();",
              info));
      codes.add(
          StringTemplateHelper.render(
              "ArrayNode ${parentName}${name}Node = MAPPER.createArrayNode();", info));
      codes.add(
          StringTemplateHelper.render(
              "for(int i = 1;i <= ${parentName}.getGroupCount(${name}.FIELD);i++) {", info));
      codes.add(
          StringTemplateHelper.render(
              "${parentName}.getGroup(i, ${parentName}${name}Group);", info));
      codes.add(
          StringTemplateHelper.render(
              "ObjectNode ${parentName}${name}GroupNode = MAPPER.createObjectNode();", info));
      codes.addAll(
          encodeMessage(MsgType.GROUP, instanceName + groupField.getName() + "Group", groupField));
      codes.add(
          StringTemplateHelper.render(
              "${parentName}${name}Node.add(${parentName}${name}GroupNode);", info));
      codes.add("}");
      codes.add(
          StringTemplateHelper.render(
              "${parentName}Node.put(\"${name}\", ${parentName}${name}Node);", info));
      if (!groupField.isRequired()) {
        codes.add("}");
      }
    }
    for (CompositeField componentField : msg.getComponentFields()) {
      CompositeField.Info info = componentField.getInfo(type, instanceName);
      codes.add(
          StringTemplateHelper.render(
              "ObjectNode ${parentName}${name}Node = MAPPER.createObjectNode();", info));
      codes.add(
          StringTemplateHelper.render(
              "${name} ${parentName}${name} = ${parentName}${headerOrTrailer}.get${name}();",
              info));
      codes.addAll(
          encodeMessage(
              MsgType.COMPONENT, instanceName + componentField.getName(), componentField));
      // advertisementNode.put("Instrument", advertisementInstrumentNode);
      codes.add(
          StringTemplateHelper.render(
              "${parentName}Node.set(\"${name}\", ${parentName}${name}Node);", info));
    }
    return codes;
  }

  @Override
  public List<String> decodeMessage(MsgType type, String instanceName, BaseMessage msg) {

    List<String> codes = new ArrayList<>();
    for (BaseField baseField : msg.getBaseFields()) {
      FieldDef fieldDef = baseField.getFieldDef();
      TypeInfo typeInfo = typeMapping.getType(fieldDef.getType());
      BaseField.Info info = baseField.getInfo(type, instanceName);
      info.setFixGetMethod(typeInfo.getFixGetMethod());
      info.setAfterSetMethod(typeInfo.getAfterSetMethod());
      info.setAfterGetMethod(typeInfo.getAfterGetMethod());

      String code =
          StringTemplateHelper.render(
              "${parentName}${headerOrTrailer}.setField(new ${name}(${parentName}Node.get(\"${name}\")${afterSetMethod}));",
              info);
      if (fieldDef.isUtc()) {
        code =
            StringTemplateHelper.render(
                "${parentName}${headerOrTrailer}.setField(new ${name}(newLocalDateTime(${parentName}Node.get(\"${name}\")${afterSetMethod})));",
                info);
      }
      if (!baseField.isRequired()) {
        codes.add(StringTemplateHelper.render("if (${parentName}Node.has(\"${name}\")) {", info));
        codes.add("  " + code);
        codes.add("}");
      } else {
        codes.add(code);
      }
    }

    for (CompositeField groupField : msg.getGroupFields()) {
      CompositeField.Info info = groupField.getInfo(type, instanceName);
      codes.add(StringTemplateHelper.render("if (${parentName}Node.has(\"${name}\")) {", info));
      codes.add(
          StringTemplateHelper.render(
              "ArrayNode ${parentName}${name}GroupNodes = (ArrayNode)${parentName}Node.get(\"${name}\");",
              info));
      codes.add(
          StringTemplateHelper.render(
              "for (JsonNode ${parentName}${name}GroupNode : ${parentName}${name}GroupNodes) {",
              info));
      codes.add(
          StringTemplateHelper.render(
              "${parentUpperName}.${name} ${parentName}${name}Group = new ${parentUpperName}.${name}();",
              info));
      codes.addAll(
          decodeMessage(MsgType.GROUP, instanceName + groupField.getName() + "Group", groupField));
      codes.add(
          StringTemplateHelper.render("${parentName}.addGroup(${parentName}${name}Group);", info));
      codes.add("}");
      codes.add("}");
    }

    for (CompositeField componentField : msg.getComponentFields()) {
      CompositeField.Info info = componentField.getInfo(type, instanceName);
      codes.add(StringTemplateHelper.render("if (${parentName}Node.has(\"${name}\")) {", info));
      codes.add(
          StringTemplateHelper.render(
              "ObjectNode ${parentName}${name}Node = (ObjectNode) ${parentName}Node.get(\"${name}\");",
              info));
      codes.add(
          StringTemplateHelper.render(
              "${name} ${parentName}${name} = ${parentName}.get${name}();", info));

      codes.addAll(
          decodeMessage(
              MsgType.COMPONENT, instanceName + componentField.getName(), componentField));
      codes.add(StringTemplateHelper.render("${parentName}.set(${parentName}${name});", info));
      codes.add("}");
    }
    return codes;
  }
}
