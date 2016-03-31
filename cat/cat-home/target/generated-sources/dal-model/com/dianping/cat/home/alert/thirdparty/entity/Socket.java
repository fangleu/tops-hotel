package com.dianping.cat.home.alert.thirdparty.entity;

import com.dianping.cat.home.alert.thirdparty.BaseEntity;
import com.dianping.cat.home.alert.thirdparty.IVisitor;

public class Socket extends BaseEntity<Socket> {
   private String m_ip;

   private int m_port;

   private String m_domain;

   public Socket() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSocket(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Socket) {
         Socket _o = (Socket) obj;
         String ip = _o.getIp();
         int port = _o.getPort();
         String domain = _o.getDomain();
         boolean result = true;

         result &= (m_ip == ip || m_ip != null && m_ip.equals(ip));
         result &= (m_port == port);
         result &= (m_domain == domain || m_domain != null && m_domain.equals(domain));

         return result;
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
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
      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Socket other) {
      if (other.getIp() != null) {
         m_ip = other.getIp();
      }

      m_port = other.getPort();

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }
   }

   public Socket setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Socket setIp(String ip) {
      m_ip = ip;
      return this;
   }

   public Socket setPort(int port) {
      m_port = port;
      return this;
   }

}
