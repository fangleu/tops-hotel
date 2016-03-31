package com.dianping.cat.home.network.entity;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class Anchor extends BaseEntity<Anchor> {
   private String m_name;

   private Integer m_x;

   private Integer m_y;

   public Anchor() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAnchor(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Anchor) {
         Anchor _o = (Anchor) obj;
         String name = _o.getName();
         Integer x = _o.getX();
         Integer y = _o.getY();
         boolean result = true;

         result &= (m_name == name || m_name != null && m_name.equals(name));
         result &= (m_x == x || m_x != null && m_x.equals(x));
         result &= (m_y == y || m_y != null && m_y.equals(y));

         return result;
      }

      return false;
   }

   public String getName() {
      return m_name;
   }

   public Integer getX() {
      return m_x;
   }

   public Integer getY() {
      return m_y;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      hash = hash * 31 + (m_x == null ? 0 : m_x.hashCode());
      hash = hash * 31 + (m_y == null ? 0 : m_y.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Anchor other) {
      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getX() != null) {
         m_x = other.getX();
      }

      if (other.getY() != null) {
         m_y = other.getY();
      }
   }

   public Anchor setName(String name) {
      m_name = name;
      return this;
   }

   public Anchor setX(Integer x) {
      m_x = x;
      return this;
   }

   public Anchor setY(Integer y) {
      m_y = y;
      return this;
   }

}
