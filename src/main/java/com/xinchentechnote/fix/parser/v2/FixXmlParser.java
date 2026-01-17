package com.xinchentechnote.fix.parser.v2;

public interface FixXmlParser {
  FixSchema parse(String xml) throws Exception;

  FixSchema parseFromXml(String xml) throws Exception;
}
