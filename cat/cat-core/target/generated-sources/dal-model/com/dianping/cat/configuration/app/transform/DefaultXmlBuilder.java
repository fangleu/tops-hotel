package com.dianping.cat.configuration.app.transform;

import static com.dianping.cat.configuration.app.Constants.ATTR_ALL;
import static com.dianping.cat.configuration.app.Constants.ATTR_DES;
import static com.dianping.cat.configuration.app.Constants.ATTR_DOMAIN;
import static com.dianping.cat.configuration.app.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.app.Constants.ATTR_STATUS;
import static com.dianping.cat.configuration.app.Constants.ATTR_THRESHOLD;
import static com.dianping.cat.configuration.app.Constants.ATTR_TITLE;
import static com.dianping.cat.configuration.app.Constants.ENTITY_APP_CONFIG;
import static com.dianping.cat.configuration.app.Constants.ENTITY_CODE;
import static com.dianping.cat.configuration.app.Constants.ENTITY_COMMAND;
import static com.dianping.cat.configuration.app.Constants.ENTITY_CONFIG_ITEM;
import static com.dianping.cat.configuration.app.Constants.ENTITY_ITEM;

import com.dianping.cat.configuration.app.IEntity;
import com.dianping.cat.configuration.app.IVisitor;
import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

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
   public void visitAppConfig(AppConfig appConfig) {
      startTag(ENTITY_APP_CONFIG, null);

      if (!appConfig.getConfigItems().isEmpty()) {
         for (ConfigItem configItem : appConfig.getConfigItems().values().toArray(new ConfigItem[0])) {
            configItem.accept(m_visitor);
         }
      }

      if (!appConfig.getCodes().isEmpty()) {
         for (Code code : appConfig.getCodes().values().toArray(new Code[0])) {
            code.accept(m_visitor);
         }
      }

      if (!appConfig.getCommands().isEmpty()) {
         for (Command command : appConfig.getCommands().values().toArray(new Command[0])) {
            command.accept(m_visitor);
         }
      }

      endTag(ENTITY_APP_CONFIG);
   }

   @Override
   public void visitCode(Code code) {
      startTag(ENTITY_CODE, true, null, ATTR_ID, code.getId(), ATTR_NAME, code.getName(), ATTR_STATUS, code.getStatus());
   }

   @Override
   public void visitCommand(Command command) {
      startTag(ENTITY_COMMAND, null, ATTR_ID, command.getId(), ATTR_NAME, command.getName(), ATTR_DOMAIN, command.getDomain(), ATTR_TITLE, command.getTitle(), ATTR_ALL, command.isAll(), ATTR_THRESHOLD, command.getThreshold());

      if (!command.getCodes().isEmpty()) {
         for (Code code : command.getCodes().values().toArray(new Code[0])) {
            code.accept(m_visitor);
         }
      }

      endTag(ENTITY_COMMAND);
   }

   @Override
   public void visitConfigItem(ConfigItem configItem) {
      startTag(ENTITY_CONFIG_ITEM, null, ATTR_ID, configItem.getId());

      if (!configItem.getItems().isEmpty()) {
         for (Item item : configItem.getItems().values().toArray(new Item[0])) {
            item.accept(m_visitor);
         }
      }

      endTag(ENTITY_CONFIG_ITEM);
   }

   @Override
   public void visitItem(Item item) {
      startTag(ENTITY_ITEM, true, null, ATTR_ID, item.getId(), ATTR_NAME, item.getName(), ATTR_DES, item.getDes());
   }
}
