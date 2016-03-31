package com.dianping.cat.home.alert.policy.transform;

import static com.dianping.cat.home.alert.policy.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.policy.Constants.ATTR_RECOVERMINUTE;
import static com.dianping.cat.home.alert.policy.Constants.ATTR_SEND;
import static com.dianping.cat.home.alert.policy.Constants.ATTR_SUSPENDMINUTE;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_ALERT_POLICY;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_GROUP;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_LEVEL;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_TYPE;

import com.dianping.cat.home.alert.policy.IEntity;
import com.dianping.cat.home.alert.policy.IVisitor;
import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

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
   public void visitAlertPolicy(AlertPolicy alertPolicy) {
      startTag(ENTITY_ALERT_POLICY, null);

      if (!alertPolicy.getTypes().isEmpty()) {
         for (Type type : alertPolicy.getTypes().values().toArray(new Type[0])) {
            type.accept(m_visitor);
         }
      }

      endTag(ENTITY_ALERT_POLICY);
   }

   @Override
   public void visitGroup(Group group) {
      startTag(ENTITY_GROUP, null, ATTR_ID, group.getId());

      if (!group.getLevels().isEmpty()) {
         for (Level level : group.getLevels().values().toArray(new Level[0])) {
            level.accept(m_visitor);
         }
      }

      endTag(ENTITY_GROUP);
   }

   @Override
   public void visitLevel(Level level) {
      startTag(ENTITY_LEVEL, true, null, ATTR_ID, level.getId(), ATTR_SEND, level.getSend(), ATTR_SUSPENDMINUTE, level.getSuspendMinute(), ATTR_RECOVERMINUTE, level.getRecoverMinute());
   }

   @Override
   public void visitType(Type type) {
      startTag(ENTITY_TYPE, null, ATTR_ID, type.getId());

      if (!type.getGroups().isEmpty()) {
         for (Group group : type.getGroups().values().toArray(new Group[0])) {
            group.accept(m_visitor);
         }
      }

      endTag(ENTITY_TYPE);
   }
}
