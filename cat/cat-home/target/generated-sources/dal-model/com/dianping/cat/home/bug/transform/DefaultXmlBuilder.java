package com.dianping.cat.home.bug.transform;

import static com.dianping.cat.home.bug.Constants.ATTR_COUNT;
import static com.dianping.cat.home.bug.Constants.ATTR_DEPARTMENT;
import static com.dianping.cat.home.bug.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.bug.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.bug.Constants.ATTR_ID;
import static com.dianping.cat.home.bug.Constants.ATTR_PRODUCTLINE;
import static com.dianping.cat.home.bug.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.bug.Constants.ELEMENT_EXCPETION;
import static com.dianping.cat.home.bug.Constants.ELEMENT_MESSAGE;
import static com.dianping.cat.home.bug.Constants.ELEMENT_PROBLEM_URL;
import static com.dianping.cat.home.bug.Constants.ENTITY_BUG_REPORT;
import static com.dianping.cat.home.bug.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.home.bug.Constants.ENTITY_EXCEPTION_ITEM;

import com.dianping.cat.home.bug.IEntity;
import com.dianping.cat.home.bug.IVisitor;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

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
   public void visitBugReport(BugReport bugReport) {
      startTag(ENTITY_BUG_REPORT, null, ATTR_STARTTIME, toString(bugReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_DOMAIN, bugReport.getDomain(), ATTR_ENDTIME, toString(bugReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!bugReport.getDomains().isEmpty()) {
         for (Domain domain : bugReport.getDomains().values().toArray(new Domain[0])) {
            domain.accept(m_visitor);
         }
      }

      endTag(ENTITY_BUG_REPORT);
   }

   @Override
   public void visitDomain(Domain domain) {
      startTag(ENTITY_DOMAIN, null, ATTR_ID, domain.getId(), ATTR_DEPARTMENT, domain.getDepartment(), ATTR_PRODUCTLINE, domain.getProductLine());

      element(ELEMENT_PROBLEM_URL, domain.getProblemUrl(), true);

      element(ELEMENT_EXCPETION, domain.getExcpetion(), true);

      if (!domain.getExceptionItems().isEmpty()) {
         for (ExceptionItem exceptionItem : domain.getExceptionItems().values().toArray(new ExceptionItem[0])) {
            exceptionItem.accept(m_visitor);
         }
      }

      endTag(ENTITY_DOMAIN);
   }

   @Override
   public void visitExceptionItem(ExceptionItem exceptionItem) {
      startTag(ENTITY_EXCEPTION_ITEM, null, ATTR_ID, exceptionItem.getId(), ATTR_COUNT, exceptionItem.getCount());

      if (!exceptionItem.getMessages().isEmpty()) {
         for (String message : exceptionItem.getMessages().toArray(new String[0])) {
            tagWithText(ELEMENT_MESSAGE, message);
         }
      }

      endTag(ENTITY_EXCEPTION_ITEM);
   }
}
