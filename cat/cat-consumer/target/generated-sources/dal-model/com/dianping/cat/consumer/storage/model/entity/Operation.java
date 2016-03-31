package com.dianping.cat.consumer.storage.model.entity;

import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_OPERATION;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.storage.model.BaseEntity;
import com.dianping.cat.consumer.storage.model.IVisitor;

public class Operation extends BaseEntity<Operation> {
   private String m_id;

   private long m_count;

   private double m_avg;

   private double m_sum;

   private long m_error;

   private long m_longCount;

   private Map<Integer, Segment> m_segments = new LinkedHashMap<Integer, Segment>();

   public Operation() {
   }

   public Operation(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitOperation(this);
   }

   public Operation addSegment(Segment segment) {
      m_segments.put(segment.getId(), segment);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Operation) {
         Operation _o = (Operation) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Segment findSegment(int id) {
      return m_segments.get(id);
   }

   public Segment findOrCreateSegment(int id) {
      Segment segment = m_segments.get(id);

      if (segment == null) {
         synchronized (m_segments) {
            segment = m_segments.get(id);

            if (segment == null) {
               segment = new Segment(id);
               m_segments.put(id, segment);
            }
         }
      }

      return segment;
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

   public String getId() {
      return m_id;
   }

   public long getLongCount() {
      return m_longCount;
   }

   public Map<Integer, Segment> getSegments() {
      return m_segments;
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

   public Operation incCount() {
      m_count++;
      return this;
   }

   public Operation incCount(long count) {
      m_count += count;
      return this;
   }

   public Operation incError() {
      m_error++;
      return this;
   }

   public Operation incError(long error) {
      m_error += error;
      return this;
   }

   public Operation incLongCount() {
      m_longCount++;
      return this;
   }

   public Operation incLongCount(long longCount) {
      m_longCount += longCount;
      return this;
   }

   public Operation incSum() {
      m_sum++;
      return this;
   }

   public Operation incSum(double sum) {
      m_sum += sum;
      return this;
   }

   @Override
   public void mergeAttributes(Operation other) {
      assertAttributeEquals(other, ENTITY_OPERATION, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();

      m_avg = other.getAvg();

      m_sum = other.getSum();

      m_error = other.getError();

      m_longCount = other.getLongCount();
   }

   public boolean removeSegment(int id) {
      if (m_segments.containsKey(id)) {
         m_segments.remove(id);
         return true;
      }

      return false;
   }

   public Operation setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Operation setCount(long count) {
      m_count = count;
      return this;
   }

   public Operation setError(long error) {
      m_error = error;
      return this;
   }

   public Operation setId(String id) {
      m_id = id;
      return this;
   }

   public Operation setLongCount(long longCount) {
      m_longCount = longCount;
      return this;
   }

   public Operation setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
