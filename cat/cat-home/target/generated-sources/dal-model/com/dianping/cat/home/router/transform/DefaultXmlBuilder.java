package com.dianping.cat.home.router.transform;

import static com.dianping.cat.home.router.Constants.ATTR_BACKUP_SERVER;
import static com.dianping.cat.home.router.Constants.ATTR_BACKUP_SERVER_PORT;
import static com.dianping.cat.home.router.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.router.Constants.ATTR_ENABLE;
import static com.dianping.cat.home.router.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.router.Constants.ATTR_ID;
import static com.dianping.cat.home.router.Constants.ATTR_PORT;
import static com.dianping.cat.home.router.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.router.Constants.ATTR_WEIGHT;
import static com.dianping.cat.home.router.Constants.ENTITY_DEFAULT_SERVER;
import static com.dianping.cat.home.router.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.home.router.Constants.ENTITY_ROUTER_CONFIG;
import static com.dianping.cat.home.router.Constants.ENTITY_SERVER;

import com.dianping.cat.home.router.IEntity;
import com.dianping.cat.home.router.IVisitor;
import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

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
   public void visitDefaultServer(DefaultServer defaultServer) {
      startTag(ENTITY_DEFAULT_SERVER, true, null, ATTR_ID, defaultServer.getId(), ATTR_PORT, defaultServer.getPort(), ATTR_ENABLE, defaultServer.isEnable(), ATTR_WEIGHT, defaultServer.getWeight());
   }

   @Override
   public void visitDomain(Domain domain) {
      startTag(ENTITY_DOMAIN, null, ATTR_ID, domain.getId());

      if (!domain.getServers().isEmpty()) {
         for (Server server : domain.getServers().toArray(new Server[0])) {
            server.accept(m_visitor);
         }
      }

      endTag(ENTITY_DOMAIN);
   }

   @Override
   public void visitRouterConfig(RouterConfig routerConfig) {
      startTag(ENTITY_ROUTER_CONFIG, null, ATTR_STARTTIME, toString(routerConfig.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_DOMAIN, routerConfig.getDomain(), ATTR_BACKUP_SERVER, routerConfig.getBackupServer(), ATTR_BACKUP_SERVER_PORT, routerConfig.getBackupServerPort(), ATTR_ENDTIME, toString(routerConfig.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!routerConfig.getDefaultServers().isEmpty()) {
         for (DefaultServer defaultServer : routerConfig.getDefaultServers().toArray(new DefaultServer[0])) {
            defaultServer.accept(m_visitor);
         }
      }

      if (!routerConfig.getDomains().isEmpty()) {
         for (Domain domain : routerConfig.getDomains().values().toArray(new Domain[0])) {
            domain.accept(m_visitor);
         }
      }

      endTag(ENTITY_ROUTER_CONFIG);
   }

   @Override
   public void visitServer(Server server) {
      startTag(ENTITY_SERVER, true, null, ATTR_ID, server.getId(), ATTR_PORT, server.getPort(), ATTR_WEIGHT, server.getWeight());
   }
}
