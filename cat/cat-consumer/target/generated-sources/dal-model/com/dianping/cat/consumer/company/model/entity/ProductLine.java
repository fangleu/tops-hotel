package com.dianping.cat.consumer.company.model.entity;

import static com.dianping.cat.consumer.company.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.company.model.Constants.ENTITY_PRODUCT_LINE;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.company.model.BaseEntity;
import com.dianping.cat.consumer.company.model.IVisitor;

public class ProductLine extends BaseEntity<ProductLine> {
   private String m_id;

   private double m_order;

   private Map<String, Domain> m_domains = new LinkedHashMap<String, Domain>();

   private String m_title;

   public ProductLine() {
   }

   public ProductLine(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitProductLine(this);
   }

   public ProductLine addDomain(Domain domain) {
      m_domains.put(domain.getId(), domain);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ProductLine) {
         ProductLine _o = (ProductLine) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Domain findDomain(String id) {
      return m_domains.get(id);
   }

   public Domain findOrCreateDomain(String id) {
      Domain domain = m_domains.get(id);

      if (domain == null) {
         synchronized (m_domains) {
            domain = m_domains.get(id);

            if (domain == null) {
               domain = new Domain(id);
               m_domains.put(id, domain);
            }
         }
      }

      return domain;
   }

   public Map<String, Domain> getDomains() {
      return m_domains;
   }

   public String getId() {
      return m_id;
   }

   public double getOrder() {
      return m_order;
   }

   public String getTitle() {
      return m_title;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ProductLine other) {
      assertAttributeEquals(other, ENTITY_PRODUCT_LINE, ATTR_ID, m_id, other.getId());

      m_order = other.getOrder();

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }
   }

   public boolean removeDomain(String id) {
      if (m_domains.containsKey(id)) {
         m_domains.remove(id);
         return true;
      }

      return false;
   }

   public ProductLine setId(String id) {
      m_id = id;
      return this;
   }

   public ProductLine setOrder(double order) {
      m_order = order;
      return this;
   }

   public ProductLine setTitle(String title) {
      m_title = title;
      return this;
   }

}
