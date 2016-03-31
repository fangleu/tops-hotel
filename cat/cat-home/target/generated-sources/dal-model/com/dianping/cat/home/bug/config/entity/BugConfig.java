package com.dianping.cat.home.bug.config.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dianping.cat.home.bug.config.BaseEntity;
import com.dianping.cat.home.bug.config.IVisitor;

public class BugConfig extends BaseEntity<BugConfig> {
   private List<String> m_exceptions = new ArrayList<String>();

   private Map<String, Domain> m_domains = new LinkedHashMap<String, Domain>();

   public BugConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitBugConfig(this);
   }

   public BugConfig addDomain(Domain domain) {
      m_domains.put(domain.getId(), domain);
      return this;
   }

   public BugConfig addException(String exception) {
      m_exceptions.add(exception);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof BugConfig) {
         BugConfig _o = (BugConfig) obj;
         List<String> exceptions = _o.getExceptions();
         Map<String, Domain> domains = _o.getDomains();
         boolean result = true;

         result &= (m_exceptions == exceptions || m_exceptions != null && m_exceptions.equals(exceptions));
         result &= (m_domains == domains || m_domains != null && m_domains.equals(domains));

         return result;
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

   public Map<String, Domain> getDomains() {
      return m_domains;
   }

   public List<String> getExceptions() {
      return m_exceptions;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_exceptions == null ? 0 : m_exceptions.hashCode());
      hash = hash * 31 + (m_domains == null ? 0 : m_domains.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(BugConfig other) {
   }

   public boolean removeDomain(String id) {
      if (m_domains.containsKey(id)) {
         m_domains.remove(id);
         return true;
      }

      return false;
   }

}
