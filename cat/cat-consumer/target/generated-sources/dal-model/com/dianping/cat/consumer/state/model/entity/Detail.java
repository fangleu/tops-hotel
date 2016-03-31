package com.dianping.cat.consumer.state.model.entity;

import static com.dianping.cat.consumer.state.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.state.model.Constants.ENTITY_DETAIL;

import com.dianping.cat.consumer.state.model.BaseEntity;
import com.dianping.cat.consumer.state.model.IVisitor;

public class Detail extends BaseEntity<Detail> {
   private Long m_id;

   private long m_total;

   private double m_size;

   private long m_totalLoss;

   public Detail() {
   }

   public Detail(Long id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDetail(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Detail) {
         Detail _o = (Detail) obj;
         Long id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Long getId() {
      return m_id;
   }

   public double getSize() {
      return m_size;
   }

   public long getTotal() {
      return m_total;
   }

   public long getTotalLoss() {
      return m_totalLoss;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Detail other) {
      assertAttributeEquals(other, ENTITY_DETAIL, ATTR_ID, m_id, other.getId());

      m_total = other.getTotal();

      m_size = other.getSize();

      m_totalLoss = other.getTotalLoss();
   }

   public Detail setId(Long id) {
      m_id = id;
      return this;
   }

   public Detail setSize(double size) {
      m_size = size;
      return this;
   }

   public Detail setTotal(long total) {
      m_total = total;
      return this;
   }

   public Detail setTotalLoss(long totalLoss) {
      m_totalLoss = totalLoss;
      return this;
   }

}
