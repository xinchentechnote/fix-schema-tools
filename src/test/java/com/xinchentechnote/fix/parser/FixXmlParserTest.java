package com.xinchentechnote.fix.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;
import org.junit.Test;

public class FixXmlParserTest {

  @Test
  public void testLoadFix() throws Exception {
    FixXmlParser loader = new FixXmlParser();
    InputStream in = getClass().getClassLoader().getResourceAsStream("fix-mini.xml");

    assertNotNull(in);
    Fix fix = loader.loadFix(in);
    assertNotNull(fix);
    assertEquals("4", fix.getMajor());
    assertEquals("4", fix.getMinor());

    assertNotNull(fix.getHeader());
    assertNotNull(fix.getTrailer());
    assertEquals(9, fix.getFields().getFields().size());
    FieldDef field = fix.getFields().getFields().get(0);
    assertEquals(1, field.getNumber());
    assertEquals("Account", field.getName());
    assertEquals("STRING", field.getType());
    assertEquals(1, fix.getMessages().getMessages().size());
    Message message = fix.getMessages().getMessages().get(0);
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
}
