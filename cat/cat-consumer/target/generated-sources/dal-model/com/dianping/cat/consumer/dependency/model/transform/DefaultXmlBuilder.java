package com.dianping.cat.consumer.dependency.model.transform;

import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_AVG;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_DOMAIN;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ENDTIME;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ERROR_COUNT;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_EXCEPTION_COUNT;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_KEY;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_STARTTIME;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_SUM;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_TARGET;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_TOTAL_COUNT;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_TYPE;
import static com.dianping.cat.consumer.dependency.model.Constants.ELEMENT_DOMAINNAME;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_DEPENDENCY;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_DEPENDENCY_REPORT;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_INDEX;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_SEGMENT;

import com.dianping.cat.consumer.dependency.model.IEntity;
import com.dianping.cat.consumer.dependency.model.IVisitor;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

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

   protected String toString(Number number, String format) {
      if (number != null) {
         return new java.text.DecimalFormat(format).format(number);
      } else {
         return null;
      }
   }

   @Override
   public void visitDependency(Dependency dependency) {
      startTag(ENTITY_DEPENDENCY, true, null, ATTR_TYPE, dependency.getType(), ATTR_TARGET, dependency.getTarget(), ATTR_TOTAL_COUNT, dependency.getTotalCount(), ATTR_AVG, toString(dependency.getAvg(), "0.00"), ATTR_ERROR_COUNT, dependency.getErrorCount(), ATTR_KEY, dependency.getKey(), ATTR_SUM, toString(dependency.getSum(), "0.00"));
   }

   @Override
   public void visitDependencyReport(DependencyReport dependencyReport) {
      startTag(ENTITY_DEPENDENCY_REPORT, null, ATTR_DOMAIN, dependencyReport.getDomain(), ATTR_STARTTIME, toString(dependencyReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_ENDTIME, toString(dependencyReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!dependencyReport.getDomainNames().isEmpty()) {
         for (String domainName : dependencyReport.getDomainNames().toArray(new String[0])) {
            tagWithText(ELEMENT_DOMAINNAME, domainName);
         }
      }

      if (!dependencyReport.getSegments().isEmpty()) {
         for (Segment segment : dependencyReport.getSegments().values().toArray(new Segment[0])) {
            segment.accept(m_visitor);
         }
      }

      endTag(ENTITY_DEPENDENCY_REPORT);
   }

   @Override
   public void visitIndex(Index index) {
      startTag(ENTITY_INDEX, true, null, ATTR_NAME, index.getName(), ATTR_TOTAL_COUNT, index.getTotalCount(), ATTR_ERROR_COUNT, index.getErrorCount(), ATTR_SUM, toString(index.getSum(), "0.00"), ATTR_AVG, toString(index.getAvg(), "0.00"));
   }

   @Override
   public void visitSegment(Segment segment) {
      startTag(ENTITY_SEGMENT, null, ATTR_ID, segment.getId(), ATTR_EXCEPTION_COUNT, segment.getExceptionCount());

      if (!segment.getIndexs().isEmpty()) {
         for (Index index : segment.getIndexs().values().toArray(new Index[0])) {
            index.accept(m_visitor);
         }
      }

      if (!segment.getDependencies().isEmpty()) {
         for (Dependency dependency : segment.getDependencies().values().toArray(new Dependency[0])) {
            dependency.accept(m_visitor);
         }
      }

      endTag(ENTITY_SEGMENT);
   }
}
