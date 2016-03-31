package com.dianping.cat.home.service.transform;

import static com.dianping.cat.home.service.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.service.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.service.Constants.ATTR_ID;
import static com.dianping.cat.home.service.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.service.Constants.ELEMENT_AVG;
import static com.dianping.cat.home.service.Constants.ELEMENT_FAILURECOUNT;
import static com.dianping.cat.home.service.Constants.ELEMENT_FAILUREPERCENT;
import static com.dianping.cat.home.service.Constants.ELEMENT_QPS;
import static com.dianping.cat.home.service.Constants.ELEMENT_SUM;
import static com.dianping.cat.home.service.Constants.ELEMENT_TOTALCOUNT;
import static com.dianping.cat.home.service.Constants.ENTITY_DOMAINS;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dianping.cat.home.service.IEntity;
import com.dianping.cat.home.service.IVisitor;
import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

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

   protected String toString(java.util.Date date, String format) {
      if (date != null) {
         return new java.text.SimpleDateFormat(format).format(date);
      } else {
         return null;
      }
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
   public void visitDomain(Domain domain) {
      attributes(null, ATTR_ID, domain.getId(), ELEMENT_TOTALCOUNT, domain.getTotalCount(), ELEMENT_FAILURECOUNT, domain.getFailureCount(), ELEMENT_FAILUREPERCENT, domain.getFailurePercent(), ELEMENT_SUM, domain.getSum(), ELEMENT_AVG, domain.getAvg(), ELEMENT_QPS, domain.getQps());
   }

   @Override
   public void visitServiceReport(ServiceReport serviceReport) {
      attributes(null, ATTR_STARTTIME, toString(serviceReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_DOMAIN, serviceReport.getDomain(), ATTR_ENDTIME, toString(serviceReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!serviceReport.getDomains().isEmpty()) {
         objectBegin(ENTITY_DOMAINS);

         for (Map.Entry<String, Domain> e : serviceReport.getDomains().entrySet()) {
            String key = String.valueOf(e.getKey());

            objectBegin(key);
            e.getValue().accept(m_visitor);
            objectEnd(key);
         }

         objectEnd(ENTITY_DOMAINS);
      }
   }
}
