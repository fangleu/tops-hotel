package com.dianping.cat.configuration.server.filter.transform;

import static com.dianping.cat.configuration.server.filter.Constants.ATTR_ID;
import static com.dianping.cat.configuration.server.filter.Constants.ATTR_TITLE;
import static com.dianping.cat.configuration.server.filter.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.configuration.server.filter.Constants.ELEMENT_TRANSACTION_NAME;
import static com.dianping.cat.configuration.server.filter.Constants.ELEMENT_TRANSACTION_TYPE;
import static com.dianping.cat.configuration.server.filter.Constants.ENTITY_CRASH_LOG_DOMAIN;
import static com.dianping.cat.configuration.server.filter.Constants.ENTITY_SERVER_FILTER_CONFIG;

import com.dianping.cat.configuration.server.filter.IEntity;
import com.dianping.cat.configuration.server.filter.IVisitor;
import com.dianping.cat.configuration.server.filter.entity.CrashLogDomain;
import com.dianping.cat.configuration.server.filter.entity.ServerFilterConfig;

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

   @Override
   public void visitCrashLogDomain(CrashLogDomain crashLogDomain) {
      startTag(ENTITY_CRASH_LOG_DOMAIN, true, null, ATTR_ID, crashLogDomain.getId(), ATTR_TITLE, crashLogDomain.getTitle());
   }

   @Override
   public void visitServerFilterConfig(ServerFilterConfig serverFilterConfig) {
      startTag(ENTITY_SERVER_FILTER_CONFIG, null);

      if (!serverFilterConfig.getTransactionTypes().isEmpty()) {
         for (String transactionType : serverFilterConfig.getTransactionTypes().toArray(new String[0])) {
            tagWithText(ELEMENT_TRANSACTION_TYPE, transactionType);
         }
      }

      if (!serverFilterConfig.getTransactionNames().isEmpty()) {
         for (String transactionName : serverFilterConfig.getTransactionNames().toArray(new String[0])) {
            tagWithText(ELEMENT_TRANSACTION_NAME, transactionName);
         }
      }

      if (!serverFilterConfig.getDomains().isEmpty()) {
         for (String domain : serverFilterConfig.getDomains().toArray(new String[0])) {
            tagWithText(ELEMENT_DOMAIN, domain);
         }
      }

      if (!serverFilterConfig.getCrashLogDomains().isEmpty()) {
         for (CrashLogDomain crashLogDomain : serverFilterConfig.getCrashLogDomains().values().toArray(new CrashLogDomain[0])) {
            crashLogDomain.accept(m_visitor);
         }
      }

      endTag(ENTITY_SERVER_FILTER_CONFIG);
   }
}
