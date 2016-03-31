package com.dianping.cat.consumer.transaction.model.transform;

import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_AVG;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_COUNT;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_DOMAIN;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_ENDTIME;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_FAILCOUNT;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_FAILPERCENT;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_FAILS;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_IP;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_LINE95VALUE;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_LINE99VALUE;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_MAX;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_MIN;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_STARTTIME;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_STD;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_SUM;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_SUM2;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_TOTALCOUNT;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_TPS;
import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_VALUE;
import static com.dianping.cat.consumer.transaction.model.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.consumer.transaction.model.Constants.ELEMENT_FAILMESSAGEURL;
import static com.dianping.cat.consumer.transaction.model.Constants.ELEMENT_IP;
import static com.dianping.cat.consumer.transaction.model.Constants.ELEMENT_SUCCESSMESSAGEURL;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_ALL_DURATION;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_DURATION;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_MACHINE;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_NAME;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_RANGE;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_RANGE2;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_TRANSACTION_REPORT;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_TYPE;

import com.dianping.cat.consumer.transaction.model.IEntity;
import com.dianping.cat.consumer.transaction.model.IVisitor;
import com.dianping.cat.consumer.transaction.model.entity.AllDuration;
import com.dianping.cat.consumer.transaction.model.entity.Duration;
import com.dianping.cat.consumer.transaction.model.entity.Machine;
import com.dianping.cat.consumer.transaction.model.entity.Range;
import com.dianping.cat.consumer.transaction.model.entity.Range2;
import com.dianping.cat.consumer.transaction.model.entity.TransactionName;
import com.dianping.cat.consumer.transaction.model.entity.TransactionReport;
import com.dianping.cat.consumer.transaction.model.entity.TransactionType;

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

   protected String toString(Number number, String format) {
      if (number != null) {
         return new java.text.DecimalFormat(format).format(number);
      } else {
         return null;
      }
   }

   @Override
   public void visitAllDuration(AllDuration allDuration) {
      startTag(ENTITY_ALL_DURATION, true, null, ATTR_VALUE, allDuration.getValue(), ATTR_COUNT, allDuration.getCount());
   }

   @Override
   public void visitDuration(Duration duration) {
      startTag(ENTITY_DURATION, true, null, ATTR_VALUE, duration.getValue(), ATTR_COUNT, duration.getCount());
   }

   @Override
   public void visitMachine(Machine machine) {
      startTag(ENTITY_MACHINE, null, ATTR_IP, machine.getIp());

      if (!machine.getTypes().isEmpty()) {
         for (TransactionType type : machine.getTypes().values().toArray(new TransactionType[0])) {
            type.accept(m_visitor);
         }
      }

      endTag(ENTITY_MACHINE);
   }

   @Override
   public void visitName(TransactionName name) {
      startTag(ENTITY_NAME, null, ATTR_ID, name.getId(), ATTR_TOTALCOUNT, name.getTotalCount(), ATTR_FAILCOUNT, name.getFailCount(), ATTR_FAILPERCENT, toString(name.getFailPercent(), "0.00"), ATTR_MIN, toString(name.getMin(), "0.00"), ATTR_MAX, toString(name.getMax(), "0.00"), ATTR_AVG, toString(name.getAvg(), "0.0"), ATTR_SUM, toString(name.getSum(), "0.0"), ATTR_SUM2, toString(name.getSum2(), "0.0"), ATTR_STD, toString(name.getStd(), "0.0"), ATTR_TPS, toString(name.getTps(), "0.00"), ATTR_LINE95VALUE, toString(name.getLine95Value(), "0.00"), ATTR_LINE99VALUE, toString(name.getLine99Value(), "0.00"));

      element(ELEMENT_SUCCESSMESSAGEURL, name.getSuccessMessageUrl(), true);

      element(ELEMENT_FAILMESSAGEURL, name.getFailMessageUrl(), true);

      if (!name.getRanges().isEmpty()) {
         for (Range range : name.getRanges().values().toArray(new Range[0])) {
            range.accept(m_visitor);
         }
      }

      if (!name.getDurations().isEmpty()) {
         for (Duration duration : name.getDurations().values().toArray(new Duration[0])) {
            duration.accept(m_visitor);
         }
      }

      endTag(ENTITY_NAME);
   }

   @Override
   public void visitRange(Range range) {
      startTag(ENTITY_RANGE, true, null, ATTR_VALUE, range.getValue(), ATTR_COUNT, range.getCount(), ATTR_SUM, range.getSum(), ATTR_AVG, toString(range.getAvg(), "0.0"), ATTR_FAILS, range.getFails());
   }

   @Override
   public void visitRange2(Range2 range2) {
      startTag(ENTITY_RANGE2, true, null, ATTR_VALUE, range2.getValue(), ATTR_COUNT, range2.getCount(), ATTR_SUM, range2.getSum(), ATTR_AVG, toString(range2.getAvg(), "0.0"), ATTR_FAILS, range2.getFails());
   }

   @Override
   public void visitTransactionReport(TransactionReport transactionReport) {
      startTag(ENTITY_TRANSACTION_REPORT, null, ATTR_DOMAIN, transactionReport.getDomain(), ATTR_STARTTIME, toString(transactionReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_ENDTIME, toString(transactionReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!transactionReport.getDomainNames().isEmpty()) {
         for (String domain : transactionReport.getDomainNames().toArray(new String[0])) {
            tagWithText(ELEMENT_DOMAIN, domain);
         }
      }

      if (!transactionReport.getIps().isEmpty()) {
         for (String ip : transactionReport.getIps().toArray(new String[0])) {
            tagWithText(ELEMENT_IP, ip);
         }
      }

      if (!transactionReport.getMachines().isEmpty()) {
         for (Machine machine : transactionReport.getMachines().values().toArray(new Machine[0])) {
            machine.accept(m_visitor);
         }
      }

      endTag(ENTITY_TRANSACTION_REPORT);
   }

   @Override
   public void visitType(TransactionType type) {
      startTag(ENTITY_TYPE, type.getDynamicAttributes(), ATTR_ID, type.getId(), ATTR_TOTALCOUNT, type.getTotalCount(), ATTR_FAILCOUNT, type.getFailCount(), ATTR_FAILPERCENT, toString(type.getFailPercent(), "0.00"), ATTR_MIN, toString(type.getMin(), "0.00"), ATTR_MAX, toString(type.getMax(), "0.00"), ATTR_AVG, toString(type.getAvg(), "0.0"), ATTR_SUM, toString(type.getSum(), "0.0"), ATTR_SUM2, toString(type.getSum2(), "0.0"), ATTR_STD, toString(type.getStd(), "0.0"), ATTR_TPS, toString(type.getTps(), "0.00"), ATTR_LINE95VALUE, toString(type.getLine95Value(), "0.00"), ATTR_LINE99VALUE, toString(type.getLine99Value(), "0.00"));

      element(ELEMENT_SUCCESSMESSAGEURL, type.getSuccessMessageUrl(), true);

      element(ELEMENT_FAILMESSAGEURL, type.getFailMessageUrl(), true);

      if (!type.getNames().isEmpty()) {
         for (TransactionName name : type.getNames().values().toArray(new TransactionName[0])) {
            name.accept(m_visitor);
         }
      }

      endTag(ENTITY_TYPE);
   }
}
