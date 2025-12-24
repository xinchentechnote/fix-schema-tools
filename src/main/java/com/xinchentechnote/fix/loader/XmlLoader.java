package com.xinchentechnote.fix.loader;

import com.xinchentechnote.fix.ir.FixSchema;
import java.io.InputStream;
import quickfix.ConfigError;
import quickfix.DataDictionary;

public class XmlLoader {

  public FixSchema load(InputStream in) {
    FixSchema schema = new FixSchema();

    try {
      DataDictionary dd = new DataDictionary(in);
    } catch (ConfigError e) {
      e.printStackTrace();
    }
    return schema;
  }
}
