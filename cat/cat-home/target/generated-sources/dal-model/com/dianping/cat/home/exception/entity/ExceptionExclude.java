package com.dianping.cat.home.exception.entity;

import static com.dianping.cat.home.exception.Constants.ATTR_ID;
import static com.dianping.cat.home.exception.Constants.ENTITY_EXCEPTION_EXCLUDE;

import com.dianping.cat.home.exception.BaseEntity;
import com.dianping.cat.home.exception.IVisitor;

public class ExceptionExclude extends BaseEntity<ExceptionExclude> {
   private String m_id;

   private String m_domain;

   private String m_name;

   public ExceptionExclude() {
   }

   public ExceptionExclude(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitExceptionExclude(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ExceptionExclude) {
         ExceptionExclude _o = (ExceptionExclude) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getId() {
      return m_id;
   }

   public String getName() {
      return m_name;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ExceptionExclude other) {
      assertAttributeEquals(other, ENTITY_EXCEPTION_EXCLUDE, ATTR_ID, m_id, other.getId());

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getName() != null) {
         m_name = other.getName();
      }
   }

   public ExceptionExclude setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public ExceptionExclude setId(String id) {
      m_id = id;
      return this;
   }

   public ExceptionExclude setName(String name) {
      m_name = name;
      return this;
   }

}
