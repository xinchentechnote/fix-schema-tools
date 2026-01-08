package com.xinchentechnote.fix.parser.v2;

import static org.junit.Assert.*;

import org.junit.Test;

public class FixXmlDomParserTest {

  @Test
  public void parse() throws Exception {
    FixXmlDomParser parser = new FixXmlDomParser();
    FixSchema fixSchema = parser.parse("src/test/resources/fix-mini.xml");
    assertEquals(14, fixSchema.getFields().size());
    FieldDef fieldDef = fixSchema.getFields().get("Account");
    assertEquals(1, fieldDef.getNumber());
    assertEquals("Account", fieldDef.getName());
    assertEquals(FixType.STRING, fieldDef.getType());
    assertEquals(1, fixSchema.getMessages().size());
    MessageDef messageDef = fixSchema.getMessages().get("NewOrderSingle");
    assertEquals("NewOrderSingle", messageDef.getName());
    assertEquals("D", messageDef.getMsgType());
    assertEquals(5, messageDef.getEntries().size());
    assertEquals(0, fixSchema.getComponents().size());
  }
}
