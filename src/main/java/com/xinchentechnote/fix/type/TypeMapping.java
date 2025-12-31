package com.xinchentechnote.fix.type;

// Interface for mapping FIX types to Java or other language types
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
public interface TypeMapping {

  TypeInfo getType(String fixType);
}
