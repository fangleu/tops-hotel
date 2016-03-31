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
import static com.dianping.cat.home.network.Constants.ENTITY_ANCHOR;
import static com.dianping.cat.home.network.Constants.ENTITY_ANCHORS;
import static com.dianping.cat.home.network.Constants.ENTITY_CONNECTION;
import static com.dianping.cat.home.network.Constants.ENTITY_CONNECTIONS;
import static com.dianping.cat.home.network.Constants.ENTITY_INTERFACE;
import static com.dianping.cat.home.network.Constants.ENTITY_NETGRAPH;
import static com.dianping.cat.home.network.Constants.ENTITY_NETGRAPHSET;
import static com.dianping.cat.home.network.Constants.ENTITY_NETTOPOLOGY;
import static com.dianping.cat.home.network.Constants.ENTITY_SWITCH;
import static com.dianping.cat.home.network.Constants.ENTITY_SWITCHS;

import com.dianping.cat.home.network.IEntity;
import com.dianping.cat.home.network.IVisitor;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultXmlBuilder implements IVisitor {

   private IVisitor m_visitor = this;

   private int m_level;

   private StringBuilder m_sb;

   private boolean m_compact;

   public DefaultXmlBuilder() {
      this(false);
   }

   public DefaultXmlBuilder(boolean compact) {
      this(compact, new StringBuilder(4096));
   }

   public DefaultXmlBuilder(boolean compact, StringBuilder sb) {
      m_compact = compact;
      m_sb = sb;
      m_sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
   }

   public String buildXml(IEntity<?> entity) {
      entity.accept(m_visitor);
      return m_sb.toString();
   }

   protected void endTag(String name) {
      m_level--;

      indent();
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected String escape(Object value) {
      return escape(value, false);
   }
   
   protected String escape(Object value, boolean text) {
      if (value == null) {
         return null;
      }

      String str = value.toString();
      int len = str.length();
      StringBuilder sb = new StringBuilder(len + 16);

      for (int i = 0; i < len; i++) {
         final char ch = str.charAt(i);

         switch (ch) {
         case '<':
            sb.append("&lt;");
            break;
         case '>':
            sb.append("&gt;");
            break;
         case '&':
            sb.append("&amp;");
            break;
         case '"':
            if (!text) {
               sb.append("&quot;");
               break;
            }
         default:
            sb.append(ch);
            break;
         }
      }

      return sb.toString();
   }
   
   protected void indent() {
      if (!m_compact) {
         for (int i = m_level - 1; i >= 0; i--) {
            m_sb.append("   ");
         }
      }
   }

   protected void startTag(String name) {
      startTag(name, false, null);
   }
   
   protected void startTag(String name, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      startTag(name, null, closed, dynamicAttributes, nameValues);
   }

   protected void startTag(String name, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      startTag(name, null, false, dynamicAttributes, nameValues);
   }

   protected void startTag(String name, Object text, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      indent();

      m_sb.append('<').append(name);

      int len = nameValues.length;

      for (int i = 0; i + 1 < len; i += 2) {
         Object attrName = nameValues[i];
         Object attrValue = nameValues[i + 1];

         if (attrValue != null) {
            m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
         }
      }

      if (dynamicAttributes != null) {
         for (java.util.Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
            m_sb.append(' ').append(e.getKey()).append("=\"").append(escape(e.getValue())).append('"');
         }
      }

      if (text != null && closed) {
         m_sb.append('>');
         m_sb.append(escape(text, true));
         m_sb.append("</").append(name).append(">\r\n");
      } else {
         if (closed) {
            m_sb.append('/');
         } else {
            m_level++;
         }
   
         m_sb.append(">\r\n");
      }
   }

   @Override
   public void visitAnchor(Anchor anchor) {
      startTag(ENTITY_ANCHOR, true, null, ATTR_NAME, anchor.getName(), ATTR_X, anchor.getX(), ATTR_Y, anchor.getY());
   }

   @Override
   public void visitConnection(Connection connection) {
      startTag(ENTITY_CONNECTION, null, ATTR_FROM, connection.getFrom(), ATTR_TO, connection.getTo(), ATTR_INSTATE, connection.getInstate(), ATTR_OUTSTATE, connection.getOutstate(), ATTR_INSUM, connection.getInsum(), ATTR_OUTSUM, connection.getOutsum(), ATTR_INDISCARDSSTATE, connection.getInDiscardsState(), ATTR_OUTDISCARDSSTATE, connection.getOutDiscardsState(), ATTR_INERRORSSTATE, connection.getInErrorsState(), ATTR_OUTERRORSSTATE, connection.getOutErrorsState(), ATTR_INDISCARDS, connection.getInDiscards(), ATTR_OUTDISCARDS, connection.getOutDiscards(), ATTR_INERRORS, connection.getInErrors(), ATTR_OUTERRORS, connection.getOutErrors());

      if (!connection.getInterfaces().isEmpty()) {
         for (Interface interface_ : connection.getInterfaces().toArray(new Interface[0])) {
            interface_.accept(m_visitor);
         }
      }

      endTag(ENTITY_CONNECTION);
   }

   @Override
   public void visitInterface(Interface _interface) {
      startTag(ENTITY_INTERFACE, true, null, ATTR_GROUP, _interface.getGroup(), ATTR_DOMAIN, _interface.getDomain(), ATTR_KEY, _interface.getKey(), ATTR_INSTATE, _interface.getInstate(), ATTR_OUTSTATE, _interface.getOutstate(), ATTR_IN, _interface.getIn(), ATTR_OUT, _interface.getOut(), ATTR_INDISCARDSSTATE, _interface.getInDiscardsState(), ATTR_OUTDISCARDSSTATE, _interface.getOutDiscardsState(), ATTR_INERRORSSTATE, _interface.getInErrorsState(), ATTR_OUTERRORSSTATE, _interface.getOutErrorsState(), ATTR_INDISCARDS, _interface.getInDiscards(), ATTR_OUTDISCARDS, _interface.getOutDiscards(), ATTR_INERRORS, _interface.getInErrors(), ATTR_OUTERRORS, _interface.getOutErrors());
   }

   @Override
   public void visitNetGraph(NetGraph netGraph) {
      startTag(ENTITY_NETGRAPH, null, ATTR_MINUTE, netGraph.getMinute());

      if (!netGraph.getNetTopologies().isEmpty()) {
         for (NetTopology netTopology : netGraph.getNetTopologies().toArray(new NetTopology[0])) {
            netTopology.accept(m_visitor);
         }
      }

      endTag(ENTITY_NETGRAPH);
   }

   @Override
   public void visitNetGraphSet(NetGraphSet netGraphSet) {
      startTag(ENTITY_NETGRAPHSET, null);

      if (!netGraphSet.getNetGraphs().isEmpty()) {
         for (NetGraph netGraph : netGraphSet.getNetGraphs().values().toArray(new NetGraph[0])) {
            netGraph.accept(m_visitor);
         }
      }

      endTag(ENTITY_NETGRAPHSET);
   }

   @Override
   public void visitNetTopology(NetTopology netTopology) {
      startTag(ENTITY_NETTOPOLOGY, null, ATTR_NAME, netTopology.getName());

      if (!netTopology.getAnchors().isEmpty()) {
         startTag(ENTITY_ANCHORS);

         for (Anchor anchor : netTopology.getAnchors().toArray(new Anchor[0])) {
            anchor.accept(m_visitor);
         }

         endTag(ENTITY_ANCHORS);
      }

      if (!netTopology.getSwitchs().isEmpty()) {
         startTag(ENTITY_SWITCHS);

         for (Switch switch_ : netTopology.getSwitchs().toArray(new Switch[0])) {
            switch_.accept(m_visitor);
         }

         endTag(ENTITY_SWITCHS);
      }

      if (!netTopology.getConnections().isEmpty()) {
         startTag(ENTITY_CONNECTIONS);

         for (Connection connection : netTopology.getConnections().toArray(new Connection[0])) {
            connection.accept(m_visitor);
         }

         endTag(ENTITY_CONNECTIONS);
      }

      endTag(ENTITY_NETTOPOLOGY);
   }

   @Override
   public void visitSwitch(Switch _switch) {
      startTag(ENTITY_SWITCH, true, null, ATTR_NAME, _switch.getName(), ATTR_X, _switch.getX(), ATTR_Y, _switch.getY(), ATTR_STATE, _switch.getState());
   }
}
