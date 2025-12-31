mvn clean spotless:apply package
mvn exec:java \
  -Dexec.mainClass=com.xinchentechnote.fix.cli.FixSchemaCli \
  -Dexec.args="generate --xml src/test/resources/FIX44.xml --lang java --out ../quickfixj-lab/fix-codec/src/main/java/ --package com.xinchentechnote.fix.codec"
