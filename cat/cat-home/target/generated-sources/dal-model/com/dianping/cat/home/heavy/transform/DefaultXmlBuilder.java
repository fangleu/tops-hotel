package com.dianping.cat.home.heavy.transform;

import static com.dianping.cat.home.heavy.Constants.ATTR_COUNT;
import static com.dianping.cat.home.heavy.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.heavy.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.heavy.Constants.ATTR_KEY;
import static com.dianping.cat.home.heavy.Constants.ATTR_LOGVIEW;
import static com.dianping.cat.home.heavy.Constants.ATTR_NAME;
import static com.dianping.cat.home.heavy.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_CACHE;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_CALL;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_REPORT;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_SQL;
import static com.dianping.cat.home.heavy.Constants.ENTITY_SERVICE;
import static com.dianping.cat.home.heavy.Constants.ENTITY_URL;

import com.dianping.cat.home.heavy.IEntity;
import com.dianping.cat.home.heavy.IVisitor;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

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
   public void visitHeavyCache(HeavyCache heavyCache) {
      startTag(ENTITY_HEAVY_CACHE, null);

      if (!heavyCache.getUrls().isEmpty()) {
         for (Url url : heavyCache.getUrls().values().toArray(new Url[0])) {
            url.accept(m_visitor);
         }
      }

      if (!heavyCache.getServices().isEmpty()) {
         for (Service service : heavyCache.getServices().values().toArray(new Service[0])) {
            service.accept(m_visitor);
         }
      }

      endTag(ENTITY_HEAVY_CACHE);
   }

   @Override
   public void visitHeavyCall(HeavyCall heavyCall) {
      startTag(ENTITY_HEAVY_CALL, null);

      if (!heavyCall.getUrls().isEmpty()) {
         for (Url url : heavyCall.getUrls().values().toArray(new Url[0])) {
            url.accept(m_visitor);
         }
      }

      if (!heavyCall.getServices().isEmpty()) {
         for (Service service : heavyCall.getServices().values().toArray(new Service[0])) {
            service.accept(m_visitor);
         }
      }

      endTag(ENTITY_HEAVY_CALL);
   }

   @Override
   public void visitHeavyReport(HeavyReport heavyReport) {
      startTag(ENTITY_HEAVY_REPORT, null, ATTR_DOMAIN, heavyReport.getDomain(), ATTR_STARTTIME, toString(heavyReport.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_ENDTIME, toString(heavyReport.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (heavyReport.getHeavySql() != null) {
         heavyReport.getHeavySql().accept(m_visitor);
      }

      if (heavyReport.getHeavyCall() != null) {
         heavyReport.getHeavyCall().accept(m_visitor);
      }

      if (heavyReport.getHeavyCache() != null) {
         heavyReport.getHeavyCache().accept(m_visitor);
      }

      endTag(ENTITY_HEAVY_REPORT);
   }

   @Override
   public void visitHeavySql(HeavySql heavySql) {
      startTag(ENTITY_HEAVY_SQL, null);

      if (!heavySql.getUrls().isEmpty()) {
         for (Url url : heavySql.getUrls().values().toArray(new Url[0])) {
            url.accept(m_visitor);
         }
      }

      if (!heavySql.getServices().isEmpty()) {
         for (Service service : heavySql.getServices().values().toArray(new Service[0])) {
            service.accept(m_visitor);
         }
      }

      endTag(ENTITY_HEAVY_SQL);
   }

   @Override
   public void visitService(Service service) {
      startTag(ENTITY_SERVICE, true, null, ATTR_DOMAIN, service.getDomain(), ATTR_NAME, service.getName(), ATTR_LOGVIEW, service.getLogview(), ATTR_COUNT, service.getCount(), ATTR_KEY, service.getKey());
   }

   @Override
   public void visitUrl(Url url) {
      startTag(ENTITY_URL, true, null, ATTR_DOMAIN, url.getDomain(), ATTR_NAME, url.getName(), ATTR_LOGVIEW, url.getLogview(), ATTR_COUNT, url.getCount(), ATTR_KEY, url.getKey());
   }
}
