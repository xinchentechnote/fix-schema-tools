package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.InputStream;

public class FixXmlParser {

  public FixXml loadFix(InputStream in) throws Exception {

    JAXBContext jaxb =
        JAXBContext.newInstance(
            FixXml.class, Fields.class, Field.class, Messages.class, Message.class);
    Unmarshaller un = jaxb.createUnmarshaller();
    FixXml xml = (FixXml) un.unmarshal(in);
    return xml;
  }
}
