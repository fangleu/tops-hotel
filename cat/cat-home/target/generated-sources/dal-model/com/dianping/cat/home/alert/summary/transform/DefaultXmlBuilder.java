package com.dianping.cat.home.alert.summary.transform;

import static com.dianping.cat.home.alert.summary.Constants.ATTR_ALERT_DATE;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_ALERTTIME;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_CONTEXT;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_COUNT;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_METRIC;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_NAME;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_TYPE;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_ALERT;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_ALERT_SUMMARY;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_CATEGORY;

import com.dianping.cat.home.alert.summary.IEntity;
import com.dianping.cat.home.alert.summary.IVisitor;
import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

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

   protected String toString(java.util.Date date, String format) {
      if (date != null) {
         return new java.text.SimpleDateFormat(format).format(date);
      } else {
         return null;
      }
   }

   @Override
   public void visitAlert(Alert alert) {
      startTag(ENTITY_ALERT, true, null, ATTR_ALERTTIME, toString(alert.getAlertTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_TYPE, alert.getType(), ATTR_METRIC, alert.getMetric(), ATTR_CONTEXT, alert.getContext(), ATTR_DOMAIN, alert.getDomain(), ATTR_COUNT, alert.getCount());
   }

   @Override
   public void visitAlertSummary(AlertSummary alertSummary) {
      startTag(ENTITY_ALERT_SUMMARY, null, ATTR_ALERT_DATE, toString(alertSummary.getAlertDate(), "yyyy-MM-dd HH:mm:ss"), ATTR_DOMAIN, alertSummary.getDomain());

      if (!alertSummary.getCategories().isEmpty()) {
         for (Category category : alertSummary.getCategories().values().toArray(new Category[0])) {
            category.accept(m_visitor);
         }
      }

      endTag(ENTITY_ALERT_SUMMARY);
   }

   @Override
   public void visitCategory(Category category) {
      startTag(ENTITY_CATEGORY, null, ATTR_NAME, category.getName());

      if (!category.getAlerts().isEmpty()) {
         for (Alert alert : category.getAlerts().toArray(new Alert[0])) {
            alert.accept(m_visitor);
         }
      }

      endTag(ENTITY_CATEGORY);
   }
}
