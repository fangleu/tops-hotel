package com.dianping.cat.consumer.metric.config.transform;

import static com.dianping.cat.consumer.metric.config.Constants.ATTR_ALARM;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_DOMAIN;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_METRIC_KEY;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_AVG;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_COUNT;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_DASHBOARD_ORDER;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_SUM;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_TITLE;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_TYPE;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_VIEW_ORDER;
import static com.dianping.cat.consumer.metric.config.Constants.ENTITY_METRIC_CONFIG;
import static com.dianping.cat.consumer.metric.config.Constants.ENTITY_METRIC_ITEM_CONFIG;
import static com.dianping.cat.consumer.metric.config.Constants.ENTITY_TAG;

import com.dianping.cat.consumer.metric.config.IEntity;
import com.dianping.cat.consumer.metric.config.IVisitor;
import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

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
   public void visitMetricConfig(MetricConfig metricConfig) {
      startTag(ENTITY_METRIC_CONFIG, null);

      if (!metricConfig.getMetricItemConfigs().isEmpty()) {
         for (MetricItemConfig metricItemConfig : metricConfig.getMetricItemConfigs().values().toArray(new MetricItemConfig[0])) {
            metricItemConfig.accept(m_visitor);
         }
      }

      endTag(ENTITY_METRIC_CONFIG);
   }

   @Override
   public void visitMetricItemConfig(MetricItemConfig metricItemConfig) {
      startTag(ENTITY_METRIC_ITEM_CONFIG, null, ATTR_ID, metricItemConfig.getId(), ATTR_DOMAIN, metricItemConfig.getDomain(), ATTR_TYPE, metricItemConfig.getType(), ATTR_VIEW_ORDER, metricItemConfig.getViewOrder(), ATTR_METRIC_KEY, metricItemConfig.getMetricKey(), ATTR_TITLE, metricItemConfig.getTitle(), ATTR_SHOW_COUNT, metricItemConfig.isShowCount(), ATTR_SHOW_AVG, metricItemConfig.isShowAvg(), ATTR_SHOW_SUM, metricItemConfig.isShowSum(), ATTR_SHOW_DASHBOARD_ORDER, metricItemConfig.getShowDashboardOrder(), ATTR_ALARM, metricItemConfig.isAlarm());

      if (!metricItemConfig.getTags().isEmpty()) {
         for (Tag tag : metricItemConfig.getTags().toArray(new Tag[0])) {
            tag.accept(m_visitor);
         }
      }

      endTag(ENTITY_METRIC_ITEM_CONFIG);
   }

   @Override
   public void visitTag(Tag tag) {
      startTag(ENTITY_TAG, true, null, ATTR_NAME, tag.getName(), ATTR_TYPE, tag.getType());
   }
}
