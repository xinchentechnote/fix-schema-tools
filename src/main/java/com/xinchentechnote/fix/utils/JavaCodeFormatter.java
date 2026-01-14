package com.xinchentechnote.fix.utils;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.googlejavaformat.java.JavaFormatterOptions;

public class JavaCodeFormatter {
  public static String format(String code) {
    JavaFormatterOptions options =
        JavaFormatterOptions.builder().style(JavaFormatterOptions.Style.GOOGLE).build();
    try {
      return new Formatter(options).formatSource(code);
    } catch (FormatterException e) {
      return code;
    }
  }
}
