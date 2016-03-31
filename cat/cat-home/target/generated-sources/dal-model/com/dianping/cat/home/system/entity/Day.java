package com.dianping.cat.home.system.entity;

import com.dianping.cat.home.system.BaseEntity;
import com.dianping.cat.home.system.IVisitor;

public class Day extends BaseEntity<Day> {
   private long m_count;

   private double m_sum;

   private double m_avg;

   public Day() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDay(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Day) {
         Day _o = (Day) obj;
         long count = _o.getCount();
         double sum = _o.getSum();
         double avg = _o.getAvg();
         boolean result = true;

         result &= (m_count == count);
         result &= (m_sum == sum);
         result &= (m_avg == avg);

         return result;
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getCount() {
      return m_count;
   }

   public double getSum() {
      return m_sum;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (int) (m_count ^ (m_count >>> 32));
      hash = hash * 31 + (int) (Double.doubleToLongBits(m_sum) ^ (Double.doubleToLongBits(m_sum) >>> 32));
      hash = hash * 31 + (int) (Double.doubleToLongBits(m_avg) ^ (Double.doubleToLongBits(m_avg) >>> 32));

      return hash;
   }

   public Day incCount() {
      m_count++;
      return this;
   }

   public Day incCount(long count) {
      m_count += count;
      return this;
   }

   public Day incSum() {
      m_sum++;
      return this;
   }

   public Day incSum(double sum) {
      m_sum += sum;
      return this;
   }

   @Override
   public void mergeAttributes(Day other) {
      m_count = other.getCount();

      m_sum = other.getSum();

      m_avg = other.getAvg();
   }

   public Day setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Day setCount(long count) {
      m_count = count;
      return this;
   }

   public Day setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
