package com.xinchentechnote.fix.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;
import org.junit.Test;

public class XmlLoaderTest {

  @Test
  public void testLoadFix1() throws Exception {
    XmlLoader loader = new XmlLoader();
    InputStream in = getClass().getClassLoader().getResourceAsStream("fix-mini.xml");

    assertNotNull(in);
    FixXml xml = loader.loadFix(in);
    assertNotNull(xml);
    assertEquals("4", xml.getMajor());
    assertEquals("4", xml.getMinor());

    assertNotNull(xml.getHeader());
    assertNotNull(xml.getTrailer());
    assertEquals(3, xml.getFields().getFields().size());
    Field field = xml.getFields().getFields().get(0);
    assertEquals(8, field.getNumber());
    assertEquals("BeginString", field.getName());
    assertEquals("STRING", field.getType());
    assertEquals(1, xml.getMessages().getMessages().size());
    Message message = xml.getMessages().getMessages().get(0);
    assertEquals("NewOrderSingle", message.getName());
    assertEquals("D", message.getMsgtype());
    assertEquals("app", message.getMsgcat());
    assertEquals(3, message.getBaseFields().size());
    BaseField msgField = message.getBaseFields().get(0);
    assertEquals("ClOrdID", msgField.getName());
    assertEquals("Y", msgField.getRequired());
    List<CompositeField> componentFields = message.getComponentFields();
    assertEquals(1, componentFields.size());
    CompositeField componentField = componentFields.get(0);
    assertEquals("Parties", componentField.getName());
    assertEquals("N", componentField.getRequired());
    List<CompositeField> groupFields = message.getGroupFields();
    CompositeField groupField = groupFields.get(0);
    assertEquals("NoAllocs", groupField.getName());
    assertEquals("N", groupField.getRequired());
  }

  @Test
  public void testLoadFix2() throws Exception {
    XmlLoader loader = new XmlLoader();
    InputStream in = getClass().getClassLoader().getResourceAsStream("FIX44.xml");
    FixXml xml = loader.loadFix(in);
  }
}
