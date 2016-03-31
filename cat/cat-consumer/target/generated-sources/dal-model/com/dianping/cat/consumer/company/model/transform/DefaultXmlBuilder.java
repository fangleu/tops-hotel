package com.dianping.cat.consumer.company.model.transform;

import static com.dianping.cat.consumer.company.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.company.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.company.model.Constants.ATTR_ORDER;
import static com.dianping.cat.consumer.company.model.Constants.ATTR_TITLE;
import static com.dianping.cat.consumer.company.model.Constants.ENTITY_COMPANY;
import static com.dianping.cat.consumer.company.model.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.consumer.company.model.Constants.ENTITY_PRODUCT_LINE;

import com.dianping.cat.consumer.company.model.IEntity;
import com.dianping.cat.consumer.company.model.IVisitor;
import com.dianping.cat.consumer.company.model.entity.Company;
import com.dianping.cat.consumer.company.model.entity.Domain;
import com.dianping.cat.consumer.company.model.entity.ProductLine;

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
   public void visitCompany(Company company) {
      startTag(ENTITY_COMPANY, null, ATTR_NAME, company.getName());

      if (!company.getProductLines().isEmpty()) {
         for (ProductLine productLine : company.getProductLines().values().toArray(new ProductLine[0])) {
            productLine.accept(m_visitor);
         }
      }

      endTag(ENTITY_COMPANY);
   }

   @Override
   public void visitDomain(Domain domain) {
      startTag(ENTITY_DOMAIN, true, null, ATTR_ID, domain.getId());
   }

   @Override
   public void visitProductLine(ProductLine productLine) {
      startTag(ENTITY_PRODUCT_LINE, null, ATTR_ID, productLine.getId(), ATTR_ORDER, productLine.getOrder(), ATTR_TITLE, productLine.getTitle());

      if (!productLine.getDomains().isEmpty()) {
         for (Domain domain : productLine.getDomains().values().toArray(new Domain[0])) {
            domain.accept(m_visitor);
         }
      }

      endTag(ENTITY_PRODUCT_LINE);
   }
}
