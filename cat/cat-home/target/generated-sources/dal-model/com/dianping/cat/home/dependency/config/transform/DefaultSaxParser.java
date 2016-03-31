package com.dianping.cat.home.dependency.config.transform;

import static com.dianping.cat.home.dependency.config.Constants.ENTITY_DOMAIN_CONFIG;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_EDGE_CONFIG;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_NODE_CONFIG;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_TOPOLOGY_GRAPH_CONFIG;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dianping.cat.home.dependency.config.IEntity;
import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static TopologyGraphConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(TopologyGraphConfig.class, is);
   }

   public static TopologyGraphConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static TopologyGraphConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static TopologyGraphConfig parse(String xml) throws SAXException, IOException {
      return parse(new InputSource(new StringReader(xml)));
   }

   public static <T extends IEntity<?>> T parseEntity(Class<T> type, String xml) throws SAXException, IOException {
      return parseEntity(type, new InputSource(new StringReader(xml)));
   }

   @SuppressWarnings("unchecked")
   public static <T extends IEntity<?>> T parseEntity(Class<T> type, InputSource is) throws SAXException, IOException {
      try {
         DefaultSaxParser handler = new DefaultSaxParser();
         SAXParserFactory factory = SAXParserFactory.newInstance();

         factory.setValidating(false);
         factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
         factory.setFeature("http://xml.org/sax/features/validation", false);

         factory.newSAXParser().parse(is, handler);
         return (T) handler.getEntity();
      } catch (ParserConfigurationException e) {
         throw new IllegalStateException("Unable to get SAX parser instance!", e);
      }
   }


   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null || value.length() == 0) {
         return defaultValue;
      }

      if (type == Boolean.class) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }

   @Override
   public void characters(char[] ch, int start, int length) throws SAXException {
      m_text.append(ch, start, length);
   }

   @Override
   public void endDocument() throws SAXException {
      m_linker.finish();
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (uri == null || uri.length() == 0) {
         m_objs.pop();
         m_tags.pop();

      }

      m_text.setLength(0);
   }

   private IEntity<?> getEntity() {
      return m_entity;
   }

   protected String getText() {
      return m_text.toString();
   }

   private void parseForDomainConfig(DomainConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForEdgeConfig(EdgeConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForNodeConfig(NodeConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DOMAIN_CONFIG.equals(qName)) {
         DomainConfig domainConfig = m_maker.buildDomainConfig(attributes);

         m_linker.onDomainConfig(parentObj, domainConfig);
         m_objs.push(domainConfig);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under node-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForTopologyGraphConfig(TopologyGraphConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_NODE_CONFIG.equals(qName)) {
         NodeConfig nodeConfig = m_maker.buildNodeConfig(attributes);

         m_linker.onNodeConfig(parentObj, nodeConfig);
         m_objs.push(nodeConfig);
      } else if (ENTITY_EDGE_CONFIG.equals(qName)) {
         EdgeConfig edgeConfig = m_maker.buildEdgeConfig(attributes);

         m_linker.onEdgeConfig(parentObj, edgeConfig);
         m_objs.push(edgeConfig);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under topology-graph-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_TOPOLOGY_GRAPH_CONFIG.equals(qName)) {
         TopologyGraphConfig topologyGraphConfig = m_maker.buildTopologyGraphConfig(attributes);

         m_entity = topologyGraphConfig;
         m_objs.push(topologyGraphConfig);
         m_tags.push(qName);
      } else if (ENTITY_NODE_CONFIG.equals(qName)) {
         NodeConfig nodeConfig = m_maker.buildNodeConfig(attributes);

         m_entity = nodeConfig;
         m_objs.push(nodeConfig);
         m_tags.push(qName);
      } else if (ENTITY_DOMAIN_CONFIG.equals(qName)) {
         DomainConfig domainConfig = m_maker.buildDomainConfig(attributes);

         m_entity = domainConfig;
         m_objs.push(domainConfig);
         m_tags.push(qName);
      } else if (ENTITY_EDGE_CONFIG.equals(qName)) {
         EdgeConfig edgeConfig = m_maker.buildEdgeConfig(attributes);

         m_entity = edgeConfig;
         m_objs.push(edgeConfig);
         m_tags.push(qName);
      } else {
         throw new SAXException("Unknown root element(" + qName + ") found!");
      }
   }

   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if (uri == null || uri.length() == 0) {
         if (m_objs.isEmpty()) { // root
            parseRoot(qName, attributes);
         } else {
            Object parent = m_objs.peek();
            String tag = m_tags.peek();

            if (parent instanceof TopologyGraphConfig) {
               parseForTopologyGraphConfig((TopologyGraphConfig) parent, tag, qName, attributes);
            } else if (parent instanceof NodeConfig) {
               parseForNodeConfig((NodeConfig) parent, tag, qName, attributes);
            } else if (parent instanceof DomainConfig) {
               parseForDomainConfig((DomainConfig) parent, tag, qName, attributes);
            } else if (parent instanceof EdgeConfig) {
               parseForEdgeConfig((EdgeConfig) parent, tag, qName, attributes);
            } else {
               throw new RuntimeException(String.format("Unknown entity(%s) under %s!", qName, parent.getClass().getName()));
            }
         }

         m_text.setLength(0);
        } else {
         throw new SAXException(String.format("Namespace(%s) is not supported by %s.", uri, this.getClass().getName()));
      }
   }
}
