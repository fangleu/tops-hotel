package com.dianping.cat.configuration.client.entity;

import static com.dianping.cat.configuration.client.Constants.ATTR_ID;
import static com.dianping.cat.configuration.client.Constants.ENTITY_DOMAIN;

import com.dianping.cat.configuration.client.BaseEntity;
import com.dianping.cat.configuration.client.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private String m_ip;

   private Boolean m_enabled;

   private int m_maxMessageSize = 1000;

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Domain) {
         Domain _o = (Domain) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Boolean getEnabled() {
      return m_enabled;
   }

   public String getId() {
      return m_id;
   }

   public String getIp() {
      return m_ip;
   }

   public int getMaxMessageSize() {
      return m_maxMessageSize;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isEnabled() {
      return m_enabled != null && m_enabled.booleanValue();
   }

   @Override
   public void mergeAttributes(Domain other) {
      assertAttributeEquals(other, ENTITY_DOMAIN, ATTR_ID, m_id, other.getId());

      if (other.getIp() != null) {
         m_ip = other.getIp();
      }

      if (other.getEnabled() != null) {
         m_enabled = other.getEnabled();
      }

      m_maxMessageSize = other.getMaxMessageSize();
   }

   public Domain setEnabled(Boolean enabled) {
      m_enabled = enabled;
      return this;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

   public Domain setIp(String ip) {
      m_ip = ip;
      return this;
   }

   public Domain setMaxMessageSize(int maxMessageSize) {
      m_maxMessageSize = maxMessageSize;
      return this;
   }

}
