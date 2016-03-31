package com.dianping.cat.consumer.matrix.model.entity;

import static com.dianping.cat.consumer.matrix.model.Constants.ATTR_TYPE;
import static com.dianping.cat.consumer.matrix.model.Constants.ENTITY_RATIO;

import com.dianping.cat.consumer.matrix.model.BaseEntity;
import com.dianping.cat.consumer.matrix.model.IVisitor;

public class Ratio extends BaseEntity<Ratio> {
   private String m_type;

   private int m_min;

   private int m_max;

   private int m_totalCount;

   private long m_totalTime;

   private String m_url;

   public Ratio() {
   }

   public Ratio(String type) {
      m_type = type;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitRatio(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Ratio) {
         Ratio _o = (Ratio) obj;
         String type = _o.getType();

         return m_type == type || m_type != null && m_type.equals(type);
      }

      return false;
   }

   public int getMax() {
      return m_max;
   }

   public int getMin() {
      return m_min;
   }

   public int getTotalCount() {
      return m_totalCount;
   }

   public long getTotalTime() {
      return m_totalTime;
   }

   public String getType() {
      return m_type;
   }

   public String getUrl() {
      return m_url;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());

      return hash;
   }

   public Ratio incTotalCount() {
      m_totalCount++;
      return this;
   }

   public Ratio incTotalCount(int totalCount) {
      m_totalCount += totalCount;
      return this;
   }

   @Override
   public void mergeAttributes(Ratio other) {
      assertAttributeEquals(other, ENTITY_RATIO, ATTR_TYPE, m_type, other.getType());

      m_min = other.getMin();

      m_max = other.getMax();

      m_totalCount = other.getTotalCount();

      m_totalTime = other.getTotalTime();

      if (other.getUrl() != null) {
         m_url = other.getUrl();
      }
   }

   public Ratio setMax(int max) {
      m_max = max;
      return this;
   }

   public Ratio setMin(int min) {
      m_min = min;
      return this;
   }

   public Ratio setTotalCount(int totalCount) {
      m_totalCount = totalCount;
      return this;
   }

   public Ratio setTotalTime(long totalTime) {
      m_totalTime = totalTime;
      return this;
   }

   public Ratio setType(String type) {
      m_type = type;
      return this;
   }

   public Ratio setUrl(String url) {
      m_url = url;
      return this;
   }

}
