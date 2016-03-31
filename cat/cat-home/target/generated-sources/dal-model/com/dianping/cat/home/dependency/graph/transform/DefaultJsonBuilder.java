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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dianping.cat.home.dependency.graph.IEntity;
import com.dianping.cat.home.dependency.graph.IVisitor;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

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
   public void visitTopologyEdge(TopologyEdge topologyEdge) {
      attributes(null, ATTR_KEY, topologyEdge.getKey(), ATTR_TYPE, topologyEdge.getType(), ATTR_TARGET, topologyEdge.getTarget(), ATTR_OPPOSITE, topologyEdge.isOpposite(), ATTR_WEIGHT, topologyEdge.getWeight(), ATTR_STATUS, topologyEdge.getStatus(), ATTR_DES, topologyEdge.getDes(), ATTR_LINK, topologyEdge.getLink(), ATTR_SELF, topologyEdge.getSelf(), ATTR_DASHED, topologyEdge.isDashed());
   }

   @Override
   public void visitTopologyGraph(TopologyGraph topologyGraph) {
      attributes(null, ATTR_ID, topologyGraph.getId(), ATTR_TYPE, topologyGraph.getType(), ATTR_STATUS, topologyGraph.getStatus(), ATTR_DES, topologyGraph.getDes());

      if (!topologyGraph.getNodes().isEmpty()) {
         objectBegin(ENTITY_NODES);

         for (Map.Entry<String, TopologyNode> e : topologyGraph.getNodes().entrySet()) {
            String key = String.valueOf(e.getKey());

            objectBegin(key);
            e.getValue().accept(m_visitor);
            objectEnd(key);
         }

         objectEnd(ENTITY_NODES);
      }

      if (!topologyGraph.getEdges().isEmpty()) {
         objectBegin(ENTITY_EDGES);

         for (Map.Entry<String, TopologyEdge> e : topologyGraph.getEdges().entrySet()) {
            String key = String.valueOf(e.getKey());

            objectBegin(key);
            e.getValue().accept(m_visitor);
            objectEnd(key);
         }

         objectEnd(ENTITY_EDGES);
      }
   }

   @Override
   public void visitTopologyNode(TopologyNode topologyNode) {
      attributes(null, ATTR_ID, topologyNode.getId(), ATTR_TYPE, topologyNode.getType(), ATTR_STATUS, topologyNode.getStatus(), ATTR_DES, topologyNode.getDes(), ATTR_LINK, topologyNode.getLink(), ATTR_WEIGHT, topologyNode.getWeight());
   }
}
