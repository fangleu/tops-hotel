package com.dianping.cat.configuration.client.entity;

import static com.dianping.cat.configuration.client.Constants.ATTR_IP;
import static com.dianping.cat.configuration.client.Constants.ENTITY_SERVER;

import com.dianping.cat.configuration.client.BaseEntity;
import com.dianping.cat.configuration.client.IVisitor;

public class Server extends BaseEntity<Server> {
   private String m_ip;

   private Integer m_port = 2280;

   private Integer m_httpPort = 8080;

   private Boolean m_enabled = true;

   public Server() {
   }

   public Server(String ip) {
      m_ip = ip;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitServer(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Server) {
         Server _o = (Server) obj;
         String ip = _o.getIp();

         return m_ip == ip || m_ip != null && m_ip.equals(ip);
      }

      return false;
   }

   public Boolean getEnabled() {
      return m_enabled;
   }

   public Integer getHttpPort() {
      return m_httpPort;
   }

   public String getIp() {
      return m_ip;
   }

   public Integer getPort() {
      return m_port;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_ip == null ? 0 : m_ip.hashCode());

      return hash;
   }

   public boolean isEnabled() {
      return m_enabled != null && m_enabled.booleanValue();
   }

   @Override
   public void mergeAttributes(Server other) {
      assertAttributeEquals(other, ENTITY_SERVER, ATTR_IP, m_ip, other.getIp());

      if (other.getPort() != null) {
         m_port = other.getPort();
      }

      if (other.getHttpPort() != null) {
         m_httpPort = other.getHttpPort();
      }

      if (other.getEnabled() != null) {
         m_enabled = other.getEnabled();
      }
   }

   public Server setEnabled(Boolean enabled) {
      m_enabled = enabled;
      return this;
   }

   public Server setHttpPort(Integer httpPort) {
      m_httpPort = httpPort;
      return this;
   }

   public Server setIp(String ip) {
      m_ip = ip;
      return this;
   }

   public Server setPort(Integer port) {
      m_port = port;
      return this;
   }

}
