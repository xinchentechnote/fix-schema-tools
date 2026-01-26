package com.xinchentechnote.fix.parser;

public interface FixXmlParser {
  FixSchema parse(String xml) throws Exception;

  FixSchema parseFromXml(String xml) throws Exception;
}
