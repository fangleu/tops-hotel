package com.dianping.cat.home.app.transform;

import static com.dianping.cat.home.app.Constants.ATTR_AVG;
import static com.dianping.cat.home.app.Constants.ATTR_COUNT;
import static com.dianping.cat.home.app.Constants.ATTR_END_TIME;
import static com.dianping.cat.home.app.Constants.ATTR_ERRORS;
import static com.dianping.cat.home.app.Constants.ATTR_ID;
import static com.dianping.cat.home.app.Constants.ATTR_NAME;
import static com.dianping.cat.home.app.Constants.ATTR_REQUESTAVG;
import static com.dianping.cat.home.app.Constants.ATTR_REQUESTSUM;
import static com.dianping.cat.home.app.Constants.ATTR_RESPONSEAVG;
import static com.dianping.cat.home.app.Constants.ATTR_RESPONSESUM;
import static com.dianping.cat.home.app.Constants.ATTR_START_TIME;
import static com.dianping.cat.home.app.Constants.ATTR_SUCCESSRATIO;
import static com.dianping.cat.home.app.Constants.ATTR_SUM;
import static com.dianping.cat.home.app.Constants.ATTR_URL;
import static com.dianping.cat.home.app.Constants.ENTITY_APP_REPORT;
import static com.dianping.cat.home.app.Constants.ENTITY_CODE;
import static com.dianping.cat.home.app.Constants.ENTITY_COMMAND;
import static com.dianping.cat.home.app.Constants.ENTITY_TRANSACTION;

import com.dianping.cat.home.app.IEntity;
import com.dianping.cat.home.app.IVisitor;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

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
   public void visitAppReport(AppReport appReport) {
      startTag(ENTITY_APP_REPORT, null, ATTR_ID, appReport.getId(), ATTR_START_TIME, toString(appReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_END_TIME, toString(appReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!appReport.getCommands().isEmpty()) {
         for (Command command : appReport.getCommands().values().toArray(new Command[0])) {
            command.accept(m_visitor);
         }
      }

      endTag(ENTITY_APP_REPORT);
   }

   @Override
   public void visitCode(Code code) {
      startTag(ENTITY_CODE, true, null, ATTR_ID, code.getId(), ATTR_COUNT, code.getCount(), ATTR_SUM, toString(code.getSum(), "0.00"), ATTR_AVG, toString(code.getAvg(), "0.00"), ATTR_ERRORS, code.getErrors(), ATTR_SUCCESSRATIO, toString(code.getSuccessRatio(), "0.000"));
   }

   @Override
   public void visitCommand(Command command) {
      startTag(ENTITY_COMMAND, null, ATTR_ID, command.getId(), ATTR_NAME, command.getName(), ATTR_COUNT, command.getCount(), ATTR_SUM, toString(command.getSum(), "0.00"), ATTR_AVG, toString(command.getAvg(), "0.00"), ATTR_ERRORS, command.getErrors(), ATTR_SUCCESSRATIO, toString(command.getSuccessRatio(), "0.000"), ATTR_REQUESTSUM, command.getRequestSum(), ATTR_REQUESTAVG, toString(command.getRequestAvg(), "0.00"), ATTR_RESPONSESUM, command.getResponseSum(), ATTR_RESPONSEAVG, toString(command.getResponseAvg(), "0.00"));

      if (command.getTransaction() != null) {
         command.getTransaction().accept(m_visitor);
      }

      if (!command.getCodes().isEmpty()) {
         for (Code code : command.getCodes().values().toArray(new Code[0])) {
            code.accept(m_visitor);
         }
      }

      endTag(ENTITY_COMMAND);
   }

   @Override
   public void visitTransaction(Transaction transaction) {
      startTag(ENTITY_TRANSACTION, true, null, ATTR_URL, transaction.getUrl(), ATTR_COUNT, transaction.getCount(), ATTR_AVG, toString(transaction.getAvg(), "0.00"));
   }
}
