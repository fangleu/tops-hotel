package com.dianping.cat.configuration.server.filter.entity;

import static com.dianping.cat.configuration.server.filter.Constants.ATTR_ID;
import static com.dianping.cat.configuration.server.filter.Constants.ENTITY_CRASH_LOG_DOMAIN;

import com.dianping.cat.configuration.server.filter.BaseEntity;
import com.dianping.cat.configuration.server.filter.IVisitor;

public class CrashLogDomain extends BaseEntity<CrashLogDomain> {
   private String m_id;

   private String m_title;

   public CrashLogDomain() {
   }

   public CrashLogDomain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCrashLogDomain(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof CrashLogDomain) {
         CrashLogDomain _o = (CrashLogDomain) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
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

   @Override
   public void mergeAttributes(CrashLogDomain other) {
      assertAttributeEquals(other, ENTITY_CRASH_LOG_DOMAIN, ATTR_ID, m_id, other.getId());

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }
   }

   public CrashLogDomain setId(String id) {
      m_id = id;
      return this;
   }

   public CrashLogDomain setTitle(String title) {
      m_title = title;
      return this;
   }

}
