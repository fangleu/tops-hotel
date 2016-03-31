package com.dianping.cat.home.bug.config.entity;

import static com.dianping.cat.home.bug.config.Constants.ATTR_ID;
import static com.dianping.cat.home.bug.config.Constants.ENTITY_DOMAIN;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.bug.config.BaseEntity;
import com.dianping.cat.home.bug.config.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private Boolean m_additivity;

   private List<String> m_exceptions = new ArrayList<String>();

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addException(String exception) {
      m_exceptions.add(exception);
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

   public Boolean getAdditivity() {
      return m_additivity;
   }

   public List<String> getExceptions() {
      return m_exceptions;
   }

   public String getId() {
      return m_id;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isAdditivity() {
      return m_additivity != null && m_additivity.booleanValue();
   }

   @Override
   public void mergeAttributes(Domain other) {
      assertAttributeEquals(other, ENTITY_DOMAIN, ATTR_ID, m_id, other.getId());

      if (other.getAdditivity() != null) {
         m_additivity = other.getAdditivity();
      }
   }

   public Domain setAdditivity(Boolean additivity) {
      m_additivity = additivity;
      return this;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

}
