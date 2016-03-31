package com.dianping.cat.consumer.dependency.model.entity;

import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_INDEX;

import com.dianping.cat.consumer.dependency.model.BaseEntity;
import com.dianping.cat.consumer.dependency.model.IVisitor;

public class Index extends BaseEntity<Index> {
   private String m_name;

   private long m_totalCount;

   private long m_errorCount;

   private double m_sum;

   private double m_avg;

   public Index() {
   }

   public Index(String name) {
      m_name = name;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitIndex(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Index) {
         Index _o = (Index) obj;
         String name = _o.getName();

         return m_name == name || m_name != null && m_name.equals(name);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getErrorCount() {
      return m_errorCount;
   }

   public String getName() {
      return m_name;
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

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

      return hash;
   }

   public Index incErrorCount() {
      m_errorCount++;
      return this;
   }

   public Index incErrorCount(long errorCount) {
      m_errorCount += errorCount;
      return this;
   }

   public Index incTotalCount() {
      m_totalCount++;
      return this;
   }

   public Index incTotalCount(long totalCount) {
      m_totalCount += totalCount;
      return this;
   }

   @Override
   public void mergeAttributes(Index other) {
      assertAttributeEquals(other, ENTITY_INDEX, ATTR_NAME, m_name, other.getName());

      m_totalCount = other.getTotalCount();

      m_errorCount = other.getErrorCount();

      m_sum = other.getSum();

      m_avg = other.getAvg();
   }

   public Index setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Index setErrorCount(long errorCount) {
      m_errorCount = errorCount;
      return this;
   }

   public Index setName(String name) {
      m_name = name;
      return this;
   }

   public Index setSum(double sum) {
      m_sum = sum;
      return this;
   }

   public Index setTotalCount(long totalCount) {
      m_totalCount = totalCount;
      return this;
   }

}
