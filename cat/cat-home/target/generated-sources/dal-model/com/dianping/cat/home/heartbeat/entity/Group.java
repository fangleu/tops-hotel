package com.dianping.cat.home.heartbeat.entity;

import static com.dianping.cat.home.heartbeat.Constants.ATTR_ID;
import static com.dianping.cat.home.heartbeat.Constants.ENTITY_GROUP;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.heartbeat.BaseEntity;
import com.dianping.cat.home.heartbeat.IVisitor;

public class Group extends BaseEntity<Group> {
   private String m_id;

   private int m_order;

   private Map<String, Metric> m_metrics = new LinkedHashMap<String, Metric>();

   public Group() {
   }

   public Group(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitGroup(this);
   }

   public Group addMetric(Metric metric) {
      m_metrics.put(metric.getId(), metric);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Group) {
         Group _o = (Group) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Metric findMetric(String id) {
      return m_metrics.get(id);
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Metric> getMetrics() {
      return m_metrics;
   }

   public int getOrder() {
      return m_order;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Group other) {
      assertAttributeEquals(other, ENTITY_GROUP, ATTR_ID, m_id, other.getId());

      m_order = other.getOrder();
   }

   public boolean removeMetric(String id) {
      if (m_metrics.containsKey(id)) {
         m_metrics.remove(id);
         return true;
      }

      return false;
   }

   public Group setId(String id) {
      m_id = id;
      return this;
   }

   public Group setOrder(int order) {
      m_order = order;
      return this;
   }

}
