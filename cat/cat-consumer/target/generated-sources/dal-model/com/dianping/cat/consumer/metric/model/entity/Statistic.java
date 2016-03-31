package com.dianping.cat.consumer.metric.model.entity;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_STATISTIC;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.metric.model.BaseEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;

public class Statistic extends BaseEntity<Statistic> {
   private String m_id;

   private Map<String, StatisticsItem> m_statisticsItems = new LinkedHashMap<String, StatisticsItem>();

   public Statistic() {
   }

   public Statistic(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitStatistic(this);
   }

   public Statistic addStatisticsItem(StatisticsItem statisticsItem) {
      m_statisticsItems.put(statisticsItem.getId(), statisticsItem);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Statistic) {
         Statistic _o = (Statistic) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public StatisticsItem findStatisticsItem(String id) {
      return m_statisticsItems.get(id);
   }

   public StatisticsItem findOrCreateStatisticsItem(String id) {
      StatisticsItem statisticsItem = m_statisticsItems.get(id);

      if (statisticsItem == null) {
         synchronized (m_statisticsItems) {
            statisticsItem = m_statisticsItems.get(id);

            if (statisticsItem == null) {
               statisticsItem = new StatisticsItem(id);
               m_statisticsItems.put(id, statisticsItem);
            }
         }
      }

      return statisticsItem;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, StatisticsItem> getStatisticsItems() {
      return m_statisticsItems;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Statistic other) {
      assertAttributeEquals(other, ENTITY_STATISTIC, ATTR_ID, m_id, other.getId());

   }

   public boolean removeStatisticsItem(String id) {
      if (m_statisticsItems.containsKey(id)) {
         m_statisticsItems.remove(id);
         return true;
      }

      return false;
   }

   public Statistic setId(String id) {
      m_id = id;
      return this;
   }

}
