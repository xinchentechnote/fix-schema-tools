mvn clean package
mvn exec:java \
  -Dexec.mainClass=com.xinchentechnote.fix.cli.FixSchemaCli \
  -Dexec.args="generate --schema quickfixj --lang java --target codec --out ./gen"
