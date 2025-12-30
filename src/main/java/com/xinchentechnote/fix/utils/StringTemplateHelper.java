package com.xinchentechnote.fix.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public class StringTemplateHelper {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @SuppressWarnings("unchecked")
  public static String render(String template, Object obj) {
    if (null == obj) {
      return template;
    }
    Map<String, Object> map = MAPPER.convertValue(obj, Map.class);
    return StringSubstitutor.replace(template, map);
  }

  public static String render(String template, Map<String, Object> obj) {
    if (null == obj) {
      return template;
    }
    return StringSubstitutor.replace(template, obj);
  }
}
