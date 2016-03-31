package com.dianping.cat.configuration.web.url.transform;

import static com.dianping.cat.configuration.web.url.Constants.ATTR_DOMAIN;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_GROUP;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_ID;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_PATTERN;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_STATUS;
import static com.dianping.cat.configuration.web.url.Constants.ENTITY_CODE;
import static com.dianping.cat.configuration.web.url.Constants.ENTITY_PATTERN_ITEM;
import static com.dianping.cat.configuration.web.url.Constants.ENTITY_URL_PATTERN;

import com.dianping.cat.configuration.web.url.IEntity;
import com.dianping.cat.configuration.web.url.IVisitor;
import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

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
   public void visitCode(Code code) {
      startTag(ENTITY_CODE, true, null, ATTR_ID, code.getId(), ATTR_NAME, code.getName(), ATTR_STATUS, code.getStatus());
   }

   @Override
   public void visitPatternItem(PatternItem patternItem) {
      startTag(ENTITY_PATTERN_ITEM, true, null, ATTR_GROUP, patternItem.getGroup(), ATTR_NAME, patternItem.getName(), ATTR_PATTERN, patternItem.getPattern(), ATTR_DOMAIN, patternItem.getDomain(), ATTR_ID, patternItem.getId());
   }

   @Override
   public void visitUrlPattern(UrlPattern urlPattern) {
      startTag(ENTITY_URL_PATTERN, null);

      if (!urlPattern.getPatternItems().isEmpty()) {
         for (PatternItem patternItem : urlPattern.getPatternItems().values().toArray(new PatternItem[0])) {
            patternItem.accept(m_visitor);
         }
      }

      if (!urlPattern.getCodes().isEmpty()) {
         for (Code code : urlPattern.getCodes().values().toArray(new Code[0])) {
            code.accept(m_visitor);
         }
      }

      endTag(ENTITY_URL_PATTERN);
   }
}
