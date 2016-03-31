package com.dianping.cat.home.alert.summary.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.alert.summary.BaseEntity;
import com.dianping.cat.home.alert.summary.IVisitor;

public class AlertSummary extends BaseEntity<AlertSummary> {
   private java.util.Date m_alertDate;

   private String m_domain;

   private Map<String, Category> m_categories = new LinkedHashMap<String, Category>();

   public AlertSummary() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAlertSummary(this);
   }

   public AlertSummary addCategory(Category category) {
      m_categories.put(category.getName(), category);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AlertSummary) {
         AlertSummary _o = (AlertSummary) obj;
         java.util.Date alertDate = _o.getAlertDate();
         String domain = _o.getDomain();
         Map<String, Category> categories = _o.getCategories();
         boolean result = true;

         result &= (m_alertDate == alertDate || m_alertDate != null && m_alertDate.equals(alertDate));
         result &= (m_domain == domain || m_domain != null && m_domain.equals(domain));
         result &= (m_categories == categories || m_categories != null && m_categories.equals(categories));

         return result;
      }

      return false;
   }

   public Category findCategory(String name) {
      return m_categories.get(name);
   }

   public Category findOrCreateCategory(String name) {
      Category category = m_categories.get(name);

      if (category == null) {
         synchronized (m_categories) {
            category = m_categories.get(name);

            if (category == null) {
               category = new Category(name);
               m_categories.put(name, category);
            }
         }
      }

      return category;
   }

   public java.util.Date getAlertDate() {
      return m_alertDate;
   }

   public Map<String, Category> getCategories() {
      return m_categories;
   }

   public String getDomain() {
      return m_domain;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_alertDate == null ? 0 : m_alertDate.hashCode());
      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());
      hash = hash * 31 + (m_categories == null ? 0 : m_categories.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AlertSummary other) {
      if (other.getAlertDate() != null) {
         m_alertDate = other.getAlertDate();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }
   }

   public boolean removeCategory(String name) {
      if (m_categories.containsKey(name)) {
         m_categories.remove(name);
         return true;
      }

      return false;
   }

   public AlertSummary setAlertDate(java.util.Date alertDate) {
      m_alertDate = alertDate;
      return this;
   }

   public AlertSummary setDomain(String domain) {
      m_domain = domain;
      return this;
   }

}
