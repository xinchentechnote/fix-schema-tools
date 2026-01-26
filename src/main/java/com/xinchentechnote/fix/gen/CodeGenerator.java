package com.xinchentechnote.fix.gen;

import com.xinchentechnote.fix.model.MessageDefTemplateModel;
import com.xinchentechnote.fix.parser.*;
import com.xinchentechnote.fix.utils.StringTemplateHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public interface CodeGenerator {

  default List<MsgCodeModel> parseCodeModel(
      FixSchema fix, String packageName, Set<String> msgNames) {
    List<MsgCodeModel> msgCodeModels = new ArrayList<>();
    if (null != fix.getMessages() && null != msgNames) {
      for (String msgName : msgNames) {
        MessageDef msgDef = fix.getMessages().get(msgName);
        MsgCodeModel model = new MsgCodeModel();
        model.setMessageType(msgDef.getMsgType());
        model.setMessageName(msgDef.getName());
        model.setPackageName(packageName);
        model.setEncodeCode(encodeMessage(fix.getHeader(), msgDef, fix.getTrailer()));
        model.setDecodeCode(decodeMessage(fix.getHeader(), msgDef, fix.getTrailer()));
        msgCodeModels.add(model);
      }
    }
    return msgCodeModels;
  }

  default String encodeMessage(MessageDef header, MessageDef message, MessageDef trailer) {
    List<String> codes = new ArrayList<>();
    String name = message.getName();
    String instanceName = StringUtils.uncapitalize(name);
    MessageDefTemplateModel templateModel = MessageDefTemplateModel.buildTemplateModel(message);
    codes.add(
        StringTemplateHelper.render(
            "ObjectNode ${instanceName}Node = MAPPER.createObjectNode();", templateModel));
    codes.add(
        StringTemplateHelper.render(
            "Message.Header header = ${instanceName}.getHeader();", templateModel));
    codes.add(
        StringTemplateHelper.render(
            "Message.Trailer trailer = ${instanceName}.getTrailer();", templateModel));
    codes.addAll(encodeMessage(MsgType.HEADER, instanceName, header));
    codes.addAll(encodeMessage(MsgType.BODY, instanceName, message));
    codes.addAll(encodeMessage(MsgType.TRAILER, instanceName, trailer));
    return String.join("\n", codes);
  }

  default List<String> encodeMessage(MsgType type, String name, MessageDef msg) {
    List<String> codes = new ArrayList<>();
    for (Entry entry : msg.getEntries()) {
      codes.addAll(encodeEntry(type, name, entry));
    }
    return codes;
  }

  default List<String> encodeEntry(MsgType msgType, String parentName, Entry entry) {
    if (entry instanceof FieldEntry) {
      return encodeEntry(msgType, parentName, (FieldEntry) entry);
    } else if (entry instanceof ComponentEntry) {
      return encodeEntry(msgType, parentName, (ComponentEntry) entry);
    } else {
      return encodeEntry(msgType, parentName, (GroupEntry) entry);
    }
  }

  List<String> encodeEntry(MsgType msgType, String parentName, FieldEntry entry);

  List<String> encodeEntry(MsgType msgType, String parentName, ComponentEntry entry);

  List<String> encodeEntry(MsgType msgType, String parentName, GroupEntry entry);

  default String decodeMessage(MessageDef header, MessageDef message, MessageDef trailer) {
    List<String> codes = new ArrayList<>();
    String name = message.getName();
    String instanceName = StringUtils.uncapitalize(name);
    MessageDefTemplateModel templateModel = MessageDefTemplateModel.buildTemplateModel(message);
    codes.add(
        StringTemplateHelper.render("${name} ${instanceName} = new ${name}();", templateModel));
    codes.add(
        StringTemplateHelper.render(
            "Message.Header header = ${instanceName}.getHeader();", templateModel));
    codes.add(
        StringTemplateHelper.render(
            "Message.Trailer trailer = ${instanceName}.getTrailer();", templateModel));
    codes.addAll(decodeMessage(MsgType.HEADER, instanceName, header));
    codes.addAll(decodeMessage(MsgType.BODY, instanceName, message));
    codes.addAll(decodeMessage(MsgType.TRAILER, instanceName, trailer));
    return String.join("\n", codes);
  }

  default List<String> decodeMessage(MsgType type, String name, MessageDef msg) {
    List<String> codes = new ArrayList<>();
    for (Entry entry : msg.getEntries()) {
      codes.addAll(decodeEntry(type, name, entry));
    }
    return codes;
  }

  default List<String> decodeEntry(MsgType msgType, String parentName, Entry entry) {
    if (entry instanceof FieldEntry) {
      return decodeEntry(msgType, parentName, (FieldEntry) entry);
    } else if (entry instanceof ComponentEntry) {
      return decodeEntry(msgType, parentName, (ComponentEntry) entry);
    } else {
      return decodeEntry(msgType, parentName, (GroupEntry) entry);
    }
  }

  List<String> decodeEntry(MsgType msgType, String parentName, FieldEntry entry);

  List<String> decodeEntry(MsgType msgType, String parentName, ComponentEntry entry);

  List<String> decodeEntry(MsgType msgType, String parentName, GroupEntry entry);
}
