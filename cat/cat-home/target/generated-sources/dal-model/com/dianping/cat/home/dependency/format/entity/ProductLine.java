package com.dianping.cat.home.dependency.format.entity;

import com.dianping.cat.home.dependency.format.BaseEntity;
import com.dianping.cat.home.dependency.format.IVisitor;

public class ProductLine extends BaseEntity<ProductLine> {
   private String m_id;

   private Integer m_colInside;

   public ProductLine() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitProductLine(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ProductLine) {
         ProductLine _o = (ProductLine) obj;
         String id = _o.getId();
         Integer colInside = _o.getColInside();
         boolean result = true;

         result &= (m_id == id || m_id != null && m_id.equals(id));
         result &= (m_colInside == colInside || m_colInside != null && m_colInside.equals(colInside));

         return result;
      }

      return false;
   }

   public Integer getColInside() {
      return m_colInside;
   }

   public String getId() {
      return m_id;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());
      hash = hash * 31 + (m_colInside == null ? 0 : m_colInside.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ProductLine other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }

      if (other.getColInside() != null) {
         m_colInside = other.getColInside();
      }
   }

   public ProductLine setColInside(Integer colInside) {
      m_colInside = colInside;
      return this;
   }

   public ProductLine setId(String id) {
      m_id = id;
      return this;
   }

}
