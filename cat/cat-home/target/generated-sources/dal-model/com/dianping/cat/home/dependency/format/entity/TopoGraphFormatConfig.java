package com.dianping.cat.home.dependency.format.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.dependency.format.BaseEntity;
import com.dianping.cat.home.dependency.format.IVisitor;

public class TopoGraphFormatConfig extends BaseEntity<TopoGraphFormatConfig> {
   private List<ProductLine> m_productLines = new ArrayList<ProductLine>();

   public TopoGraphFormatConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTopoGraphFormatConfig(this);
   }

   public TopoGraphFormatConfig addProductLine(ProductLine productLine) {
      m_productLines.add(productLine);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TopoGraphFormatConfig) {
         TopoGraphFormatConfig _o = (TopoGraphFormatConfig) obj;
         List<ProductLine> productLines = _o.getProductLines();
         boolean result = true;

         result &= (m_productLines == productLines || m_productLines != null && m_productLines.equals(productLines));

         return result;
      }

      return false;
   }

   public List<ProductLine> getProductLines() {
      return m_productLines;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_productLines == null ? 0 : m_productLines.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(TopoGraphFormatConfig other) {
   }

}
