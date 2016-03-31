package com.dianping.cat.consumer.all.config.entity;

import static com.dianping.cat.consumer.all.config.Constants.ATTR_ID;
import static com.dianping.cat.consumer.all.config.Constants.ENTITY_TYPE;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.consumer.all.config.BaseEntity;
import com.dianping.cat.consumer.all.config.IVisitor;

public class Type extends BaseEntity<Type> {
   private String m_id;

   private List<Name> m_nameList = new ArrayList<Name>();

   public Type() {
   }

   public Type(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitType(this);
   }

   public Type addName(Name name) {
      m_nameList.add(name);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Type) {
         Type _o = (Type) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public List<Name> getNameList() {
      return m_nameList;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Type other) {
      assertAttributeEquals(other, ENTITY_TYPE, ATTR_ID, m_id, other.getId());

   }

   public Type setId(String id) {
      m_id = id;
      return this;
   }

}
