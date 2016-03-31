package com.dianping.cat.consumer.metric.model.entity;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_POINT;

import com.dianping.cat.consumer.metric.model.BaseEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;

public class Point extends BaseEntity<Point> {
   private Integer m_id;

   private int m_count;

   private double m_sum;

   private double m_avg;

   public Point() {
   }

   public Point(Integer id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitPoint(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Point) {
         Point _o = (Point) obj;
         Integer id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public int getCount() {
      return m_count;
   }

   public Integer getId() {
      return m_id;
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
   public void mergeAttributes(Point other) {
      assertAttributeEquals(other, ENTITY_POINT, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();

      m_sum = other.getSum();

      m_avg = other.getAvg();
   }

   public Point setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Point setCount(int count) {
      m_count = count;
      return this;
   }

   public Point setId(Integer id) {
      m_id = id;
      return this;
   }

   public Point setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
