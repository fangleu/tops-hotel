package com.dianping.cat.configuration.app.command.transform;

import static com.dianping.cat.configuration.app.command.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PATTERN;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PICLARGE;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PICMIDDEL;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PICSMALL;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_TYPE;
import static com.dianping.cat.configuration.app.command.Constants.ENTITY_COMMAND;
import static com.dianping.cat.configuration.app.command.Constants.ENTITY_COMMAND_FORMAT;
import static com.dianping.cat.configuration.app.command.Constants.ENTITY_RULE;

import com.dianping.cat.configuration.app.command.IEntity;
import com.dianping.cat.configuration.app.command.IVisitor;
import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

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
   public void visitCommand(Command command) {
      startTag(ENTITY_COMMAND, true, null, ATTR_ID, command.getId());
   }

   @Override
   public void visitCommandFormat(CommandFormat commandFormat) {
      startTag(ENTITY_COMMAND_FORMAT, null, ATTR_PICSMALL, commandFormat.getPicSmall(), ATTR_PICMIDDEL, commandFormat.getPicMiddel(), ATTR_PICLARGE, commandFormat.getPicLarge());

      if (!commandFormat.getRules().isEmpty()) {
         for (Rule rule : commandFormat.getRules().toArray(new Rule[0])) {
            rule.accept(m_visitor);
         }
      }

      endTag(ENTITY_COMMAND_FORMAT);
   }

   @Override
   public void visitRule(Rule rule) {
      startTag(ENTITY_RULE, null, ATTR_PATTERN, rule.getPattern(), ATTR_TYPE, rule.getType());

      if (!rule.getCommands().isEmpty()) {
         for (Command command : rule.getCommands().toArray(new Command[0])) {
            command.accept(m_visitor);
         }
      }

      endTag(ENTITY_RULE);
   }
}
