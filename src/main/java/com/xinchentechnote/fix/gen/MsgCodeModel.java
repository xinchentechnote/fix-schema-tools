package com.xinchentechnote.fix.gen;

import lombok.Data;

@Data
public class MsgCodeModel {
  private String packageName;
  private String messageType;
  private String messageName;
  private String encodeCode;
  private String decodeCode;
}
