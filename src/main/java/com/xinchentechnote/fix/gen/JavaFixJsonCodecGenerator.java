package com.xinchentechnote.fix.gen;

import com.xinchentechnote.fix.parser.BaseMessage;
import java.util.ArrayList;
import java.util.List;

public class JavaFixJsonCodecGenerator implements CodeGenerator {

  @Override
  public List<String> encodeMessage(MsgType type, String instanceName, BaseMessage msg) {
    List<String> codes = new ArrayList<>();

    return codes;
  }

  @Override
  public List<String> decodeMessage(MsgType type, String instanceName, BaseMessage msg) {
    List<String> codes = new ArrayList<>();
    return codes;
  }
}
