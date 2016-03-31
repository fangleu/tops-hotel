package com.dianping.cat.home.heartbeat.transform;

import static com.dianping.cat.home.heartbeat.Constants.ATTR_ALERT;
import static com.dianping.cat.home.heartbeat.Constants.ATTR_DELTA;
import static com.dianping.cat.home.heartbeat.Constants.ATTR_ID;
import static com.dianping.cat.home.heartbeat.Constants.ATTR_LABLE;
import static com.dianping.cat.home.heartbeat.Constants.ATTR_ORDER;
import static com.dianping.cat.home.heartbeat.Constants.ATTR_TITLE;
import static com.dianping.cat.home.heartbeat.Constants.ATTR_UNIT;
import static com.dianping.cat.home.heartbeat.Constants.ENTITY_GROUP;
import static com.dianping.cat.home.heartbeat.Constants.ENTITY_HEARTBEAT_DISPLAY_POLICY;
import static com.dianping.cat.home.heartbeat.Constants.ENTITY_METRIC;

import com.dianping.cat.home.heartbeat.IEntity;
import com.dianping.cat.home.heartbeat.IVisitor;
import com.dianping.cat.home.heartbeat.entity.Group;
import com.dianping.cat.home.heartbeat.entity.HeartbeatDisplayPolicy;
import com.dianping.cat.home.heartbeat.entity.Metric;

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
   public void visitGroup(Group group) {
      startTag(ENTITY_GROUP, null, ATTR_ID, group.getId(), ATTR_ORDER, group.getOrder());

      if (!group.getMetrics().isEmpty()) {
         for (Metric metric : group.getMetrics().values().toArray(new Metric[0])) {
            metric.accept(m_visitor);
         }
      }

      endTag(ENTITY_GROUP);
   }

   @Override
   public void visitHeartbeatDisplayPolicy(HeartbeatDisplayPolicy heartbeatDisplayPolicy) {
      startTag(ENTITY_HEARTBEAT_DISPLAY_POLICY, null);

      if (!heartbeatDisplayPolicy.getGroups().isEmpty()) {
         for (Group group : heartbeatDisplayPolicy.getGroups().values().toArray(new Group[0])) {
            group.accept(m_visitor);
         }
      }

      endTag(ENTITY_HEARTBEAT_DISPLAY_POLICY);
   }

   @Override
   public void visitMetric(Metric metric) {
      startTag(ENTITY_METRIC, true, null, ATTR_ID, metric.getId(), ATTR_UNIT, metric.getUnit(), ATTR_DELTA, metric.isDelta(), ATTR_ORDER, metric.getOrder(), ATTR_TITLE, metric.getTitle(), ATTR_LABLE, metric.getLable(), ATTR_ALERT, metric.isAlert());
   }
}
