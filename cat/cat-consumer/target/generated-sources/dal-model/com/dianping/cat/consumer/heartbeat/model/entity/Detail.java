package com.dianping.cat.consumer.heartbeat.model.entity;

import static com.dianping.cat.consumer.heartbeat.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_DETAIL;

import com.dianping.cat.consumer.heartbeat.model.BaseEntity;
import com.dianping.cat.consumer.heartbeat.model.IVisitor;

public class Detail extends BaseEntity<Detail> {
   private String m_id;

   private double m_value;

   public Detail() {
   }

   public Detail(String id) {
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
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public double getValue() {
      return m_value;
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

      m_value = other.getValue();
   }

   public Detail setId(String id) {
      m_id = id;
      return this;
   }

   public Detail setValue(double value) {
      m_value = value;
      return this;
   }

}
