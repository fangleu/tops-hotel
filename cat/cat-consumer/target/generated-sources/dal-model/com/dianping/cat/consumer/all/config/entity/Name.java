package com.dianping.cat.consumer.all.config.entity;

import com.dianping.cat.consumer.all.config.BaseEntity;
import com.dianping.cat.consumer.all.config.IVisitor;

public class Name extends BaseEntity<Name> {
   private String m_id;

   public Name() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitName(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Name) {
         Name _o = (Name) obj;
         String id = _o.getId();
         boolean result = true;

         result &= (m_id == id || m_id != null && m_id.equals(id));

         return result;
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
   public void mergeAttributes(Name other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }
   }

   public Name setId(String id) {
      m_id = id;
      return this;
   }

}
