package com.dianping.cat.home.dependency.graph.transform;

import static com.dianping.cat.home.dependency.graph.Constants.ATTR_DASHED;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_DES;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_ID;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_KEY;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_LINK;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_OPPOSITE;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_SELF;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_STATUS;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_TARGET;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_TYPE;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_WEIGHT;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_EDGES;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_NODES;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dianping.cat.home.dependency.graph.IEntity;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public class DefaultJsonParser {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private List<Object> m_entities = new ArrayList<Object>();

   private Class<?> m_entityClass;

   private DefaultJsonParser(Class<?> entityClass) {
      m_entityClass = entityClass;
   }

   public static <T extends IEntity<T>> T parse(Class<T> entityClass, InputStream in) throws IOException {
      return parse(entityClass, new InputStreamReader(in, "utf-8"));
   }

   @SuppressWarnings("unchecked")
      public static <T extends IEntity<T>> T parse(Class<T> entityClass, Reader reader) throws IOException {
      DefaultJsonParser parser = new DefaultJsonParser(entityClass);

      parser.onArrayBegin();
      parser.parse(new JsonReader(reader));
      parser.onArrayEnd();

      if (parser.m_entities.isEmpty()) {
         return null;
      } else {
         return (T) parser.m_entities.get(0);
      }
   }

   public static <T extends IEntity<T>> T parse(Class<T> entityClass, String json) throws IOException {
      return parse(entityClass, new StringReader(json));
   }

   public static <T extends IEntity<T>> List<T> parseArray(Class<T> entityClass, InputStream in) throws Exception {
      return parseArray(entityClass, new InputStreamReader(in, "utf-8"));
   }

   @SuppressWarnings("unchecked")
   public static <T extends IEntity<T>> List<T> parseArray(Class<T> entityClass, Reader reader) throws Exception {
      DefaultJsonParser parser = new DefaultJsonParser(entityClass);

      parser.parse(new JsonReader(reader));
      return (List<T>) (List<?>) parser.m_entities;
   }

   public static <T extends IEntity<T>> List<T> parseArray(Class<T> entityClass, String json) throws Exception {
      return parseArray(entityClass, new StringReader(json));
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

   private Object createRootEntity() {
      try {
         Object entity = m_entityClass.newInstance();

         return entity;
      } catch (Exception e) {
         throw new RuntimeException(String.format("Unable to create entity(%s) instance!", m_entityClass.getName()), e);
      }
   }

   private boolean isTopLevel() {
      return m_objs.size() == 1;
   }

   protected void onArrayBegin() {
      if (m_objs.isEmpty()) {
         m_objs.push(m_entities);
         m_tags.push("");
      }   }

   protected void onArrayEnd() {
      m_objs.pop();
      m_tags.pop();

   }
   protected void onName(String name) {
      m_tags.push(name);
   }

   protected void onObjectBegin() {
      if (isTopLevel()) {
         m_objs.push(createRootEntity());
         m_tags.push("");
      } else {
         Object parent = m_objs.peek();
         String tag = m_tags.peek();

         if (parent instanceof TopologyGraph) {
            if (ENTITY_NODES.equals(tag) || ENTITY_EDGES.equals(tag)) {
               m_objs.push(parent);
            } else {
               String parentTag = m_tags.size() >= 2 ? m_tags.get(m_tags.size() - 2) : null;

               if (ENTITY_NODES.equals(parentTag)) {
                  TopologyNode nodes = new TopologyNode();

                  m_linker.onTopologyNode((TopologyGraph) parent, nodes);
                  m_objs.push(nodes);
               } else if (ENTITY_EDGES.equals(parentTag)) {
                  TopologyEdge edges = new TopologyEdge();

                  m_linker.onTopologyEdge((TopologyGraph) parent, edges);
                  m_objs.push(edges);
               } else {
                  throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
               }
            }
         } else {
            throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
         }
      }
   }

   protected void onObjectEnd() {
      m_tags.pop();

      Object entity = m_objs.pop();

      if (isTopLevel()) {
         m_entities.add(entity);
      }
   }

   protected void onValue(String value) {
      Object parent = m_objs.peek();
      String tag = m_tags.pop();

      if (parent instanceof TopologyGraph) {
         parseForTopologyGraph((TopologyGraph) parent, tag, value);
      } else if (parent instanceof TopologyNode) {
         parseForTopologyNode((TopologyNode) parent, tag, value);
      } else if (parent instanceof TopologyEdge) {
         parseForTopologyEdge((TopologyEdge) parent, tag, value);
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) under %s!", tag, parent));
      }
   }

   private void parse(JsonReader reader) throws IOException {
      try {
         reader.parse(this);
      } catch (EOFException e) {
         if (m_objs.size() > 1) {
            throw new EOFException(String.format("Unexpected end while parsing json! tags: %s.", m_tags));
         }
      }

      m_linker.finish();
   }

   public void parseForTopologyEdge(TopologyEdge topologyEdge, String tag, String value) {
      if (ATTR_KEY.equals(tag)) {
         topologyEdge.setKey(value);
      } else if (ATTR_TYPE.equals(tag)) {
         topologyEdge.setType(value);
      } else if (ATTR_TARGET.equals(tag)) {
         topologyEdge.setTarget(value);
      } else if (ATTR_OPPOSITE.equals(tag)) {
         topologyEdge.setOpposite(convert(Boolean.class, value, false));
      } else if (ATTR_WEIGHT.equals(tag)) {
         topologyEdge.setWeight(convert(Integer.class, value, 0));
      } else if (ATTR_STATUS.equals(tag)) {
         topologyEdge.setStatus(convert(Integer.class, value, 0));
      } else if (ATTR_DES.equals(tag)) {
         topologyEdge.setDes(value);
      } else if (ATTR_LINK.equals(tag)) {
         topologyEdge.setLink(value);
      } else if (ATTR_SELF.equals(tag)) {
         topologyEdge.setSelf(value);
      } else if (ATTR_DASHED.equals(tag)) {
         topologyEdge.setDashed(convert(Boolean.class, value, false));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, topologyEdge, m_tags));
      }
   }

   public void parseForTopologyGraph(TopologyGraph topologyGraph, String tag, String value) {
      if (ENTITY_NODES.equals(tag) || ENTITY_EDGES.equals(tag)) {
         // do nothing here
      } else if (ATTR_ID.equals(tag)) {
         topologyGraph.setId(value);
      } else if (ATTR_TYPE.equals(tag)) {
         topologyGraph.setType(value);
      } else if (ATTR_STATUS.equals(tag)) {
         topologyGraph.setStatus(convert(Integer.class, value, 0));
      } else if (ATTR_DES.equals(tag)) {
         topologyGraph.setDes(value);
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, topologyGraph, m_tags));
      }
   }

   public void parseForTopologyNode(TopologyNode topologyNode, String tag, String value) {
      if (ATTR_ID.equals(tag)) {
         topologyNode.setId(value);
      } else if (ATTR_TYPE.equals(tag)) {
         topologyNode.setType(value);
      } else if (ATTR_STATUS.equals(tag)) {
         topologyNode.setStatus(convert(Integer.class, value, 0));
      } else if (ATTR_DES.equals(tag)) {
         topologyNode.setDes(value);
      } else if (ATTR_LINK.equals(tag)) {
         topologyNode.setLink(value);
      } else if (ATTR_WEIGHT.equals(tag)) {
         topologyNode.setWeight(convert(Integer.class, value, 0));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, topologyNode, m_tags));
      }
   }


   static class JsonReader {
      private Reader m_reader;

      private char[] m_buffer = new char[2048];

      private int m_size;

      private int m_index;

      public JsonReader(Reader reader) {
         m_reader = reader;
      }

      private char next() throws IOException {
         if (m_index >= m_size) {
            m_size = m_reader.read(m_buffer);
            m_index = 0;

            if (m_size == -1) {
               throw new EOFException();
            }
         }

         return m_buffer[m_index++];
      }

      public void parse(DefaultJsonParser parser) throws IOException {
         StringBuilder sb = new StringBuilder();
         boolean flag = false;

         while (true) {
            char ch = next();

            switch (ch) {
            case ' ':
            case '\t':
            case '\r':
            case '\n':
               break;
            case '{':
               parser.onObjectBegin();
               flag = false;
               break;
            case '}':
               if (flag) { // have value
                  parser.onValue(sb.toString());
                  sb.setLength(0);
               }

               parser.onObjectEnd();
               flag = false;
               break;
            case '\'':
            case '"':
               while (true) {
                  char ch2 = next();

                  if (ch2 != ch) {
                     if (ch2 == '\\') {
                        char ch3 = next();

                        switch (ch3) {
                        case 't':
                        	sb.append('\t');
                        	break;
                        case 'r':
                        	sb.append('\r');
                        	break;
                        case 'n':
                        	sb.append('\n');
                        	break;
                        default:
                        	sb.append(ch3);
                        	break;
                        }
                     } else {
                        sb.append(ch2);
                     }
                  } else {
                     if (!flag) {
                        parser.onName(sb.toString());
                     } else {
                        parser.onValue(sb.toString());
                        flag = false;
                     }

                     sb.setLength(0);
                     break;
                  }
               }

               break;
            case ':':
               if (sb.length() != 0) {
                  parser.onName(sb.toString());
                  sb.setLength(0);
               }

               flag = true;
               break;
            case ',':
               if (sb.length() != 0) {
                  if (!flag) {
                     parser.onName(sb.toString());
                  } else {
                     parser.onValue(sb.toString());
                  }

                  sb.setLength(0);
               }

               flag = false;
               break;
            case '[':
               parser.onArrayBegin();
               flag = false;
               break;
            case ']':
               parser.onArrayEnd();
               flag = false;
               break;
            default:
               sb.append(ch);
               break;
            }
         }
      }
   }
}
