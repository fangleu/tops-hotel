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
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_EDGE;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_GRAPH;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_NODE;

import com.dianping.cat.home.dependency.graph.IEntity;
import com.dianping.cat.home.dependency.graph.IVisitor;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

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
   public void visitTopologyEdge(TopologyEdge topologyEdge) {
      startTag(ENTITY_TOPOLOGY_EDGE, true, null, ATTR_KEY, topologyEdge.getKey(), ATTR_TYPE, topologyEdge.getType(), ATTR_TARGET, topologyEdge.getTarget(), ATTR_OPPOSITE, topologyEdge.isOpposite(), ATTR_WEIGHT, topologyEdge.getWeight(), ATTR_STATUS, topologyEdge.getStatus(), ATTR_DES, topologyEdge.getDes(), ATTR_LINK, topologyEdge.getLink(), ATTR_SELF, topologyEdge.getSelf(), ATTR_DASHED, topologyEdge.isDashed());
   }

   @Override
   public void visitTopologyGraph(TopologyGraph topologyGraph) {
      startTag(ENTITY_TOPOLOGY_GRAPH, null, ATTR_ID, topologyGraph.getId(), ATTR_TYPE, topologyGraph.getType(), ATTR_STATUS, topologyGraph.getStatus(), ATTR_DES, topologyGraph.getDes());

      if (!topologyGraph.getNodes().isEmpty()) {
         for (TopologyNode topologyNode : topologyGraph.getNodes().values().toArray(new TopologyNode[0])) {
            topologyNode.accept(m_visitor);
         }
      }

      if (!topologyGraph.getEdges().isEmpty()) {
         for (TopologyEdge topologyEdge : topologyGraph.getEdges().values().toArray(new TopologyEdge[0])) {
            topologyEdge.accept(m_visitor);
         }
      }

      endTag(ENTITY_TOPOLOGY_GRAPH);
   }

   @Override
   public void visitTopologyNode(TopologyNode topologyNode) {
      startTag(ENTITY_TOPOLOGY_NODE, true, null, ATTR_ID, topologyNode.getId(), ATTR_TYPE, topologyNode.getType(), ATTR_STATUS, topologyNode.getStatus(), ATTR_DES, topologyNode.getDes(), ATTR_LINK, topologyNode.getLink(), ATTR_WEIGHT, topologyNode.getWeight());
   }
}
