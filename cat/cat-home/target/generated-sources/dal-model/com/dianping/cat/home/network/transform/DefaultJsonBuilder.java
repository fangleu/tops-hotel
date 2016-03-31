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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dianping.cat.home.network.IEntity;
import com.dianping.cat.home.network.IVisitor;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultJsonBuilder implements IVisitor {

   private IVisitor m_visitor;

   private int m_level;

   private StringBuilder m_sb;

   private boolean m_compact;

   public DefaultJsonBuilder() {
      this(false);
   }

   public DefaultJsonBuilder(boolean compact) {
      this(compact, new StringBuilder(4096));
   }

   public DefaultJsonBuilder(boolean compact, StringBuilder sb) {
      m_compact = compact;
      m_sb = sb;
      m_visitor = this;
   }

   protected void arrayBegin(String name) {
      indent();
      m_sb.append('"').append(name).append(m_compact ? "\":[" : "\": [\r\n");
      m_level++;
   }

   protected void arrayEnd(String name) {
      m_level--;

      trimComma();
      indent();
      m_sb.append("],").append(m_compact ? "" : "\r\n");
   }

   protected void attributes(Map<String, String> dynamicAttributes, Object... nameValues) {
      int len = nameValues.length;

      for (int i = 0; i + 1 < len; i += 2) {
         Object attrName = nameValues[i];
         Object attrValue = nameValues[i + 1];

         if (attrValue != null) {
            if (attrValue instanceof List) {
               @SuppressWarnings("unchecked")
               List<Object> list = (List<Object>) attrValue;

               if (!list.isEmpty()) {
                  indent();
                  m_sb.append('"').append(attrName).append(m_compact ? "\":[" : "\": [");

                  for (Object item : list) {
                     m_sb.append(' ');
                     toString(m_sb, item);
                     m_sb.append(',');
                  }

                  m_sb.setLength(m_sb.length() - 1);
                  m_sb.append(m_compact ? "]," : " ],\r\n");
               }
            } else {
               if (m_compact) {
                  m_sb.append('"').append(attrName).append("\":");
                  toString(m_sb, attrValue);
                  m_sb.append(",");
               } else {
                  indent();
                  m_sb.append('"').append(attrName).append("\": ");
                  toString(m_sb, attrValue);
                  m_sb.append(",\r\n");
               }
            }
         }
      }

      if (dynamicAttributes != null) {
         for (Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
            if (m_compact) {
               m_sb.append('"').append(e.getKey()).append("\":");
               toString(m_sb, e.getValue());
               m_sb.append(",");
            } else {
               indent();
               m_sb.append('"').append(e.getKey()).append("\": ");
               toString(m_sb, e.getValue());
               m_sb.append(",\r\n");
            }
         }
      }
   }

   public String build(IEntity<?> entity) {
      objectBegin(null);
      entity.accept(this);
      objectEnd(null);
      trimComma();

      return m_sb.toString();
   }

   public String buildArray(Collection<? extends IEntity<?>> entities) {
      m_sb.append('[');

      if (entities != null) {
         for (IEntity<?> entity : entities) {
            objectBegin(null);
            entity.accept(this);
            objectEnd(null);
         }

         trimComma();
      }

      m_sb.append(']');

      return m_sb.toString();
   }

   protected void indent() {
      if (!m_compact) {
         for (int i = m_level - 1; i >= 0; i--) {
            m_sb.append("   ");
         }
      }
   }

   protected void objectBegin(String name) {
      indent();

      if (name == null) {
         m_sb.append("{").append(m_compact ? "" : "\r\n");
      } else {
         m_sb.append('"').append(name).append(m_compact ? "\":{" : "\": {\r\n");
      }

      m_level++;
   }

   protected void objectEnd(String name) {
      m_level--;

      trimComma();
      indent();
      m_sb.append(m_compact ? "}," : "},\r\n");
   }

   protected void toString(StringBuilder sb, Object value) {
      if (value == null) {
         sb.append("null");
      } else if (value instanceof Boolean || value instanceof Number) {
         sb.append(value);
      } else {
         String val = value.toString();
         int len = val.length();

         sb.append('"');

         for (int i = 0; i < len; i++) {
            char ch = val.charAt(i);

            switch (ch) {
            case '\\':
            case '/':
            case '"':
               sb.append('\\').append(ch);
               break;
            case '\t':
               sb.append("\\t");
               break;
            case '\r':
               sb.append("\\r");
               break;
            case '\n':
               sb.append("\\n");
               break;
            default:
               sb.append(ch);
               break;
            }
         }

         sb.append('"');
      }
   }

   protected void trimComma() {
      int len = m_sb.length();

      if (m_compact) {
         if (len > 1 && m_sb.charAt(len - 1) == ',') {
            m_sb.replace(len - 1, len, "");
         }
      } else {
         if (len > 3 && m_sb.charAt(len - 3) == ',') {
            m_sb.replace(len - 3, len - 2, "");
         }
      }
   }

   @Override
   public void visitAnchor(Anchor anchor) {
      attributes(null, ATTR_NAME, anchor.getName(), ATTR_X, anchor.getX(), ATTR_Y, anchor.getY());
   }

   @Override
   public void visitConnection(Connection connection) {
      attributes(null, ATTR_FROM, connection.getFrom(), ATTR_TO, connection.getTo(), ATTR_INSTATE, connection.getInstate(), ATTR_OUTSTATE, connection.getOutstate(), ATTR_INSUM, connection.getInsum(), ATTR_OUTSUM, connection.getOutsum(), ATTR_INDISCARDSSTATE, connection.getInDiscardsState(), ATTR_OUTDISCARDSSTATE, connection.getOutDiscardsState(), ATTR_INERRORSSTATE, connection.getInErrorsState(), ATTR_OUTERRORSSTATE, connection.getOutErrorsState(), ATTR_INDISCARDS, connection.getInDiscards(), ATTR_OUTDISCARDS, connection.getOutDiscards(), ATTR_INERRORS, connection.getInErrors(), ATTR_OUTERRORS, connection.getOutErrors());

      if (!connection.getInterfaces().isEmpty()) {
         arrayBegin(ENTITY_INTERFACES);

         for (Interface interface_ : connection.getInterfaces()) {
            objectBegin(null);
            interface_.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_INTERFACES);
      }
   }

   @Override
   public void visitInterface(Interface _interface) {
      attributes(null, ATTR_GROUP, _interface.getGroup(), ATTR_DOMAIN, _interface.getDomain(), ATTR_KEY, _interface.getKey(), ATTR_INSTATE, _interface.getInstate(), ATTR_OUTSTATE, _interface.getOutstate(), ATTR_IN, _interface.getIn(), ATTR_OUT, _interface.getOut(), ATTR_INDISCARDSSTATE, _interface.getInDiscardsState(), ATTR_OUTDISCARDSSTATE, _interface.getOutDiscardsState(), ATTR_INERRORSSTATE, _interface.getInErrorsState(), ATTR_OUTERRORSSTATE, _interface.getOutErrorsState(), ATTR_INDISCARDS, _interface.getInDiscards(), ATTR_OUTDISCARDS, _interface.getOutDiscards(), ATTR_INERRORS, _interface.getInErrors(), ATTR_OUTERRORS, _interface.getOutErrors());
   }

   @Override
   public void visitNetGraph(NetGraph netGraph) {
      attributes(null, ATTR_MINUTE, netGraph.getMinute());

      if (!netGraph.getNetTopologies().isEmpty()) {
         arrayBegin(ENTITY_NETTOPOLOGIES);

         for (NetTopology netTopology : netGraph.getNetTopologies()) {
            objectBegin(null);
            netTopology.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_NETTOPOLOGIES);
      }
   }

   @Override
   public void visitNetGraphSet(NetGraphSet netGraphSet) {

      if (!netGraphSet.getNetGraphs().isEmpty()) {
         objectBegin(ENTITY_NETGRAPHS);

         for (Map.Entry<Integer, NetGraph> e : netGraphSet.getNetGraphs().entrySet()) {
            String key = String.valueOf(e.getKey());

            objectBegin(key);
            e.getValue().accept(m_visitor);
            objectEnd(key);
         }

         objectEnd(ENTITY_NETGRAPHS);
      }
   }

   @Override
   public void visitNetTopology(NetTopology netTopology) {
      attributes(null, ATTR_NAME, netTopology.getName());

      if (!netTopology.getAnchors().isEmpty()) {
         arrayBegin(ENTITY_ANCHORS);

         for (Anchor anchor : netTopology.getAnchors()) {
            objectBegin(null);
            anchor.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_ANCHORS);
      }

      if (!netTopology.getSwitchs().isEmpty()) {
         arrayBegin(ENTITY_SWITCHS);

         for (Switch switch_ : netTopology.getSwitchs()) {
            objectBegin(null);
            switch_.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_SWITCHS);
      }

      if (!netTopology.getConnections().isEmpty()) {
         arrayBegin(ENTITY_CONNECTIONS);

         for (Connection connection : netTopology.getConnections()) {
            objectBegin(null);
            connection.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_CONNECTIONS);
      }
   }

   @Override
   public void visitSwitch(Switch _switch) {
      attributes(null, ATTR_NAME, _switch.getName(), ATTR_X, _switch.getX(), ATTR_Y, _switch.getY(), ATTR_STATE, _switch.getState());
   }
}
