package com.xinchentechnote.fix.gen;

import com.xinchentechnote.fix.parser.BaseField;
import com.xinchentechnote.fix.parser.BaseMessage;
import com.xinchentechnote.fix.parser.FieldDef;
import com.xinchentechnote.fix.type.JavaTypeMapping;
import com.xinchentechnote.fix.type.TypeInfo;
import com.xinchentechnote.fix.type.TypeMapping;
import com.xinchentechnote.fix.utils.StringTemplateHelper;
import java.util.ArrayList;
import java.util.List;

public class JavaFixJsonCodecGenerator implements CodeGenerator {
  private TypeMapping typeMapping = new JavaTypeMapping();

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
              "root.put(\"${name}\", ${parenName}${headerOrTrailer}.${fixGetMethod}(${name}.FIELD)${afterGetMethod});",
              info);
      if (!baseField.isRequired()) {
        codes.add(
            StringTemplateHelper.render(
                "if (${parenName}${headerOrTrailer}.isSetField(${name}.FIELD)) {", info));
        codes.add("  " + code);
        codes.add("}");
      } else {
        codes.add(code);
      }
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
              "${parenName}${headerOrTrailer}.setField(new ${name}(root.get(\"${name}\")${afterSetMethod}));",
              info);
      if (!baseField.isRequired()) {
        codes.add(StringTemplateHelper.render("if (root.has(\"${name}\")) {", info));
        codes.add("  " + code);
        codes.add("}");
      } else {
        codes.add(code);
      }
    }
    return codes;
  }
}
