package com.dianping.cat.consumer.cross.model.entity;

import static com.dianping.cat.consumer.cross.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.cross.model.Constants.ENTITY_REMOTE;

import com.dianping.cat.consumer.cross.model.BaseEntity;
import com.dianping.cat.consumer.cross.model.IVisitor;

public class Remote extends BaseEntity<Remote> {
   private String m_id;

   private String m_role;

   private Type m_type;

   private String m_app;

   private String m_ip;

   public Remote() {
   }

   public Remote(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitRemote(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Remote) {
         Remote _o = (Remote) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getApp() {
      return m_app;
   }

   public String getId() {
      return m_id;
   }

   public String getIp() {
      return m_ip;
   }

   public String getRole() {
      return m_role;
   }

   public Type getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Remote other) {
      assertAttributeEquals(other, ENTITY_REMOTE, ATTR_ID, m_id, other.getId());

      if (other.getRole() != null) {
         m_role = other.getRole();
      }

      if (other.getApp() != null) {
         m_app = other.getApp();
      }

      if (other.getIp() != null) {
         m_ip = other.getIp();
      }
   }

   public Remote setApp(String app) {
      m_app = app;
      return this;
   }

   public Remote setId(String id) {
      m_id = id;
      return this;
   }

   public Remote setIp(String ip) {
      m_ip = ip;
      return this;
   }

   public Remote setRole(String role) {
      m_role = role;
      return this;
   }

   public Remote setType(Type type) {
      m_type = type;
      return this;
   }

}
