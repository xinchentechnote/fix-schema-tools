package com.xinchentechnote.fix.gen;

import static org.junit.Assert.*;

import com.xinchentechnote.fix.parser.*;
import java.util.List;
import org.junit.Test;

public class JavaFixJsonCodecGeneratorTest {

  @Test
  public void encodeMessage() throws Exception {
    JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
    FixXmlDomParser parser = new FixXmlDomParser();
    FixSchema fix = parser.parse("src/test/resources/fix-mini.xml");
    MessageDef header = fix.getHeader();
    List<String> codes = generator.encodeMessage(MsgType.HEADER, "logon", header);
    assertEquals(3, codes.size());
    List<String> expectedLines =
        List.of(
            "logonNode.put(\"BeginString\", header.getString(BeginString.FIELD));",
            "logonNode.put(\"BodyLength\", header.getInt(BodyLength.FIELD));",
            "logonNode.put(\"MsgType\", header.getString(MsgType.FIELD));");
    assertEquals(expectedLines, codes);
  }

  @Test
  public void encodeFieldEntry() {
    JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
    FieldEntry entry = FieldEntry.build("Account", true, 1, FixType.STRING);
    List<String> codes = generator.encodeEntry(MsgType.BODY, "logon", entry);
    assertEquals(1, codes.size());
    assertEquals("logonNode.put(\"Account\", logon.getString(Account.FIELD));", codes.get(0));

    codes = generator.encodeEntry(MsgType.HEADER, "logon", entry);
    assertEquals(1, codes.size());
    assertEquals("logonNode.put(\"Account\", header.getString(Account.FIELD));", codes.get(0));

    entry.setRequired(false);
    codes = generator.encodeEntry(MsgType.HEADER, "logon", entry);
    assertEquals(3, codes.size());
    assertEquals("  logonNode.put(\"Account\", header.getString(Account.FIELD));", codes.get(1));
  }

  @Test
  public void encodeComponentEntry() {
    JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
    ComponentEntry entry = ComponentEntry.build("Instrument", true);
    entry.setDef(
        ComponentDef.build(
            "Instrument", List.of(FieldEntry.build("Account", true, 1, FixType.STRING))));
    List<String> codes = generator.encodeEntry(MsgType.BODY, "newOrderSingle", entry);
    System.out.println(String.join("\n", codes));
    assertEquals(4, codes.size());
  }

  @Test
  public void encodeGroupEntry() {
    JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
    GroupEntry entry =
        GroupEntry.build(
            "NoAllocs", true, List.of(FieldEntry.build("AllocAccount", true, 1, FixType.STRING)));
    List<String> codes = generator.encodeEntry(MsgType.BODY, "newOrderSingle", entry);
    System.out.println(String.join("\n", codes));
    assertEquals(9, codes.size());
  }
}
