package com.dianping.cat.home.utilization.transform;

import static com.dianping.cat.home.utilization.Constants.ATTR_AVG;
import static com.dianping.cat.home.utilization.Constants.ATTR_AVG_MAX;
import static com.dianping.cat.home.utilization.Constants.ATTR_AVG95;
import static com.dianping.cat.home.utilization.Constants.ATTR_CMDB_ID;
import static com.dianping.cat.home.utilization.Constants.ATTR_COUNT;
import static com.dianping.cat.home.utilization.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.utilization.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.utilization.Constants.ATTR_FAILURE_COUNT;
import static com.dianping.cat.home.utilization.Constants.ATTR_FAILURE_PERCENT;
import static com.dianping.cat.home.utilization.Constants.ATTR_ID;
import static com.dianping.cat.home.utilization.Constants.ATTR_MACHINE_NUMBER;
import static com.dianping.cat.home.utilization.Constants.ATTR_MAXQPS;
import static com.dianping.cat.home.utilization.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.utilization.Constants.ATTR_SUM;
import static com.dianping.cat.home.utilization.Constants.ENTITY_APPLICATIONSTATE;
import static com.dianping.cat.home.utilization.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.home.utilization.Constants.ENTITY_MACHINESTATE;
import static com.dianping.cat.home.utilization.Constants.ENTITY_UTILIZATION_REPORT;

import com.dianping.cat.home.utilization.IEntity;
import com.dianping.cat.home.utilization.IVisitor;
import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

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

   protected String toString(Number number, String format) {
      if (number != null) {
         return new java.text.DecimalFormat(format).format(number);
      } else {
         return null;
      }
   }

   @Override
   public void visitApplicationState(ApplicationState applicationState) {
      startTag(ENTITY_APPLICATIONSTATE, true, null, ATTR_ID, applicationState.getId(), ATTR_FAILURE_COUNT, applicationState.getFailureCount(), ATTR_FAILURE_PERCENT, toString(applicationState.getFailurePercent(), "0.000000"), ATTR_COUNT, applicationState.getCount(), ATTR_MAXQPS, toString(applicationState.getMaxQps(), "0.0000"), ATTR_AVG, toString(applicationState.getAvg(), "0.0000"), ATTR_SUM, toString(applicationState.getSum(), "0.0000"), ATTR_AVG95, toString(applicationState.getAvg95(), "0.0000"));
   }

   @Override
   public void visitDomain(Domain domain) {
      startTag(ENTITY_DOMAIN, null, ATTR_ID, domain.getId(), ATTR_MACHINE_NUMBER, domain.getMachineNumber(), ATTR_CMDB_ID, domain.getCmdbId());

      if (!domain.getMachineStates().isEmpty()) {
         for (MachineState machineState : domain.getMachineStates().values().toArray(new MachineState[0])) {
            machineState.accept(m_visitor);
         }
      }

      if (!domain.getApplicationStates().isEmpty()) {
         for (ApplicationState applicationState : domain.getApplicationStates().values().toArray(new ApplicationState[0])) {
            applicationState.accept(m_visitor);
         }
      }

      endTag(ENTITY_DOMAIN);
   }

   @Override
   public void visitMachineState(MachineState machineState) {
      startTag(ENTITY_MACHINESTATE, true, null, ATTR_ID, machineState.getId(), ATTR_COUNT, machineState.getCount(), ATTR_SUM, toString(machineState.getSum(), "0.0000"), ATTR_AVG, toString(machineState.getAvg(), "0.0000"), ATTR_AVG_MAX, toString(machineState.getAvgMax(), "0.0000"));
   }

   @Override
   public void visitUtilizationReport(UtilizationReport utilizationReport) {
      startTag(ENTITY_UTILIZATION_REPORT, null, ATTR_DOMAIN, utilizationReport.getDomain(), ATTR_STARTTIME, toString(utilizationReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_ENDTIME, toString(utilizationReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!utilizationReport.getDomains().isEmpty()) {
         for (Domain domain : utilizationReport.getDomains().values().toArray(new Domain[0])) {
            domain.accept(m_visitor);
         }
      }

      endTag(ENTITY_UTILIZATION_REPORT);
   }
}
