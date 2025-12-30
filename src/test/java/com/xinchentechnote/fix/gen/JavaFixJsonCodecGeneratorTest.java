package com.xinchentechnote.fix.gen;

import static org.junit.Assert.*;

import com.xinchentechnote.fix.parser.BaseMessage;
import com.xinchentechnote.fix.parser.Fix;
import com.xinchentechnote.fix.parser.FixXmlParser;
import java.io.InputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class JavaFixJsonCodecGeneratorTest {

  JavaFixJsonCodecGenerator generator;
  Fix fix;

  @Before
  public void setUp() throws Exception {
    generator = new JavaFixJsonCodecGenerator();
    FixXmlParser loader = new FixXmlParser();
    InputStream in = getClass().getClassLoader().getResourceAsStream("fix-mini.xml");
    fix = loader.loadFix(in);
  }

  @Test
  public void encodeMessage() {
    BaseMessage header = fix.getHeader();
    List<String> codes = generator.encodeMessage(MsgType.HEADER, "logon", fix.getHeader());
    assertEquals(3, codes.size());
    codes.forEach(System.out::println);
  }
}
