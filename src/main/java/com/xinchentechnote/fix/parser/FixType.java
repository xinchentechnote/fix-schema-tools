package com.xinchentechnote.fix.parser;

import java.util.Set;

public enum FixType {
  AMT,
  BOOLEAN,
  CHAR,
  COUNTRY,
  CURRENCY,
  DATA,
  EXCHANGE,
  FLOAT,
  INT,
  LENGTH,
  LOCALMKTDATE,
  MONTHYEAR,
  MULTIPLEVALUESTRING,
  NUMINGROUP,
  PERCENTAGE,
  PRICE,
  PRICEOFFSET,
  QTY,
  SEQNUM,
  STRING,
  UTCDATEONLY,
  UTCTIMEONLY,
  UTCTIMESTAMP;
  public static final Set<String> UTC_TYPES =
      Set.of(UTCTIMESTAMP.name(), UTCDATEONLY.name(), UTCTIMEONLY.name());

  public static boolean isUtc(String type) {
    return UTC_TYPES.contains(type);
  }
}
