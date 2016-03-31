package com.dianping.cat.home.alert.config.transform;

import static com.dianping.cat.home.alert.config.Constants.ATTR_ENABLE;
import static com.dianping.cat.home.alert.config.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_EMAIL;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_PHONE;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_WEIXIN;
import static com.dianping.cat.home.alert.config.Constants.ENTITY_ALERT_CONFIG;
import static com.dianping.cat.home.alert.config.Constants.ENTITY_RECEIVER;

import com.dianping.cat.home.alert.config.IEntity;
import com.dianping.cat.home.alert.config.IVisitor;
import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

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
   public void visitAlertConfig(AlertConfig alertConfig) {
      startTag(ENTITY_ALERT_CONFIG, null, ATTR_ENABLE, alertConfig.getEnable());

      if (!alertConfig.getReceivers().isEmpty()) {
         for (Receiver receiver : alertConfig.getReceivers().values().toArray(new Receiver[0])) {
            receiver.accept(m_visitor);
         }
      }

      endTag(ENTITY_ALERT_CONFIG);
   }

   @Override
   public void visitReceiver(Receiver receiver) {
      startTag(ENTITY_RECEIVER, null, ATTR_ID, receiver.getId(), ATTR_ENABLE, receiver.getEnable());

      if (!receiver.getEmails().isEmpty()) {
         for (String email : receiver.getEmails().toArray(new String[0])) {
            tagWithText(ELEMENT_EMAIL, email);
         }
      }

      if (!receiver.getPhones().isEmpty()) {
         for (String phone : receiver.getPhones().toArray(new String[0])) {
            tagWithText(ELEMENT_PHONE, phone);
         }
      }

      if (!receiver.getWeixins().isEmpty()) {
         for (String weixin : receiver.getWeixins().toArray(new String[0])) {
            tagWithText(ELEMENT_WEIXIN, weixin);
         }
      }

      endTag(ENTITY_RECEIVER);
   }
}
