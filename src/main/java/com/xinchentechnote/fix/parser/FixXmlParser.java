package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.InputStream;

public class FixXmlParser {

  public Fix loadFix(InputStream in) throws Exception {

    JAXBContext jaxb =
        JAXBContext.newInstance(
            Fix.class, Fields.class, Field.class, Messages.class, Message.class);
    Unmarshaller un = jaxb.createUnmarshaller();
    Fix xml = (Fix) un.unmarshal(in);
    return xml;
  }
}
