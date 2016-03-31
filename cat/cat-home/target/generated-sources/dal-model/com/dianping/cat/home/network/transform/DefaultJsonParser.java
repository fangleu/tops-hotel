package com.dianping.cat.home.network.transform;

import static com.dianping.cat.home.network.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.network.Constants.ATTR_FROM;
import static com.dianping.cat.home.network.Constants.ATTR_GROUP;
import static com.dianping.cat.home.network.Constants.ATTR_IN;
import static com.dianping.cat.home.network.Constants.ATTR_INDISCARDS;
import static com.dianping.cat.home.network.Constants.ATTR_INDISCARDSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_INERRORS;
import static com.dianping.cat.home.network.Constants.ATTR_INERRORSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_INSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_INSUM;
import static com.dianping.cat.home.network.Constants.ATTR_KEY;
import static com.dianping.cat.home.network.Constants.ATTR_MINUTE;
import static com.dianping.cat.home.network.Constants.ATTR_NAME;
import static com.dianping.cat.home.network.Constants.ATTR_OUT;
import static com.dianping.cat.home.network.Constants.ATTR_OUTDISCARDS;
import static com.dianping.cat.home.network.Constants.ATTR_OUTDISCARDSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_OUTERRORS;
import static com.dianping.cat.home.network.Constants.ATTR_OUTERRORSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_OUTSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_OUTSUM;
import static com.dianping.cat.home.network.Constants.ATTR_STATE;
import static com.dianping.cat.home.network.Constants.ATTR_TO;
import static com.dianping.cat.home.network.Constants.ATTR_X;
import static com.dianping.cat.home.network.Constants.ATTR_Y;
import static com.dianping.cat.home.network.Constants.ENTITY_ANCHORS;
import static com.dianping.cat.home.network.Constants.ENTITY_CONNECTIONS;
import static com.dianping.cat.home.network.Constants.ENTITY_INTERFACES;
import static com.dianping.cat.home.network.Constants.ENTITY_NETGRAPHS;
import static com.dianping.cat.home.network.Constants.ENTITY_NETTOPOLOGIES;
import static com.dianping.cat.home.network.Constants.ENTITY_SWITCHS;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dianping.cat.home.network.IEntity;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

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
      } else {
         Object parent = m_objs.peek();
         String tag = m_tags.peek();

         if (parent instanceof NetGraph) {
            if (ENTITY_NETTOPOLOGIES.equals(tag)) {
               m_objs.push(parent);
            } else {
               throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
            }
         } else if (parent instanceof NetTopology) {
            if (ENTITY_ANCHORS.equals(tag)) {
               m_objs.push(parent);
            } else if (ENTITY_SWITCHS.equals(tag)) {
               m_objs.push(parent);
            } else if (ENTITY_CONNECTIONS.equals(tag)) {
               m_objs.push(parent);
            } else {
               throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
            }
         } else if (parent instanceof Connection) {
            if (ENTITY_INTERFACES.equals(tag)) {
               m_objs.push(parent);
            } else {
               throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
            }
         } else {
            throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
         }
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

         if (parent instanceof NetGraphSet) {
            if (ENTITY_NETGRAPHS.equals(tag)) {
               m_objs.push(parent);
            } else {
               String parentTag = m_tags.size() >= 2 ? m_tags.get(m_tags.size() - 2) : null;

               if (ENTITY_NETGRAPHS.equals(parentTag)) {
                  NetGraph netGraphs = new NetGraph();

                  m_linker.onNetGraph((NetGraphSet) parent, netGraphs);
                  m_objs.push(netGraphs);
               } else {
                  throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
               }
            }
         } else if (parent instanceof NetGraph) {
            if (ENTITY_NETTOPOLOGIES.equals(tag)) {
               NetTopology netTopologies = new NetTopology();

               m_linker.onNetTopology((NetGraph) parent, netTopologies);
               m_objs.push(netTopologies);
               m_tags.push("");
            } else {
               throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags)); ///
            }
         } else if (parent instanceof NetTopology) {
            if (ENTITY_ANCHORS.equals(tag)) {
               Anchor anchors = new Anchor();

               m_linker.onAnchor((NetTopology) parent, anchors);
               m_objs.push(anchors);
               m_tags.push("");
            } else if (ENTITY_SWITCHS.equals(tag)) {
               Switch switchs = new Switch();

               m_linker.onSwitch((NetTopology) parent, switchs);
               m_objs.push(switchs);
               m_tags.push("");
            } else if (ENTITY_CONNECTIONS.equals(tag)) {
               Connection connections = new Connection();

               m_linker.onConnection((NetTopology) parent, connections);
               m_objs.push(connections);
               m_tags.push("");
            } else {
               throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags));
            }
         } else if (parent instanceof Connection) {
            if (ENTITY_INTERFACES.equals(tag)) {
               Interface interfaces = new Interface();

               m_linker.onInterface((Connection) parent, interfaces);
               m_objs.push(interfaces);
               m_tags.push("");
            } else {
               throw new RuntimeException(String.format("Unknown tag(%s) found at %s!", tag, m_tags)); ///
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

      if (parent instanceof NetGraphSet) {
         parseForNetGraphSet((NetGraphSet) parent, tag, value);
      } else if (parent instanceof NetGraph) {
         parseForNetGraph((NetGraph) parent, tag, value);
      } else if (parent instanceof NetTopology) {
         parseForNetTopology((NetTopology) parent, tag, value);
      } else if (parent instanceof Anchor) {
         parseForAnchor((Anchor) parent, tag, value);
      } else if (parent instanceof Switch) {
         parseForSwitch((Switch) parent, tag, value);
      } else if (parent instanceof Connection) {
         parseForConnection((Connection) parent, tag, value);
      } else if (parent instanceof Interface) {
         parseForInterface((Interface) parent, tag, value);
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

   public void parseForAnchor(Anchor anchor, String tag, String value) {
      if (ATTR_NAME.equals(tag)) {
         anchor.setName(value);
      } else if (ATTR_X.equals(tag)) {
         anchor.setX(convert(Integer.class, value, null));
      } else if (ATTR_Y.equals(tag)) {
         anchor.setY(convert(Integer.class, value, null));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, anchor, m_tags));
      }
   }

   public void parseForConnection(Connection connection, String tag, String value) {
      if (ENTITY_INTERFACES.equals(tag)) {
         // do nothing here
      } else if (ATTR_FROM.equals(tag)) {
         connection.setFrom(value);
      } else if (ATTR_TO.equals(tag)) {
         connection.setTo(value);
      } else if (ATTR_INSTATE.equals(tag)) {
         connection.setInstate(convert(Integer.class, value, null));
      } else if (ATTR_OUTSTATE.equals(tag)) {
         connection.setOutstate(convert(Integer.class, value, null));
      } else if (ATTR_INSUM.equals(tag)) {
         connection.setInsum(convert(Double.class, value, null));
      } else if (ATTR_OUTSUM.equals(tag)) {
         connection.setOutsum(convert(Double.class, value, null));
      } else if (ATTR_INDISCARDSSTATE.equals(tag)) {
         connection.setInDiscardsState(convert(Integer.class, value, null));
      } else if (ATTR_OUTDISCARDSSTATE.equals(tag)) {
         connection.setOutDiscardsState(convert(Integer.class, value, null));
      } else if (ATTR_INERRORSSTATE.equals(tag)) {
         connection.setInErrorsState(convert(Integer.class, value, null));
      } else if (ATTR_OUTERRORSSTATE.equals(tag)) {
         connection.setOutErrorsState(convert(Integer.class, value, null));
      } else if (ATTR_INDISCARDS.equals(tag)) {
         connection.setInDiscards(convert(Double.class, value, null));
      } else if (ATTR_OUTDISCARDS.equals(tag)) {
         connection.setOutDiscards(convert(Double.class, value, null));
      } else if (ATTR_INERRORS.equals(tag)) {
         connection.setInErrors(convert(Double.class, value, null));
      } else if (ATTR_OUTERRORS.equals(tag)) {
         connection.setOutErrors(convert(Double.class, value, null));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, connection, m_tags));
      }
   }

   public void parseForInterface(Interface _interface, String tag, String value) {
      if (ATTR_GROUP.equals(tag)) {
         _interface.setGroup(value);
      } else if (ATTR_DOMAIN.equals(tag)) {
         _interface.setDomain(value);
      } else if (ATTR_KEY.equals(tag)) {
         _interface.setKey(value);
      } else if (ATTR_INSTATE.equals(tag)) {
         _interface.setInstate(convert(Integer.class, value, null));
      } else if (ATTR_OUTSTATE.equals(tag)) {
         _interface.setOutstate(convert(Integer.class, value, null));
      } else if (ATTR_IN.equals(tag)) {
         _interface.setIn(convert(Double.class, value, null));
      } else if (ATTR_OUT.equals(tag)) {
         _interface.setOut(convert(Double.class, value, null));
      } else if (ATTR_INDISCARDSSTATE.equals(tag)) {
         _interface.setInDiscardsState(convert(Integer.class, value, null));
      } else if (ATTR_OUTDISCARDSSTATE.equals(tag)) {
         _interface.setOutDiscardsState(convert(Integer.class, value, null));
      } else if (ATTR_INERRORSSTATE.equals(tag)) {
         _interface.setInErrorsState(convert(Integer.class, value, null));
      } else if (ATTR_OUTERRORSSTATE.equals(tag)) {
         _interface.setOutErrorsState(convert(Integer.class, value, null));
      } else if (ATTR_INDISCARDS.equals(tag)) {
         _interface.setInDiscards(convert(Double.class, value, null));
      } else if (ATTR_OUTDISCARDS.equals(tag)) {
         _interface.setOutDiscards(convert(Double.class, value, null));
      } else if (ATTR_INERRORS.equals(tag)) {
         _interface.setInErrors(convert(Double.class, value, null));
      } else if (ATTR_OUTERRORS.equals(tag)) {
         _interface.setOutErrors(convert(Double.class, value, null));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, _interface, m_tags));
      }
   }

   public void parseForNetGraph(NetGraph netGraph, String tag, String value) {
      if (ENTITY_NETTOPOLOGIES.equals(tag)) {
         // do nothing here
      } else if (ATTR_MINUTE.equals(tag)) {
         netGraph.setMinute(convert(Integer.class, value, null));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, netGraph, m_tags));
      }
   }

   public void parseForNetGraphSet(NetGraphSet netGraphSet, String tag, String value) {
      if (ENTITY_NETGRAPHS.equals(tag)) {
         // do nothing here
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, netGraphSet, m_tags));
      }
   }

   public void parseForNetTopology(NetTopology netTopology, String tag, String value) {
      if (ENTITY_ANCHORS.equals(tag) || ENTITY_SWITCHS.equals(tag) || ENTITY_CONNECTIONS.equals(tag)) {
         // do nothing here
      } else if (ATTR_NAME.equals(tag)) {
         netTopology.setName(value);
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, netTopology, m_tags));
      }
   }

   public void parseForSwitch(Switch _switch, String tag, String value) {
      if (ATTR_NAME.equals(tag)) {
         _switch.setName(value);
      } else if (ATTR_X.equals(tag)) {
         _switch.setX(convert(Integer.class, value, null));
      } else if (ATTR_Y.equals(tag)) {
         _switch.setY(convert(Integer.class, value, null));
      } else if (ATTR_STATE.equals(tag)) {
         _switch.setState(convert(Integer.class, value, null));
      } else {
         throw new RuntimeException(String.format("Unknown tag(%s) of %s under %s!", tag, _switch, m_tags));
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
