package com.dianping.cat.home.dependency.graph.transform;

import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_EDGE;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_GRAPH;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_NODE;

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

import com.dianping.cat.home.dependency.graph.IEntity;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static TopologyGraph parse(InputSource is) throws SAXException, IOException {
      return parseEntity(TopologyGraph.class, is);
   }

   public static TopologyGraph parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static TopologyGraph parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static TopologyGraph parse(String xml) throws SAXException, IOException {
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

   private void parseForTopologyEdge(TopologyEdge parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForTopologyGraph(TopologyGraph parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_TOPOLOGY_NODE.equals(qName)) {
         TopologyNode topologyNode = m_maker.buildTopologyNode(attributes);

         m_linker.onTopologyNode(parentObj, topologyNode);
         m_objs.push(topologyNode);
      } else if (ENTITY_TOPOLOGY_EDGE.equals(qName)) {
         TopologyEdge topologyEdge = m_maker.buildTopologyEdge(attributes);

         m_linker.onTopologyEdge(parentObj, topologyEdge);
         m_objs.push(topologyEdge);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under topology-graph!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForTopologyNode(TopologyNode parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_TOPOLOGY_GRAPH.equals(qName)) {
         TopologyGraph topologyGraph = m_maker.buildTopologyGraph(attributes);

         m_entity = topologyGraph;
         m_objs.push(topologyGraph);
         m_tags.push(qName);
      } else if (ENTITY_TOPOLOGY_NODE.equals(qName)) {
         TopologyNode topologyNode = m_maker.buildTopologyNode(attributes);

         m_entity = topologyNode;
         m_objs.push(topologyNode);
         m_tags.push(qName);
      } else if (ENTITY_TOPOLOGY_EDGE.equals(qName)) {
         TopologyEdge topologyEdge = m_maker.buildTopologyEdge(attributes);

         m_entity = topologyEdge;
         m_objs.push(topologyEdge);
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

            if (parent instanceof TopologyGraph) {
               parseForTopologyGraph((TopologyGraph) parent, tag, qName, attributes);
            } else if (parent instanceof TopologyNode) {
               parseForTopologyNode((TopologyNode) parent, tag, qName, attributes);
            } else if (parent instanceof TopologyEdge) {
               parseForTopologyEdge((TopologyEdge) parent, tag, qName, attributes);
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
