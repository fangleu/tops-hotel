package com.dianping.cat.home.utilization.entity;

import static com.dianping.cat.home.utilization.Constants.ATTR_ID;
import static com.dianping.cat.home.utilization.Constants.ENTITY_APPLICATIONSTATE;

import com.dianping.cat.home.utilization.BaseEntity;
import com.dianping.cat.home.utilization.IVisitor;

public class ApplicationState extends BaseEntity<ApplicationState> {
   private String m_id;

   private long m_failureCount;

   private double m_failurePercent;

   private long m_count;

   private double m_maxQps;

   private double m_avg;

   private double m_sum;

   private double m_avg95;

   public ApplicationState() {
   }

   public ApplicationState(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitApplicationState(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ApplicationState) {
         ApplicationState _o = (ApplicationState) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public double getAvg95() {
      return m_avg95;
   }

   public long getCount() {
      return m_count;
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

   public double getMaxQps() {
      return m_maxQps;
   }

   public double getSum() {
      return m_sum;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ApplicationState other) {
      assertAttributeEquals(other, ENTITY_APPLICATIONSTATE, ATTR_ID, m_id, other.getId());

      m_failureCount = other.getFailureCount();

      m_failurePercent = other.getFailurePercent();

      m_count = other.getCount();

      m_maxQps = other.getMaxQps();

      m_avg = other.getAvg();

      m_sum = other.getSum();

      m_avg95 = other.getAvg95();
   }

   public ApplicationState setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public ApplicationState setAvg95(double avg95) {
      m_avg95 = avg95;
      return this;
   }

   public ApplicationState setCount(long count) {
      m_count = count;
      return this;
   }

   public ApplicationState setFailureCount(long failureCount) {
      m_failureCount = failureCount;
      return this;
   }

   public ApplicationState setFailurePercent(double failurePercent) {
      m_failurePercent = failurePercent;
      return this;
   }

   public ApplicationState setId(String id) {
      m_id = id;
      return this;
   }

   public ApplicationState setMaxQps(double maxQps) {
      m_maxQps = maxQps;
      return this;
   }

   public ApplicationState setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
