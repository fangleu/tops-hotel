package com.dianping.cat.home.alert.policy.entity;

import static com.dianping.cat.home.alert.policy.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_LEVEL;

import com.dianping.cat.home.alert.policy.BaseEntity;
import com.dianping.cat.home.alert.policy.IVisitor;

public class Level extends BaseEntity<Level> {
   private String m_id;

   private String m_send;

   private Integer m_suspendMinute;

   private Integer m_recoverMinute;

   public Level() {
   }

   public Level(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitLevel(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Level) {
         Level _o = (Level) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public Integer getRecoverMinute() {
      return m_recoverMinute;
   }

   public String getSend() {
      return m_send;
   }

   public Integer getSuspendMinute() {
      return m_suspendMinute;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Level other) {
      assertAttributeEquals(other, ENTITY_LEVEL, ATTR_ID, m_id, other.getId());

      if (other.getSend() != null) {
         m_send = other.getSend();
      }

      if (other.getSuspendMinute() != null) {
         m_suspendMinute = other.getSuspendMinute();
      }

      if (other.getRecoverMinute() != null) {
         m_recoverMinute = other.getRecoverMinute();
      }
   }

   public Level setId(String id) {
      m_id = id;
      return this;
   }

   public Level setRecoverMinute(Integer recoverMinute) {
      m_recoverMinute = recoverMinute;
      return this;
   }

   public Level setSend(String send) {
      m_send = send;
      return this;
   }

   public Level setSuspendMinute(Integer suspendMinute) {
      m_suspendMinute = suspendMinute;
      return this;
   }

}
