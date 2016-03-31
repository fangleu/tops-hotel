package com.dianping.cat.consumer.metric.model.transform;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_AVG;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_COUNT;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ENDTIME;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_PRODUCT;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_RUN_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_STARTTIME;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_SUM;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_TYPE;
import static com.dianping.cat.consumer.metric.model.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_ABTEST;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_GROUP;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_METRIC_ITEM;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_METRIC_REPORT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_POINT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_SEGMENT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_STATISTIC;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_STATISTICS_ITEM;

import com.dianping.cat.consumer.metric.model.IEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;
import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

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

   protected void tagWithText(String name, String text, Object... nameValues) {
      if (text == null) {
         return;
      }
      
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

      m_sb.append(">");
      m_sb.append(escape(text, true));
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected void element(String name, String text, boolean escape) {
      if (text == null) {
         return;
      }
      
      indent();
      
      m_sb.append('<').append(name).append(">");
      
      if (escape) {
         m_sb.append(escape(text, true));
      } else {
         m_sb.append("<![CDATA[").append(text).append("]]>");
      }
      
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected String toString(java.util.Date date, String format) {
      if (date != null) {
         return new java.text.SimpleDateFormat(format).format(date);
      } else {
         return null;
      }
   }

   @Override
   public void visitAbtest(Abtest abtest) {
      startTag(ENTITY_ABTEST, null, ATTR_RUN_ID, abtest.getRunId(), ATTR_NAME, abtest.getName());

      if (!abtest.getGroups().isEmpty()) {
         for (Group group : abtest.getGroups().values().toArray(new Group[0])) {
            group.accept(m_visitor);
         }
      }

      endTag(ENTITY_ABTEST);
   }

   @Override
   public void visitGroup(Group group) {
      startTag(ENTITY_GROUP, null, ATTR_NAME, group.getName());

      if (!group.getPoints().isEmpty()) {
         for (Point point : group.getPoints().values().toArray(new Point[0])) {
            point.accept(m_visitor);
         }
      }

      endTag(ENTITY_GROUP);
   }

   @Override
   public void visitMetricItem(MetricItem metricItem) {
      startTag(ENTITY_METRIC_ITEM, null, ATTR_ID, metricItem.getId(), ATTR_TYPE, metricItem.getType());

      if (!metricItem.getDomains().isEmpty()) {
         for (String domain : metricItem.getDomains().toArray(new String[0])) {
            tagWithText(ELEMENT_DOMAIN, domain);
         }
      }

      if (!metricItem.getAbtests().isEmpty()) {
         for (Abtest abtest : metricItem.getAbtests().values().toArray(new Abtest[0])) {
            abtest.accept(m_visitor);
         }
      }

      if (!metricItem.getSegments().isEmpty()) {
         for (Segment segment : metricItem.getSegments().values().toArray(new Segment[0])) {
            segment.accept(m_visitor);
         }
      }

      endTag(ENTITY_METRIC_ITEM);
   }

   @Override
   public void visitMetricReport(MetricReport metricReport) {
      startTag(ENTITY_METRIC_REPORT, null, ATTR_PRODUCT, metricReport.getProduct(), ATTR_STARTTIME, toString(metricReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_ENDTIME, toString(metricReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!metricReport.getMetricItems().isEmpty()) {
         for (MetricItem metricItem : metricReport.getMetricItems().values().toArray(new MetricItem[0])) {
            metricItem.accept(m_visitor);
         }
      }

      if (!metricReport.getStatistics().isEmpty()) {
         for (Statistic statistic : metricReport.getStatistics().values().toArray(new Statistic[0])) {
            statistic.accept(m_visitor);
         }
      }

      endTag(ENTITY_METRIC_REPORT);
   }

   @Override
   public void visitPoint(Point point) {
      startTag(ENTITY_POINT, true, null, ATTR_ID, point.getId(), ATTR_COUNT, point.getCount(), ATTR_SUM, point.getSum(), ATTR_AVG, point.getAvg());
   }

   @Override
   public void visitSegment(Segment segment) {
      startTag(ENTITY_SEGMENT, true, null, ATTR_ID, segment.getId(), ATTR_COUNT, segment.getCount(), ATTR_SUM, segment.getSum(), ATTR_AVG, segment.getAvg());
   }

   @Override
   public void visitStatistic(Statistic statistic) {
      startTag(ENTITY_STATISTIC, null, ATTR_ID, statistic.getId());

      if (!statistic.getStatisticsItems().isEmpty()) {
         for (StatisticsItem statisticsItem : statistic.getStatisticsItems().values().toArray(new StatisticsItem[0])) {
            statisticsItem.accept(m_visitor);
         }
      }

      endTag(ENTITY_STATISTIC);
   }

   @Override
   public void visitStatisticsItem(StatisticsItem statisticsItem) {
      startTag(ENTITY_STATISTICS_ITEM, true, null, ATTR_ID, statisticsItem.getId(), ATTR_COUNT, statisticsItem.getCount());
   }
}
