package com.xinchentechnote.fix.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import org.junit.Test;

public class XmlLoaderTest {
  @Test
  public void testLoadByContent() throws Exception {
    XmlLoader loader = new XmlLoader();
    InputStream in = getClass().getClassLoader().getResourceAsStream("fix-mini.xml");

    assertNotNull(in);
    FixXml xml = loader.loadFix(in);
    assertNotNull(xml);
    assertEquals("4", xml.getMajor());
    assertEquals("4", xml.getMinor());

    assertNotNull(xml.getHeader());
    assertNotNull(xml.getTrailer());
    assertEquals(6, xml.getFields().getFields().size());
    Field field = xml.getFields().getFields().get(0);
    assertEquals(8, field.getNumber());
    assertEquals("BeginString", field.getName());
    assertEquals("STRING", field.getType());
    assertEquals(1, xml.getMessages().getMessages().size());
    Message message = xml.getMessages().getMessages().get(0);
    assertEquals("Logon", message.getName());
    assertEquals("A", message.getMsgtype());
    assertEquals("admin", message.getMsgcat());
    assertEquals(2, message.getFields().size());
    MsgField msgField = message.getFields().get(0);
    assertEquals("EncryptMethod", msgField.getName());
    assertEquals("Y", msgField.getRequired());
  }
}
