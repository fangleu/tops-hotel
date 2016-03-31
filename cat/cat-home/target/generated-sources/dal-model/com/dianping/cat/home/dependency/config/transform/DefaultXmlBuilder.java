package com.dianping.cat.home.dependency.config.transform;

import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_ERROR_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_ERROR_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_MIN_COUNT_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_WARNING_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_WARNING_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_ERROR_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_ERROR_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_FROM;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_ID;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_KEY;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_MIN_COUNT_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_TO;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_TYPE;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_WARNING_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_WARNING_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_DOMAIN_CONFIG;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_EDGE_CONFIG;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_NODE_CONFIG;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_TOPOLOGY_GRAPH_CONFIG;

import com.dianping.cat.home.dependency.config.IEntity;
import com.dianping.cat.home.dependency.config.IVisitor;
import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

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
   public void visitDomainConfig(DomainConfig domainConfig) {
      startTag(ENTITY_DOMAIN_CONFIG, true, null, ATTR_ID, domainConfig.getId(), ATTR_WARNING_THRESHOLD, domainConfig.getWarningThreshold(), ATTR_ERROR_THRESHOLD, domainConfig.getErrorThreshold(), ATTR_WARNING_RESPONSE_TIME, domainConfig.getWarningResponseTime(), ATTR_ERROR_RESPONSE_TIME, domainConfig.getErrorResponseTime(), ATTR_MIN_COUNT_THRESHOLD, domainConfig.getMinCountThreshold());
   }

   @Override
   public void visitEdgeConfig(EdgeConfig edgeConfig) {
      startTag(ENTITY_EDGE_CONFIG, true, null, ATTR_KEY, edgeConfig.getKey(), ATTR_TYPE, edgeConfig.getType(), ATTR_FROM, edgeConfig.getFrom(), ATTR_TO, edgeConfig.getTo(), ATTR_WARNING_THRESHOLD, edgeConfig.getWarningThreshold(), ATTR_ERROR_THRESHOLD, edgeConfig.getErrorThreshold(), ATTR_WARNING_RESPONSE_TIME, edgeConfig.getWarningResponseTime(), ATTR_ERROR_RESPONSE_TIME, edgeConfig.getErrorResponseTime(), ATTR_MIN_COUNT_THRESHOLD, edgeConfig.getMinCountThreshold());
   }

   @Override
   public void visitNodeConfig(NodeConfig nodeConfig) {
      startTag(ENTITY_NODE_CONFIG, null, ATTR_TYPE, nodeConfig.getType(), ATTR_DEFAULT_WARNING_THRESHOLD, nodeConfig.getDefaultWarningThreshold(), ATTR_DEFAULT_ERROR_THRESHOLD, nodeConfig.getDefaultErrorThreshold(), ATTR_DEFAULT_WARNING_RESPONSE_TIME, nodeConfig.getDefaultWarningResponseTime(), ATTR_DEFAULT_ERROR_RESPONSE_TIME, nodeConfig.getDefaultErrorResponseTime(), ATTR_DEFAULT_MIN_COUNT_THRESHOLD, nodeConfig.getDefaultMinCountThreshold());

      if (!nodeConfig.getDomainConfigs().isEmpty()) {
         for (DomainConfig domainConfig : nodeConfig.getDomainConfigs().values().toArray(new DomainConfig[0])) {
            domainConfig.accept(m_visitor);
         }
      }

      endTag(ENTITY_NODE_CONFIG);
   }

   @Override
   public void visitTopologyGraphConfig(TopologyGraphConfig topologyGraphConfig) {
      startTag(ENTITY_TOPOLOGY_GRAPH_CONFIG, null);

      if (!topologyGraphConfig.getNodeConfigs().isEmpty()) {
         for (NodeConfig nodeConfig : topologyGraphConfig.getNodeConfigs().values().toArray(new NodeConfig[0])) {
            nodeConfig.accept(m_visitor);
         }
      }

      if (!topologyGraphConfig.getEdgeConfigs().isEmpty()) {
         for (EdgeConfig edgeConfig : topologyGraphConfig.getEdgeConfigs().values().toArray(new EdgeConfig[0])) {
            edgeConfig.accept(m_visitor);
         }
      }

      endTag(ENTITY_TOPOLOGY_GRAPH_CONFIG);
   }
}
