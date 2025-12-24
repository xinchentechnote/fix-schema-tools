package com.xinchentechnote.fix.loader;

import static org.junit.Assert.assertNotNull;

import com.xinchentechnote.fix.ir.FixSchema;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

public class XmlLoaderTest {
  @Test
  public void testLoadByContent() throws IOException {
    XmlLoader loader = new XmlLoader();
    InputStream in = getClass().getClassLoader().getResourceAsStream("fix-mini.xml");

    assertNotNull(in);
    FixSchema schema = loader.load(in);
    assertNotNull(schema);
  }
}
