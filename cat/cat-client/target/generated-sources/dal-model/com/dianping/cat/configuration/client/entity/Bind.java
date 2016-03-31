package com.dianping.cat.configuration.client.entity;

import com.dianping.cat.configuration.client.BaseEntity;
import com.dianping.cat.configuration.client.IVisitor;

public class Bind extends BaseEntity<Bind> {
   private String m_ip;

   private int m_port = 2280;

   public Bind() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitBind(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Bind) {
         Bind _o = (Bind) obj;
         String ip = _o.getIp();
         int port = _o.getPort();
         boolean result = true;

         result &= (m_ip == ip || m_ip != null && m_ip.equals(ip));
         result &= (m_port == port);

         return result;
      }

      return false;
   }

   public String getIp() {
      return m_ip;
   }

   public int getPort() {
      return m_port;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_ip == null ? 0 : m_ip.hashCode());
      hash = hash * 31 + m_port;

      return hash;
   }

   @Override
   public void mergeAttributes(Bind other) {
      if (other.getIp() != null) {
         m_ip = other.getIp();
      }

      m_port = other.getPort();
   }

   public Bind setIp(String ip) {
      m_ip = ip;
      return this;
   }

   public Bind setPort(int port) {
      m_port = port;
      return this;
   }

}
