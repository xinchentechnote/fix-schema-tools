package com.xinchentechnote.fix.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Jsons {
  public static final ObjectMapper MAPPER =
      new ObjectMapper()
          .findAndRegisterModules()
          .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
}
