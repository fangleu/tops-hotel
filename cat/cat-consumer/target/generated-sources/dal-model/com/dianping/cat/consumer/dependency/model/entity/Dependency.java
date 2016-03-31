package com.dianping.cat.consumer.dependency.model.entity;

import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_KEY;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_DEPENDENCY;

import com.dianping.cat.consumer.dependency.model.BaseEntity;
import com.dianping.cat.consumer.dependency.model.IVisitor;

public class Dependency extends BaseEntity<Dependency> {
   private String m_type;

   private String m_target;

   private long m_totalCount;

   private double m_avg;

   private long m_errorCount;

   private String m_key;

   private double m_sum;

   public Dependency() {
   }

   public Dependency(String key) {
      m_key = key;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDependency(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Dependency) {
         Dependency _o = (Dependency) obj;
         String key = _o.getKey();

         return m_key == key || m_key != null && m_key.equals(key);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getErrorCount() {
      return m_errorCount;
   }

   public String getKey() {
      return m_key;
   }

   public double getSum() {
      return m_sum;
   }

   public String getTarget() {
      return m_target;
   }

   public long getTotalCount() {
      return m_totalCount;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_key == null ? 0 : m_key.hashCode());

      return hash;
   }

   public Dependency incErrorCount() {
      m_errorCount++;
      return this;
   }

   public Dependency incErrorCount(long errorCount) {
      m_errorCount += errorCount;
      return this;
   }

   public Dependency incTotalCount() {
      m_totalCount++;
      return this;
   }

   public Dependency incTotalCount(long totalCount) {
      m_totalCount += totalCount;
      return this;
   }

   @Override
   public void mergeAttributes(Dependency other) {
      assertAttributeEquals(other, ENTITY_DEPENDENCY, ATTR_KEY, m_key, other.getKey());

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getTarget() != null) {
         m_target = other.getTarget();
      }

      m_totalCount = other.getTotalCount();

      m_avg = other.getAvg();

      m_errorCount = other.getErrorCount();

      m_sum = other.getSum();
   }

   public Dependency setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Dependency setErrorCount(long errorCount) {
      m_errorCount = errorCount;
      return this;
   }

   public Dependency setKey(String key) {
      m_key = key;
      return this;
   }

   public Dependency setSum(double sum) {
      m_sum = sum;
      return this;
   }

   public Dependency setTarget(String target) {
      m_target = target;
      return this;
   }

   public Dependency setTotalCount(long totalCount) {
      m_totalCount = totalCount;
      return this;
   }

   public Dependency setType(String type) {
      m_type = type;
      return this;
   }

}
