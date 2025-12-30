package com.xinchentechnote.fix.utils;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

public class StringTemplateHelperTest {
  @Test
  public void testRender1() {
    String result = StringTemplateHelper.render("${name}:${age}{}", new User("Tom", 18, "Bejing"));
    assertEquals("Tom:18{}", result);
  }

  @Test
  public void testRender2() {
    assertEquals(
        "Logon logon = new Logon();",
        StringTemplateHelper.render(
            "${name} ${instanceName} = new ${name}();",
            Map.of("name", "Logon", "instanceName", "logon")));
  }

  @Data
  @AllArgsConstructor
  public static class User {
    private String name;
    private int age;
    private String address;
  }
}
