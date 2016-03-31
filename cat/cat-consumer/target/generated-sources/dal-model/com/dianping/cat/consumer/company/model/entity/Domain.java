package com.dianping.cat.consumer.company.model.entity;

import static com.dianping.cat.consumer.company.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.company.model.Constants.ENTITY_DOMAIN;

import com.dianping.cat.consumer.company.model.BaseEntity;
import com.dianping.cat.consumer.company.model.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
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

   public String getId() {
      return m_id;
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

   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

}
