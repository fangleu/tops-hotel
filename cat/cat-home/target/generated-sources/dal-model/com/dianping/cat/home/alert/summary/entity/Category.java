package com.dianping.cat.home.alert.summary.entity;

import static com.dianping.cat.home.alert.summary.Constants.ATTR_NAME;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_CATEGORY;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.alert.summary.BaseEntity;
import com.dianping.cat.home.alert.summary.IVisitor;

public class Category extends BaseEntity<Category> {
   private String m_name;

   private List<Alert> m_alerts = new ArrayList<Alert>();

   public Category() {
   }

   public Category(String name) {
      m_name = name;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCategory(this);
   }

   public Category addAlert(Alert alert) {
      m_alerts.add(alert);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Category) {
         Category _o = (Category) obj;
         String name = _o.getName();

         return m_name == name || m_name != null && m_name.equals(name);
      }

      return false;
   }

   public List<Alert> getAlerts() {
      return m_alerts;
   }

   public String getName() {
      return m_name;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Category other) {
      assertAttributeEquals(other, ENTITY_CATEGORY, ATTR_NAME, m_name, other.getName());

   }

   public Category setName(String name) {
      m_name = name;
      return this;
   }

}
