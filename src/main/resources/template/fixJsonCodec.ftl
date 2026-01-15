package ${packageName};

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import quickfix.Group;
import quickfix.Message;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.*;

public class ${messageName}Codec implements FixJsonCodec<${messageName}> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(${messageName} ${messageName?uncap_first}) throws Exception {
    ${encodeCode}
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(${messageName?uncap_first}Node);
  }

  @Override
  public ${messageName} decode(String jsonString) throws Exception {
    JsonNode ${messageName?uncap_first}Node = MAPPER.readTree(jsonString);
    ${decodeCode}
    return ${messageName?uncap_first};
  }

}
