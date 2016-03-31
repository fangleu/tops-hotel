package com.dianping.cat.home.service.entity;

import static com.dianping.cat.home.service.Constants.ATTR_ID;
import static com.dianping.cat.home.service.Constants.ENTITY_DOMAIN;

import com.dianping.cat.home.service.BaseEntity;
import com.dianping.cat.home.service.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private long m_totalCount;

   private long m_failureCount;

   private double m_failurePercent;

   private double m_sum;

   private double m_avg;

   private double m_qps;

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Domain) {
         Domain _o = (Domain) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getFailureCount() {
      return m_failureCount;
   }

   public double getFailurePercent() {
      return m_failurePercent;
   }

   public String getId() {
      return m_id;
   }

   public double getQps() {
      return m_qps;
   }

   public double getSum() {
      return m_sum;
   }

   public long getTotalCount() {
      return m_totalCount;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Domain other) {
      assertAttributeEquals(other, ENTITY_DOMAIN, ATTR_ID, m_id, other.getId());

   }

   public Domain setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Domain setFailureCount(long failureCount) {
      m_failureCount = failureCount;
      return this;
   }

   public Domain setFailurePercent(double failurePercent) {
      m_failurePercent = failurePercent;
      return this;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

   public Domain setQps(double qps) {
      m_qps = qps;
      return this;
   }

   public Domain setSum(double sum) {
      m_sum = sum;
      return this;
   }

   public Domain setTotalCount(long totalCount) {
      m_totalCount = totalCount;
      return this;
   }

}
