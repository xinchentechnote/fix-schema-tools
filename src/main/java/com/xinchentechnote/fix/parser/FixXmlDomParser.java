package com.xinchentechnote.fix.parser;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FixXmlDomParser implements FixXmlParser {

  public static FixSchema load(String path) throws Exception {
    FixXmlDomParser parser = new FixXmlDomParser();
    return parser.parse(path);
  }

  public static FixSchema loadXml(String xmlContent) throws Exception {
    FixXmlDomParser parser = new FixXmlDomParser();
    return parser.parseFromXml(xmlContent);
  }

  @Override
  public FixSchema parseFromXml(String xml) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setIgnoringComments(true);
    factory.setNamespaceAware(false);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new InputSource(new StringReader(xml)));
    Element root = doc.getDocumentElement();
    return parse(root);
  }

  @Override
  public FixSchema parse(String path) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setIgnoringComments(true);
    factory.setNamespaceAware(false);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new File(path));
    Element root = doc.getDocumentElement();
    return parse(root);
  }

  private FixSchema parse(Element root) {
    FixSchema schema = new FixSchema();
    schema.setVersion(parseVersion(root));
    schema.setHeader(parseHeader(root));
    schema.setTrailer(parseTrailer(root));
    schema.setFields(parseFields(root));
    schema.setComponents(parseComponents(root));
    schema.setMessages(parseMessages(root));
    schema.postProcess();
    return schema;
  }

  private String parseVersion(Element root) {
    String major = root.getAttribute("major");
    String minor = root.getAttribute("minor");
    return major + "." + minor;
  }

  private MessageDef parseHeader(Element root) {
    Element header = getFirstChild(root, "header");
    MessageDef messageDef = new MessageDef();
    messageDef.setName("header");
    messageDef.setMsgType("header");
    messageDef.setEntries(parseEntries(header));
    return messageDef;
  }

  private MessageDef parseTrailer(Element root) {
    Element trailer = getFirstChild(root, "trailer");
    MessageDef messageDef = new MessageDef();
    messageDef.setName("trailer");
    messageDef.setMsgType("trailer");
    messageDef.setEntries(parseEntries(trailer));
    return messageDef;
  }

  private Map<String, MessageDef> parseMessages(Element root) {
    Element messages = getFirstChild(root, "messages");
    NodeList messageList = messages.getElementsByTagName("message");
    Map<String, MessageDef> map = new HashMap<>();
    for (int i = 0; i < messageList.getLength(); i++) {
      if (messageList.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      Element item = ((Element) messageList.item(i));
      String name = item.getAttribute("name");
      String msgType = item.getAttribute("msgtype");
      MessageDef messageDef = new MessageDef();
      messageDef.setName(name);
      messageDef.setMsgType(msgType);
      messageDef.setEntries(parseEntries(item));
      map.put(name, messageDef);
    }
    return map;
  }

  private List<Entry> parseEntries(Element item) {
    NodeList childNodes = item.getChildNodes();
    List<Entry> entries = new ArrayList<>();
    for (int i = 0; i < childNodes.getLength(); i++) {
      if (childNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      Element child = (Element) childNodes.item(i);
      switch (child.getTagName()) {
        case "field":
          entries.add(parseFieldEntry(child));
          break;
        case "component":
          entries.add(parseComponentEntry(child));
          break;
        case "group":
          entries.add(parseGroupEntry(child));
          break;
        default:
          {
            throw new IllegalStateException("Unknown entry type: " + child.getTagName());
          }
      }
    }
    return entries;
  }

  private Entry parseFieldEntry(Element element) {
    FieldEntry fieldEntry = new FieldEntry();
    fieldEntry.setName(element.getAttribute("name"));
    fieldEntry.setRequired(element.getAttribute("required").equalsIgnoreCase("Y"));
    return fieldEntry;
  }

  private Entry parseComponentEntry(Element element) {
    ComponentEntry componentEntry = new ComponentEntry();
    componentEntry.setName(element.getAttribute("name"));
    componentEntry.setRequired(element.getAttribute("required").equalsIgnoreCase("Y"));
    return componentEntry;
  }

  private Entry parseGroupEntry(Element element) {
    String name = element.getAttribute("name");
    boolean required = element.getAttribute("required").equalsIgnoreCase("Y");
    return GroupEntry.build(name, required, parseEntries(element));
  }

  private Element getFirstChild(Element parent, String tagName) {
    NodeList children = parent.getChildNodes();

    for (int i = 0; i < children.getLength(); i++) {
      Node node = children.item(i);

      if (node.getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }

      Element el = (Element) node;
      if (tagName.equals(el.getTagName())) {
        return el;
      }
    }

    throw new IllegalStateException(
        "Missing <" + tagName + "> under <" + parent.getTagName() + ">");
  }

  private Map<String, ComponentDef> parseComponents(Element root) {
    Element components = getFirstChild(root, "components");
    NodeList componentList = components.getElementsByTagName("component");
    Map<String, ComponentDef> map = new HashMap<>();
    for (int i = 0; i < componentList.getLength(); i++) {
      if (componentList.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      Element item = ((Element) componentList.item(i));
      String name = item.getAttribute("name");
      ComponentDef componentDef = new ComponentDef();
      componentDef.setName(name);
      componentDef.setEntries(parseEntries(item));
      map.put(name, componentDef);
    }
    return map;
  }

  private Map<String, FieldDef> parseFields(Element root) {
    Element fields = getFirstChild(root, "fields");
    NodeList fieldList = fields.getElementsByTagName("field");
    Map<String, FieldDef> map = new HashMap<>();
    for (int i = 0; i < fieldList.getLength(); i++) {
      if (fieldList.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      Element item = ((Element) fieldList.item(i));
      String name = item.getAttribute("name");
      String type = item.getAttribute("type");
      int number = Integer.parseInt(item.getAttribute("number"));
      FieldDef fieldDef = new FieldDef();
      fieldDef.setName(name);
      fieldDef.setType(FixType.valueOf(type));
      fieldDef.setNumber(number);
      map.put(name, fieldDef);
    }
    return map;
  }
}
