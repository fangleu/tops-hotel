package com.dianping.cat.configuration.app.comparison.entity;

import static com.dianping.cat.configuration.app.comparison.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.comparison.Constants.ENTITY_APP_COMPARISON;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.configuration.app.comparison.BaseEntity;
import com.dianping.cat.configuration.app.comparison.IVisitor;

public class AppComparison extends BaseEntity<AppComparison> {
   private String m_id;

   private String m_emails;

   private List<Item> m_items = new ArrayList<Item>();

   public AppComparison() {
   }

   public AppComparison(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAppComparison(this);
   }

   public AppComparison addItem(Item item) {
      m_items.add(item);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AppComparison) {
         AppComparison _o = (AppComparison) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getEmails() {
      return m_emails;
   }

   public String getId() {
      return m_id;
   }

   public List<Item> getItems() {
      return m_items;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AppComparison other) {
      assertAttributeEquals(other, ENTITY_APP_COMPARISON, ATTR_ID, m_id, other.getId());

      if (other.getEmails() != null) {
         m_emails = other.getEmails();
      }
   }

   public AppComparison setEmails(String emails) {
      m_emails = emails;
      return this;
   }

   public AppComparison setId(String id) {
      m_id = id;
      return this;
   }

}
