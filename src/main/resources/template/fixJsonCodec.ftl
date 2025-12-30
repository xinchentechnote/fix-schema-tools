package ${packageName};

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.field.*;
import quickfix.fix44.*;

public class ${messageName}Codec implements FixJsonCodec<${messageName}> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(${messageName} message) throws Exception {
    ObjectNode root = MAPPER.createObjectNode();
    ${encodeCode}
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
  }

  @Override
  public Logon decode(String jsonString) throws Exception {
    JsonNode root = MAPPER.readTree(jsonString);
    ${decodeCode}
    return logon;
  }

}
