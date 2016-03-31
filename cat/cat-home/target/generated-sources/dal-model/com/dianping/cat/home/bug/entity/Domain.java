package com.dianping.cat.home.bug.entity;

import static com.dianping.cat.home.bug.Constants.ATTR_ID;
import static com.dianping.cat.home.bug.Constants.ENTITY_DOMAIN;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.bug.BaseEntity;
import com.dianping.cat.home.bug.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private String m_problemUrl;

   private Map<String, ExceptionItem> m_exceptionItems = new LinkedHashMap<String, ExceptionItem>();

   private String m_department;

   private String m_productLine;

   private String m_excpetion;

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addExceptionItem(ExceptionItem exceptionItem) {
      m_exceptionItems.put(exceptionItem.getId(), exceptionItem);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Domain) {
         Domain _o = (Domain) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public ExceptionItem findExceptionItem(String id) {
      return m_exceptionItems.get(id);
   }

   public ExceptionItem findOrCreateExceptionItem(String id) {
      ExceptionItem exceptionItem = m_exceptionItems.get(id);

      if (exceptionItem == null) {
         synchronized (m_exceptionItems) {
            exceptionItem = m_exceptionItems.get(id);

            if (exceptionItem == null) {
               exceptionItem = new ExceptionItem(id);
               m_exceptionItems.put(id, exceptionItem);
            }
         }
      }

      return exceptionItem;
   }

   public String getDepartment() {
      return m_department;
   }

   public Map<String, ExceptionItem> getExceptionItems() {
      return m_exceptionItems;
   }

   public String getExcpetion() {
      return m_excpetion;
   }

   public String getId() {
      return m_id;
   }

   public String getProblemUrl() {
      return m_problemUrl;
   }

   public String getProductLine() {
      return m_productLine;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Domain other) {
      assertAttributeEquals(other, ENTITY_DOMAIN, ATTR_ID, m_id, other.getId());

      if (other.getDepartment() != null) {
         m_department = other.getDepartment();
      }

      if (other.getProductLine() != null) {
         m_productLine = other.getProductLine();
      }
   }

   public boolean removeExceptionItem(String id) {
      if (m_exceptionItems.containsKey(id)) {
         m_exceptionItems.remove(id);
         return true;
      }

      return false;
   }

   public Domain setDepartment(String department) {
      m_department = department;
      return this;
   }

   public Domain setExcpetion(String excpetion) {
      m_excpetion = excpetion;
      return this;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

   public Domain setProblemUrl(String problemUrl) {
      m_problemUrl = problemUrl;
      return this;
   }

   public Domain setProductLine(String productLine) {
      m_productLine = productLine;
      return this;
   }

}
