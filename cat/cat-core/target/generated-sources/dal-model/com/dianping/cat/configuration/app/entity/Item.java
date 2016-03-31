package com.dianping.cat.configuration.app.entity;

import static com.dianping.cat.configuration.app.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.Constants.ENTITY_ITEM;

import com.dianping.cat.configuration.app.BaseEntity;
import com.dianping.cat.configuration.app.IVisitor;

public class Item extends BaseEntity<Item> {
   private Integer m_id;

   private String m_name;

   private String m_des;

   public Item() {
   }

   public Item(Integer id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitItem(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Item) {
         Item _o = (Item) obj;
         Integer id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getDes() {
      return m_des;
   }

   public Integer getId() {
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
   public void mergeAttributes(Item other) {
      assertAttributeEquals(other, ENTITY_ITEM, ATTR_ID, m_id, other.getId());

      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getDes() != null) {
         m_des = other.getDes();
      }
   }

   public Item setDes(String des) {
      m_des = des;
      return this;
   }

   public Item setId(Integer id) {
      m_id = id;
      return this;
   }

   public Item setName(String name) {
      m_name = name;
      return this;
   }

}
