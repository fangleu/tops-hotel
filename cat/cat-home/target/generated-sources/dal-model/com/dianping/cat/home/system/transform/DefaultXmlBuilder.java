package com.dianping.cat.home.system.transform;

import static com.dianping.cat.home.system.Constants.ATTR_AVG;
import static com.dianping.cat.home.system.Constants.ATTR_COUNT;
import static com.dianping.cat.home.system.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.system.Constants.ATTR_ID;
import static com.dianping.cat.home.system.Constants.ATTR_IP;
import static com.dianping.cat.home.system.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.system.Constants.ATTR_SUM;
import static com.dianping.cat.home.system.Constants.ENTITY_DAY;
import static com.dianping.cat.home.system.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.home.system.Constants.ENTITY_ENTITY;
import static com.dianping.cat.home.system.Constants.ENTITY_MACHINE;
import static com.dianping.cat.home.system.Constants.ENTITY_RUSH;
import static com.dianping.cat.home.system.Constants.ENTITY_SYSTEM_REPORT;

import com.dianping.cat.home.system.IEntity;
import com.dianping.cat.home.system.IVisitor;
import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

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
   public void visitDay(Day day) {
      startTag(ENTITY_DAY, true, null, ATTR_COUNT, day.getCount(), ATTR_SUM, day.getSum(), ATTR_AVG, day.getAvg());
   }

   @Override
   public void visitDomain(Domain domain) {
      startTag(ENTITY_DOMAIN, null, ATTR_ID, domain.getId());

      if (!domain.getEntities().isEmpty()) {
         for (Entity entity : domain.getEntities().values().toArray(new Entity[0])) {
            entity.accept(m_visitor);
         }
      }

      endTag(ENTITY_DOMAIN);
   }

   @Override
   public void visitEntity(Entity entity) {
      startTag(ENTITY_ENTITY, null, ATTR_ID, entity.getId());

      if (entity.getRush() != null) {
         entity.getRush().accept(m_visitor);
      }

      if (entity.getDay() != null) {
         entity.getDay().accept(m_visitor);
      }

      if (!entity.getMachines().isEmpty()) {
         for (Machine machine : entity.getMachines().values().toArray(new Machine[0])) {
            machine.accept(m_visitor);
         }
      }

      endTag(ENTITY_ENTITY);
   }

   @Override
   public void visitMachine(Machine machine) {
      startTag(ENTITY_MACHINE, true, null, ATTR_IP, machine.getIp());
   }

   @Override
   public void visitRush(Rush rush) {
      startTag(ENTITY_RUSH, true, null, ATTR_COUNT, rush.getCount(), ATTR_SUM, rush.getSum(), ATTR_AVG, rush.getAvg());
   }

   @Override
   public void visitSystemReport(SystemReport systemReport) {
      startTag(ENTITY_SYSTEM_REPORT, null, ATTR_STARTTIME, toString(systemReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_ENDTIME, toString(systemReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!systemReport.getDomains().isEmpty()) {
         for (Domain domain : systemReport.getDomains().values().toArray(new Domain[0])) {
            domain.accept(m_visitor);
         }
      }

      endTag(ENTITY_SYSTEM_REPORT);
   }
}
