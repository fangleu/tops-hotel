package com.dianping.cat.configuration.client.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dianping.cat.configuration.client.BaseEntity;
import com.dianping.cat.configuration.client.IVisitor;

public class ClientConfig extends BaseEntity<ClientConfig> {
   private String m_mode;

   private Boolean m_enabled = true;

   private Boolean m_dumpLocked;

   private List<Server> m_servers = new ArrayList<Server>();

   private Map<String, Domain> m_domains = new LinkedHashMap<String, Domain>();

   private Bind m_bind;

   private Map<String, Property> m_properties = new LinkedHashMap<String, Property>();

   private String m_baseLogDir = "target/catlog";

   private Map<String, String> m_dynamicAttributes = new LinkedHashMap<String, String>();

   public ClientConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitConfig(this);
   }

   public ClientConfig addDomain(Domain domain) {
      m_domains.put(domain.getId(), domain);
      return this;
   }

   public ClientConfig addProperty(Property property) {
      m_properties.put(property.getName(), property);
      return this;
   }

   public ClientConfig addServer(Server server) {
      m_servers.add(server);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ClientConfig) {
         ClientConfig _o = (ClientConfig) obj;
         String mode = _o.getMode();
         Boolean enabled = _o.getEnabled();
         Boolean dumpLocked = _o.getDumpLocked();
         List<Server> servers = _o.getServers();
         Map<String, Domain> domains = _o.getDomains();
         Bind bind = _o.getBind();
         Map<String, Property> properties = _o.getProperties();
         String baseLogDir = _o.getBaseLogDir();
         boolean result = true;

         result &= (m_mode == mode || m_mode != null && m_mode.equals(mode));
         result &= (m_enabled == enabled || m_enabled != null && m_enabled.equals(enabled));
         result &= (m_dumpLocked == dumpLocked || m_dumpLocked != null && m_dumpLocked.equals(dumpLocked));
         result &= (m_servers == servers || m_servers != null && m_servers.equals(servers));
         result &= (m_domains == domains || m_domains != null && m_domains.equals(domains));
         result &= (m_bind == bind || m_bind != null && m_bind.equals(bind));
         result &= (m_properties == properties || m_properties != null && m_properties.equals(properties));
         result &= (m_baseLogDir == baseLogDir || m_baseLogDir != null && m_baseLogDir.equals(baseLogDir));
         result &= m_dynamicAttributes.equals(_o.getDynamicAttributes());

         return result;
      }

      return false;
   }

   public Domain findDomain(String id) {
      return m_domains.get(id);
   }

   public Property findProperty(String name) {
      return m_properties.get(name);
   }

   public Server findServer(String ip) {
      for (Server server : m_servers) {
         if (!server.getIp().equals(ip)) {
            continue;
         }

         return server;
      }

      return null;
   }

   public String getDynamicAttribute(String name) {
      return m_dynamicAttributes.get(name);
   }

   public Map<String, String> getDynamicAttributes() {
      return m_dynamicAttributes;
   }

   public String getBaseLogDir() {
      return m_baseLogDir;
   }

   public Bind getBind() {
      return m_bind;
   }

   public Map<String, Domain> getDomains() {
      return m_domains;
   }

   public Boolean getDumpLocked() {
      return m_dumpLocked;
   }

   public Boolean getEnabled() {
      return m_enabled;
   }

   public String getMode() {
      return m_mode;
   }

   public Map<String, Property> getProperties() {
      return m_properties;
   }

   public List<Server> getServers() {
      return m_servers;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_mode == null ? 0 : m_mode.hashCode());
      hash = hash * 31 + (m_enabled == null ? 0 : m_enabled.hashCode());
      hash = hash * 31 + (m_dumpLocked == null ? 0 : m_dumpLocked.hashCode());
      hash = hash * 31 + (m_servers == null ? 0 : m_servers.hashCode());
      hash = hash * 31 + (m_domains == null ? 0 : m_domains.hashCode());
      hash = hash * 31 + (m_bind == null ? 0 : m_bind.hashCode());
      hash = hash * 31 + (m_properties == null ? 0 : m_properties.hashCode());
      hash = hash * 31 + (m_baseLogDir == null ? 0 : m_baseLogDir.hashCode());
      hash = hash * 31 + m_dynamicAttributes.hashCode();

      return hash;
   }

   public boolean isDumpLocked() {
      return m_dumpLocked != null && m_dumpLocked.booleanValue();
   }

   public boolean isEnabled() {
      return m_enabled != null && m_enabled.booleanValue();
   }

   @Override
   public void mergeAttributes(ClientConfig other) {
      for (Map.Entry<String, String> e : other.getDynamicAttributes().entrySet()) {
         m_dynamicAttributes.put(e.getKey(), e.getValue());
      }

      if (other.getMode() != null) {
         m_mode = other.getMode();
      }

      if (other.getEnabled() != null) {
         m_enabled = other.getEnabled();
      }

      if (other.getDumpLocked() != null) {
         m_dumpLocked = other.getDumpLocked();
      }
   }

   public boolean removeDomain(String id) {
      if (m_domains.containsKey(id)) {
         m_domains.remove(id);
         return true;
      }

      return false;
   }

   public boolean removeProperty(String name) {
      if (m_properties.containsKey(name)) {
         m_properties.remove(name);
         return true;
      }

      return false;
   }

   public boolean removeServer(String ip) {
      int len = m_servers.size();

      for (int i = 0; i < len; i++) {
         Server server = m_servers.get(i);

         if (!server.getIp().equals(ip)) {
            continue;
         }

         m_servers.remove(i);
         return true;
      }

      return false;
   }

   public void setDynamicAttribute(String name, String value) {
      m_dynamicAttributes.put(name, value);
   }

   public ClientConfig setBaseLogDir(String baseLogDir) {
      m_baseLogDir = baseLogDir;
      return this;
   }

   public ClientConfig setBind(Bind bind) {
      m_bind = bind;
      return this;
   }

   public ClientConfig setDumpLocked(Boolean dumpLocked) {
      m_dumpLocked = dumpLocked;
      return this;
   }

   public ClientConfig setEnabled(Boolean enabled) {
      m_enabled = enabled;
      return this;
   }

   public ClientConfig setMode(String mode) {
      m_mode = mode;
      return this;
   }

}
