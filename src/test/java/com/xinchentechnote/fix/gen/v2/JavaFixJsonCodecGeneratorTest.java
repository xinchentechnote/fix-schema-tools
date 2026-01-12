package com.xinchentechnote.fix.gen.v2;

import com.xinchentechnote.fix.gen.MsgType;
import com.xinchentechnote.fix.parser.FixType;
import com.xinchentechnote.fix.parser.v2.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class JavaFixJsonCodecGeneratorTest {


    @Test
    public void encodeMessage() throws Exception {
        JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
        FixXmlDomParser parser = new FixXmlDomParser();
        FixSchema fix = parser.parse("src/test/resources/fix-mini.xml");
        MessageDef header = fix.getHeader();
        List<String> codes = generator.encodeMessage(MsgType.HEADER,  header);
        assertEquals(3, codes.size());
        List<String> expectedLines = List.of(
                "headerNode.put(\"BeginString\", header.getHeader().getString(BeginString.FIELD));",
                "headerNode.put(\"BodyLength\", header.getHeader().getInt(BodyLength.FIELD));",
                "headerNode.put(\"MsgType\", header.getHeader().getString(MsgType.FIELD));"
        );
        assertEquals(expectedLines, codes);
    }

    @Test
    public void encodeFieldEntry() {
        JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
        FieldEntry entry = FieldEntry.build("Account", true,1, FixType.STRING);
        List<String> codes = generator.encodeEntry(MsgType.BODY, "logon", entry);
        assertEquals(1, codes.size());
        assertEquals("logonNode.put(\"Account\", logon.getString(Account.FIELD));", codes.get(0));

        codes = generator.encodeEntry(MsgType.HEADER, "logon", entry);
        assertEquals(1, codes.size());
        assertEquals("logonNode.put(\"Account\", logon.getHeader().getString(Account.FIELD));", codes.get(0));

        entry.setRequired(false);
        codes = generator.encodeEntry(MsgType.HEADER, "logon", entry);
        assertEquals(3, codes.size());
        assertEquals("  logonNode.put(\"Account\", logon.getHeader().getString(Account.FIELD));", codes.get(1));
    }

    @Test
    public void encodeComponentEntry() {
        JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
        ComponentEntry entry = ComponentEntry.build("Instrument", true);
        entry.setDef(ComponentDef.build("Instrument",List.of(FieldEntry.build("Account", true, 1, FixType.STRING))));
        List<String> codes = generator.encodeEntry(MsgType.BODY, "newOrderSingle", entry);
        System.out.println(String.join("\n", codes));
        assertEquals(4, codes.size());
    }

    @Test
    public void encodeGroupEntry() {
        JavaFixJsonCodecGenerator generator = new JavaFixJsonCodecGenerator();
        GroupEntry entry = GroupEntry.build("NoAllocs", true, List.of(
                FieldEntry.build("AllocAccount", true, 1, FixType.STRING)));
        List<String> codes = generator.encodeEntry(MsgType.BODY, "newOrderSingle", entry);
        System.out.println(String.join("\n", codes));
        assertEquals(9, codes.size());
    }
}