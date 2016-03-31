package com.dianping.cat.consumer.top.model.entity;

import static com.dianping.cat.consumer.top.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.top.model.Constants.ENTITY_ERROR;

import com.dianping.cat.consumer.top.model.BaseEntity;
import com.dianping.cat.consumer.top.model.IVisitor;

public class Error extends BaseEntity<Error> {
   private String m_id;

   private int m_count;

   public Error() {
   }

   public Error(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitError(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Error) {
         Error _o = (Error) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public int getCount() {
      return m_count;
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

   public Error incCount() {
      m_count++;
      return this;
   }

   public Error incCount(int count) {
      m_count += count;
      return this;
   }

   @Override
   public void mergeAttributes(Error other) {
      assertAttributeEquals(other, ENTITY_ERROR, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();
   }

   public Error setCount(int count) {
      m_count = count;
      return this;
   }

   public Error setId(String id) {
      m_id = id;
      return this;
   }

}
