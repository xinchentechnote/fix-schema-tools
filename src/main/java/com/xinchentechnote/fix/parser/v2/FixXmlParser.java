package com.xinchentechnote.fix.parser.v2;

public interface FixXmlParser {
  FixSchema parse(String xml) throws Exception;
}
