package com.xinchentechnote.fix.gen;

import com.xinchentechnote.fix.parser.BaseField;
import com.xinchentechnote.fix.parser.BaseMessage;
import com.xinchentechnote.fix.utils.StringTemplateHelper;
import java.util.ArrayList;
import java.util.List;

public class JavaFixJsonCodecGenerator implements CodeGenerator {

  @Override
  public List<String> encodeMessage(MsgType type, String instanceName, BaseMessage msg) {
    List<String> codes = new ArrayList<>();
    for (BaseField baseField : msg.getBaseFields()) {
      codes.add(
          StringTemplateHelper.render(
              "root.put(\"${name}\", ${parenName}${headerOrTrailer}.get${type}(${name}.FIELD));",
              baseField.getInfo(type, instanceName)));
    }
    return codes;
  }

  @Override
  public List<String> decodeMessage(MsgType type, String instanceName, BaseMessage msg) {
    List<String> codes = new ArrayList<>();
    return codes;
  }
}
