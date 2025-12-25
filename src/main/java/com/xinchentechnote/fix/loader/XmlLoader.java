package com.xinchentechnote.fix.loader;

import com.xinchentechnote.fix.ir.FixSchema;
import com.xinchentechnote.fix.utils.Jsons;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.InputStream;

public class XmlLoader {

  public FixSchema load(InputStream in) throws Exception {
    FixSchema schema = new FixSchema();

    JAXBContext jaxb =
        JAXBContext.newInstance(
            FixXml.class, Fields.class, Field.class, Messages.class, Message.class);
    Unmarshaller un = jaxb.createUnmarshaller();
    FixXml xml = (FixXml) un.unmarshal(in);
    System.out.println(Jsons.MAPPER.writeValueAsString(xml));
    return schema;
  }
}
