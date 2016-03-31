package com.dianping.cat.configuration.server.filter.entity;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dianping.cat.configuration.server.filter.BaseEntity;
import com.dianping.cat.configuration.server.filter.IVisitor;

public class ServerFilterConfig extends BaseEntity<ServerFilterConfig> {
   private Set<String> m_transactionTypes = new LinkedHashSet<String>();

   private Set<String> m_transactionNames = new LinkedHashSet<String>();

   private Set<String> m_domains = new LinkedHashSet<String>();

   private Map<String, CrashLogDomain> m_crashLogDomains = new LinkedHashMap<String, CrashLogDomain>();

   public ServerFilterConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitServerFilterConfig(this);
   }

   public ServerFilterConfig addCrashLogDomain(CrashLogDomain crashLogDomain) {
      m_crashLogDomains.put(crashLogDomain.getId(), crashLogDomain);
      return this;
   }

   public ServerFilterConfig addDomain(String domain) {
      m_domains.add(domain);
      return this;
   }

   public ServerFilterConfig addTransactionName(String transactionName) {
      m_transactionNames.add(transactionName);
      return this;
   }

   public ServerFilterConfig addTransactionType(String transactionType) {
      m_transactionTypes.add(transactionType);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ServerFilterConfig) {
         ServerFilterConfig _o = (ServerFilterConfig) obj;
         Set<String> transactionTypes = _o.getTransactionTypes();
         Set<String> transactionNames = _o.getTransactionNames();
         Set<String> domains = _o.getDomains();
         Map<String, CrashLogDomain> crashLogDomains = _o.getCrashLogDomains();
         boolean result = true;

         result &= (m_transactionTypes == transactionTypes || m_transactionTypes != null && m_transactionTypes.equals(transactionTypes));
         result &= (m_transactionNames == transactionNames || m_transactionNames != null && m_transactionNames.equals(transactionNames));
         result &= (m_domains == domains || m_domains != null && m_domains.equals(domains));
         result &= (m_crashLogDomains == crashLogDomains || m_crashLogDomains != null && m_crashLogDomains.equals(crashLogDomains));

         return result;
      }

      return false;
   }

   public CrashLogDomain findCrashLogDomain(String id) {
      return m_crashLogDomains.get(id);
   }

   public Map<String, CrashLogDomain> getCrashLogDomains() {
      return m_crashLogDomains;
   }

   public Set<String> getDomains() {
      return m_domains;
   }

   public Set<String> getTransactionNames() {
      return m_transactionNames;
   }

   public Set<String> getTransactionTypes() {
      return m_transactionTypes;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_transactionTypes == null ? 0 : m_transactionTypes.hashCode());
      hash = hash * 31 + (m_transactionNames == null ? 0 : m_transactionNames.hashCode());
      hash = hash * 31 + (m_domains == null ? 0 : m_domains.hashCode());
      hash = hash * 31 + (m_crashLogDomains == null ? 0 : m_crashLogDomains.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ServerFilterConfig other) {
   }

   public boolean removeCrashLogDomain(String id) {
      if (m_crashLogDomains.containsKey(id)) {
         m_crashLogDomains.remove(id);
         return true;
      }

      return false;
   }

}
