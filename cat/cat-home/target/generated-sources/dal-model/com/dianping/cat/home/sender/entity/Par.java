package com.dianping.cat.home.sender.entity;

import com.dianping.cat.home.sender.BaseEntity;
import com.dianping.cat.home.sender.IVisitor;

public class Par extends BaseEntity<Par> {
   private String m_id;

   public Par() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitPar(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Par) {
         Par _o = (Par) obj;
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
   public void mergeAttributes(Par other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }
   }

   public Par setId(String id) {
      m_id = id;
      return this;
   }

}
