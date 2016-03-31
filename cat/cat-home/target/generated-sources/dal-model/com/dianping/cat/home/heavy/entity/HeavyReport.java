package com.dianping.cat.home.heavy.entity;

import static com.dianping.cat.home.heavy.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_REPORT;

import com.dianping.cat.home.heavy.BaseEntity;
import com.dianping.cat.home.heavy.IVisitor;

public class HeavyReport extends BaseEntity<HeavyReport> {
   private String m_domain;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private HeavySql m_heavySql;

   private HeavyCall m_heavyCall;

   private HeavyCache m_heavyCache;

   public HeavyReport() {
   }

   public HeavyReport(String domain) {
      m_domain = domain;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitHeavyReport(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof HeavyReport) {
         HeavyReport _o = (HeavyReport) obj;
         String domain = _o.getDomain();

         return m_domain == domain || m_domain != null && m_domain.equals(domain);
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public HeavyCache getHeavyCache() {
      return m_heavyCache;
   }

   public HeavyCall getHeavyCall() {
      return m_heavyCall;
   }

   public HeavySql getHeavySql() {
      return m_heavySql;
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
   public void mergeAttributes(HeavyReport other) {
      assertAttributeEquals(other, ENTITY_HEAVY_REPORT, ATTR_DOMAIN, m_domain, other.getDomain());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public HeavyReport setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public HeavyReport setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public HeavyReport setHeavyCache(HeavyCache heavyCache) {
      m_heavyCache = heavyCache;
      return this;
   }

   public HeavyReport setHeavyCall(HeavyCall heavyCall) {
      m_heavyCall = heavyCall;
      return this;
   }

   public HeavyReport setHeavySql(HeavySql heavySql) {
      m_heavySql = heavySql;
      return this;
   }

   public HeavyReport setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
