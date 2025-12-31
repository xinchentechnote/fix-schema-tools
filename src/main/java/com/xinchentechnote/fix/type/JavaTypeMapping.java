package com.xinchentechnote.fix.type;

import java.util.HashMap;
import java.util.Map;

// AMT
// BOOLEAN
// CHAR
// COUNTRY
// CURRENCY
// DATA
// EXCHANGE
// FLOAT
// INT
// LENGTH
// LOCALMKTDATE
// MONTHYEAR
// MULTIPLEVALUESTRING
// NUMINGROUP
// PERCENTAGE
// PRICE
// PRICEOFFSET
// QTY
// SEQNUM
// STRING
// UTCDATEONLY
// UTCTIMEONLY
// UTCTIMESTAMP
public class JavaTypeMapping implements TypeMapping {
  private static Map<String, TypeInfo> MAP = new HashMap<>();

  static {
    MAP.put("AMT", new TypeInfo("AMT", "Double", "getDouble", ".asDouble()"));
    MAP.put("BOOLEAN", new TypeInfo("BOOLEAN", "Boolean", "getBoolean", ".asBoolean()"));
    MAP.put("CHAR", new TypeInfo("CHAR", "Character", "getChar", ".asChar()"));
    MAP.put("COUNTRY", new TypeInfo("COUNTRY", "String", "getString", ".asText()"));
    MAP.put("CURRENCY", new TypeInfo("CURRENCY", "String", "getString", ".asText()"));
    MAP.put("DATA", new TypeInfo("DATA", "byte[]", "getString", ".asText()"));
    MAP.put("EXCHANGE", new TypeInfo("EXCHANGE", "String", "getString", ".asText()"));
    MAP.put("FLOAT", new TypeInfo("FLOAT", "Double", "getDouble", ".asDouble()"));
    MAP.put("INT", new TypeInfo("INT", "Integer", "getInt", ".asInt()"));
    MAP.put("LENGTH", new TypeInfo("LENGTH", "Integer", "getInt", ".asInt()"));
    MAP.put(
        "LOCALMKTDATE",
        new TypeInfo("LOCALMKTDATE", "java.time.LocalDate", "getLocalDate", ".asLocalDate()"));
    MAP.put(
        "MONTHYEAR",
        new TypeInfo("MONTHYEAR", "java.time.YearMonth", "getYearMonth", ".asYearMonth()"));
    MAP.put(
        "MULTIPLEVALUESTRING",
        new TypeInfo("MULTIPLEVALUESTRING", "String", "getString", ".asText()"));
    MAP.put("NUMINGROUP", new TypeInfo("NUMINGROUP", "Integer", "getInt", ".asInt()"));
    MAP.put("PERCENTAGE", new TypeInfo("PERCENTAGE", "Double", "getDouble", ".asDouble()"));
    MAP.put("PRICE", new TypeInfo("PRICE", "Double", "getDouble", ".asDouble()"));
    MAP.put("PRICEOFFSET", new TypeInfo("PRICEOFFSET", "Double", "getDouble", ".asDouble()"));
    MAP.put("QTY", new TypeInfo("QTY", "Integer", "getInt", ".asInt()"));
    MAP.put("SEQNUM", new TypeInfo("SEQNUM", "Integer", "getInt", ".asInt()"));
    MAP.put("STRING", new TypeInfo("STRING", "String", "getString", ".asText()"));
    MAP.put(
        "UTCDATEONLY",
        new TypeInfo(
            "UTCDATEONLY", "LocalDate", "getUtcDateOnly", ".asLocalDate()", ".toString()"));
    MAP.put(
        "UTCTIMEONLY",
        new TypeInfo(
            "UTCTIMEONLY", "LocalTime", "getUtcTimeOnly", ".asLocalTime()", ".toString()"));
    MAP.put(
        "UTCTIMESTAMP",
        new TypeInfo("UTCTIMESTAMP", "Instant", "getUtcTimeStamp", ".asInstant()", ".toString()"));
  }

  @Override
  public TypeInfo getType(String fixType) {
    return MAP.get(fixType);
  }
}
