package com.dianping.cat.home.alert.thirdparty.transform;

import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_IP;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_PORT;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_TYPE;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_URL;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_HTTP;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_PAR;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_SOCKET;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_THIRD_PARTY_CONFIG;

import com.dianping.cat.home.alert.thirdparty.IEntity;
import com.dianping.cat.home.alert.thirdparty.IVisitor;
import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

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
   public void visitHttp(Http http) {
      startTag(ENTITY_HTTP, null, ATTR_URL, http.getUrl(), ATTR_TYPE, http.getType(), ATTR_DOMAIN, http.getDomain());

      if (!http.getPars().isEmpty()) {
         for (Par par : http.getPars().toArray(new Par[0])) {
            par.accept(m_visitor);
         }
      }

      endTag(ENTITY_HTTP);
   }

   @Override
   public void visitPar(Par par) {
      startTag(ENTITY_PAR, true, null, ATTR_ID, par.getId());
   }

   @Override
   public void visitSocket(Socket socket) {
      startTag(ENTITY_SOCKET, true, null, ATTR_IP, socket.getIp(), ATTR_PORT, socket.getPort(), ATTR_DOMAIN, socket.getDomain());
   }

   @Override
   public void visitThirdPartyConfig(ThirdPartyConfig thirdPartyConfig) {
      startTag(ENTITY_THIRD_PARTY_CONFIG, null);

      if (!thirdPartyConfig.getHttps().isEmpty()) {
         for (Http http : thirdPartyConfig.getHttps().toArray(new Http[0])) {
            http.accept(m_visitor);
         }
      }

      if (!thirdPartyConfig.getSockets().isEmpty()) {
         for (Socket socket : thirdPartyConfig.getSockets().toArray(new Socket[0])) {
            socket.accept(m_visitor);
         }
      }

      endTag(ENTITY_THIRD_PARTY_CONFIG);
   }
}
