package com.dianping.cat.consumer.company.model.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.company.model.BaseEntity;
import com.dianping.cat.consumer.company.model.IVisitor;

public class Company extends BaseEntity<Company> {
   private String m_name;

   private Map<String, ProductLine> m_productLines = new LinkedHashMap<String, ProductLine>();

   public Company() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCompany(this);
   }

   public Company addProductLine(ProductLine productLine) {
      m_productLines.put(productLine.getId(), productLine);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Company) {
         Company _o = (Company) obj;
         String name = _o.getName();
         Map<String, ProductLine> productLines = _o.getProductLines();
         boolean result = true;

         result &= (m_name == name || m_name != null && m_name.equals(name));
         result &= (m_productLines == productLines || m_productLines != null && m_productLines.equals(productLines));

         return result;
      }

      return false;
   }

   public ProductLine findProductLine(String id) {
      return m_productLines.get(id);
   }

   public ProductLine findOrCreateProductLine(String id) {
      ProductLine productLine = m_productLines.get(id);

      if (productLine == null) {
         synchronized (m_productLines) {
            productLine = m_productLines.get(id);

            if (productLine == null) {
               productLine = new ProductLine(id);
               m_productLines.put(id, productLine);
            }
         }
      }

      return productLine;
   }

   public String getName() {
      return m_name;
   }

   public Map<String, ProductLine> getProductLines() {
      return m_productLines;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      hash = hash * 31 + (m_productLines == null ? 0 : m_productLines.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Company other) {
      if (other.getName() != null) {
         m_name = other.getName();
      }
   }

   public boolean removeProductLine(String id) {
      if (m_productLines.containsKey(id)) {
         m_productLines.remove(id);
         return true;
      }

      return false;
   }

   public Company setName(String name) {
      m_name = name;
      return this;
   }

}
