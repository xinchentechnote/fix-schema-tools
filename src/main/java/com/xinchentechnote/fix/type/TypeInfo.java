package com.xinchentechnote.fix.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeInfo {
  private String originalType = "";
  private String targetType = "";
  private String fixGetMethod = "";
  private String afterSetMethod = "";
  private String afterGetMethod = "";

  public TypeInfo(
      String originalType, String targetType, String fixGetMethod, String afterSetMethod) {
    this.originalType = originalType;
    this.targetType = targetType;
    this.fixGetMethod = fixGetMethod;
    this.afterSetMethod = afterSetMethod;
  }
}
