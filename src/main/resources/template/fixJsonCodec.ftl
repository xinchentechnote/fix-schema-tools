package ${packageName};

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import quickfix.FieldMap;
import quickfix.Group;
import quickfix.Message;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.*;

public class ${messageName}Codec implements FixJsonCodec<JsonNode,${messageName}> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public JsonNode encode(${messageName} ${messageName?uncap_first}) throws Exception {
    ${encodeCode}
    return ${messageName?uncap_first}Node;
  }

  @Override
  public ${messageName} decode(JsonNode ${messageName?uncap_first}Node) throws Exception {
    ${decodeCode}
    return ${messageName?uncap_first};
  }

}
