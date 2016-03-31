package com.dianping.cat.home.exception.entity;

import static com.dianping.cat.home.exception.Constants.ATTR_ID;
import static com.dianping.cat.home.exception.Constants.ENTITY_EXCEPTION_LIMIT;

import com.dianping.cat.home.exception.BaseEntity;
import com.dianping.cat.home.exception.IVisitor;

public class ExceptionLimit extends BaseEntity<ExceptionLimit> {
   private String m_id;

   private String m_domain;

   private String m_name;

   private int m_warning;

   private int m_error;

   public ExceptionLimit() {
   }

   public ExceptionLimit(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitExceptionLimit(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ExceptionLimit) {
         ExceptionLimit _o = (ExceptionLimit) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public int getError() {
      return m_error;
   }

   public String getId() {
      return m_id;
   }

   public String getName() {
      return m_name;
   }

   public int getWarning() {
      return m_warning;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ExceptionLimit other) {
      assertAttributeEquals(other, ENTITY_EXCEPTION_LIMIT, ATTR_ID, m_id, other.getId());

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getName() != null) {
         m_name = other.getName();
      }

      m_warning = other.getWarning();

      m_error = other.getError();
   }

   public ExceptionLimit setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public ExceptionLimit setError(int error) {
      m_error = error;
      return this;
   }

   public ExceptionLimit setId(String id) {
      m_id = id;
      return this;
   }

   public ExceptionLimit setName(String name) {
      m_name = name;
      return this;
   }

   public ExceptionLimit setWarning(int warning) {
      m_warning = warning;
      return this;
   }

}
