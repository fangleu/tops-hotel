package com.dianping.cat.home.storage.entity;

import static com.dianping.cat.home.storage.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.Constants.ENTITY_MACHINE;

import com.dianping.cat.home.storage.BaseEntity;
import com.dianping.cat.home.storage.IVisitor;

public class Machine extends BaseEntity<Machine> {
   private String m_id;

   private String m_title;

   private boolean m_alert = true;

   public Machine() {
   }

   public Machine(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMachine(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Machine) {
         Machine _o = (Machine) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public boolean getAlert() {
      return m_alert;
   }

   public String getId() {
      return m_id;
   }

   public String getTitle() {
      return m_title;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isAlert() {
      return m_alert;
   }

   @Override
   public void mergeAttributes(Machine other) {
      assertAttributeEquals(other, ENTITY_MACHINE, ATTR_ID, m_id, other.getId());

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      m_alert = other.getAlert();
   }

   public Machine setAlert(boolean alert) {
      m_alert = alert;
      return this;
   }

   public Machine setId(String id) {
      m_id = id;
      return this;
   }

   public Machine setTitle(String title) {
      m_title = title;
      return this;
   }

}
