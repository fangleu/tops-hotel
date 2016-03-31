package com.dianping.cat.consumer.storage.model.entity;

import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_SEGMENT;

import com.dianping.cat.consumer.storage.model.BaseEntity;
import com.dianping.cat.consumer.storage.model.IVisitor;

public class Segment extends BaseEntity<Segment> {
   private int m_id;

   private long m_count;

   private double m_avg;

   private double m_sum;

   private long m_error;

   private long m_longCount;

   public Segment() {
   }

   public Segment(int id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSegment(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Segment) {
         Segment _o = (Segment) obj;
         int id = _o.getId();

         return m_id == id;
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getCount() {
      return m_count;
   }

   public long getError() {
      return m_error;
   }

   public int getId() {
      return m_id;
   }

   public long getLongCount() {
      return m_longCount;
   }

   public double getSum() {
      return m_sum;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + m_id;

      return hash;
   }

   public Segment incCount() {
      m_count++;
      return this;
   }

   public Segment incCount(long count) {
      m_count += count;
      return this;
   }

   public Segment incError() {
      m_error++;
      return this;
   }

   public Segment incError(long error) {
      m_error += error;
      return this;
   }

   public Segment incLongCount() {
      m_longCount++;
      return this;
   }

   public Segment incLongCount(long longCount) {
      m_longCount += longCount;
      return this;
   }

   public Segment incSum() {
      m_sum++;
      return this;
   }

   public Segment incSum(double sum) {
      m_sum += sum;
      return this;
   }

   @Override
   public void mergeAttributes(Segment other) {
      assertAttributeEquals(other, ENTITY_SEGMENT, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();

      m_avg = other.getAvg();

      m_sum = other.getSum();

      m_error = other.getError();

      m_longCount = other.getLongCount();
   }

   public Segment setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Segment setCount(long count) {
      m_count = count;
      return this;
   }

   public Segment setError(long error) {
      m_error = error;
      return this;
   }

   public Segment setId(int id) {
      m_id = id;
      return this;
   }

   public Segment setLongCount(long longCount) {
      m_longCount = longCount;
      return this;
   }

   public Segment setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
