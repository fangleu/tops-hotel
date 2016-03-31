package com.dianping.cat.home.bug.entity;

import static com.dianping.cat.home.bug.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.bug.Constants.ENTITY_BUG_REPORT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.bug.BaseEntity;
import com.dianping.cat.home.bug.IVisitor;

public class BugReport extends BaseEntity<BugReport> {
   private java.util.Date m_startTime;

   private String m_domain;

   private java.util.Date m_endTime;

   private Map<String, Domain> m_domains = new LinkedHashMap<String, Domain>();

   public BugReport() {
   }

   public BugReport(String domain) {
      m_domain = domain;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitBugReport(this);
   }

   public BugReport addDomain(Domain domain) {
      m_domains.put(domain.getId(), domain);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof BugReport) {
         BugReport _o = (BugReport) obj;
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
   public void mergeAttributes(BugReport other) {
      assertAttributeEquals(other, ENTITY_BUG_REPORT, ATTR_DOMAIN, m_domain, other.getDomain());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

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

   public BugReport setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public BugReport setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public BugReport setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
