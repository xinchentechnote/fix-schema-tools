package com.xinchentechnote.fix.gen;

import com.xinchentechnote.fix.parser.BaseMessage;
import com.xinchentechnote.fix.parser.Fix;
import com.xinchentechnote.fix.parser.Message;
import com.xinchentechnote.fix.utils.StringTemplateHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public interface CodeGenerator {

  default List<MsgCodeModel> parseCodeModel(Fix fix, String packageName) {
    List<MsgCodeModel> msgCodeModels = new ArrayList<>();
    if (null != fix.getMessages()
        && null != fix.getMessages().getMessages()
        && !fix.getMessages().getMessages().isEmpty()) {
      for (Message msg : fix.getMessages().getMessages()) {
        MsgCodeModel model = new MsgCodeModel();
        model.setMessageType(msg.getMsgtype());
        model.setMessageName(msg.getName());
        model.setPackageName(packageName);
        model.setEncodeCode(encodeMessage(fix.getHeader(), msg, fix.getTrailer()));
        model.setDecodeCode(decodeMessage(fix.getHeader(), msg, fix.getTrailer()));
        msgCodeModels.add(model);
      }
    }
    return msgCodeModels;
  }

  default String encodeMessage(BaseMessage header, Message message, BaseMessage trailer) {
    List<String> codes = new ArrayList<>();
    String name = message.getName();
    String instanceName = StringUtils.capitalize(name);
    codes.add(
        StringTemplateHelper.render("${name} ${instanceName} = new ${name}();", message.getInfo()));
    codes.addAll(encodeMessage(MsgType.HEADER, instanceName, header));
    codes.addAll(encodeMessage(MsgType.BODY, instanceName, message));
    codes.addAll(encodeMessage(MsgType.TRAILER, instanceName, trailer));
    return codes.stream().collect(Collectors.joining(","));
  }

  List<String> encodeMessage(MsgType type, String instanceName, BaseMessage msg);

  default String decodeMessage(BaseMessage header, Message message, BaseMessage trailer) {
    List<String> codes = new ArrayList<>();
    String instanceName = message.getInfo().getInstanceName();
    codes.addAll(decodeMessage(MsgType.HEADER, instanceName, header));
    codes.addAll(decodeMessage(MsgType.BODY, instanceName, message));
    codes.addAll(decodeMessage(MsgType.TRAILER, instanceName, trailer));
    return codes.stream().collect(Collectors.joining(","));
  }

  List<String> decodeMessage(MsgType type, String instanceName, BaseMessage msg);
}
