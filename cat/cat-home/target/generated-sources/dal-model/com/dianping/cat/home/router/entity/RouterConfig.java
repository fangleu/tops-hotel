package com.dianping.cat.home.router.entity;

import static com.dianping.cat.home.router.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.router.Constants.ENTITY_ROUTER_CONFIG;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dianping.cat.home.router.BaseEntity;
import com.dianping.cat.home.router.IVisitor;

public class RouterConfig extends BaseEntity<RouterConfig> {
   private List<DefaultServer> m_defaultServers = new ArrayList<DefaultServer>();

   private Map<String, Domain> m_domains = new LinkedHashMap<String, Domain>();

   private java.util.Date m_startTime;

   private String m_domain;

   private String m_backupServer;

   private int m_backupServerPort;

   private java.util.Date m_endTime;

   public RouterConfig() {
   }

   public RouterConfig(String domain) {
      m_domain = domain;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitRouterConfig(this);
   }

   public RouterConfig addDefaultServer(DefaultServer defaultServer) {
      m_defaultServers.add(defaultServer);
      return this;
   }

   public RouterConfig addDomain(Domain domain) {
      m_domains.put(domain.getId(), domain);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof RouterConfig) {
         RouterConfig _o = (RouterConfig) obj;
         String domain = _o.getDomain();

         return m_domain == domain || m_domain != null && m_domain.equals(domain);
      }

      return false;
   }

   public Domain findDomain(String id) {
      return m_domains.get(id);
   }

   public Domain findOrCreateDomain(String id) {
      Domain domain = m_domains.get(id);

      if (domain == null) {
         synchronized (m_domains) {
            domain = m_domains.get(id);

            if (domain == null) {
               domain = new Domain(id);
               m_domains.put(id, domain);
            }
         }
      }

      return domain;
   }

   public String getBackupServer() {
      return m_backupServer;
   }

   public int getBackupServerPort() {
      return m_backupServerPort;
   }

   public List<DefaultServer> getDefaultServers() {
      return m_defaultServers;
   }

   public String getDomain() {
      return m_domain;
   }

   public Map<String, Domain> getDomains() {
      return m_domains;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(RouterConfig other) {
      assertAttributeEquals(other, ENTITY_ROUTER_CONFIG, ATTR_DOMAIN, m_domain, other.getDomain());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getBackupServer() != null) {
         m_backupServer = other.getBackupServer();
      }

      m_backupServerPort = other.getBackupServerPort();

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public boolean removeDomain(String id) {
      if (m_domains.containsKey(id)) {
         m_domains.remove(id);
         return true;
      }

      return false;
   }

   public RouterConfig setBackupServer(String backupServer) {
      m_backupServer = backupServer;
      return this;
   }

   public RouterConfig setBackupServerPort(int backupServerPort) {
      m_backupServerPort = backupServerPort;
      return this;
   }

   public RouterConfig setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public RouterConfig setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public RouterConfig setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
