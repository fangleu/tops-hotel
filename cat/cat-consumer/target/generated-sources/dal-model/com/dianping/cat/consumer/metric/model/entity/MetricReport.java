package com.dianping.cat.consumer.metric.model.entity;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_PRODUCT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_METRIC_REPORT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.metric.model.BaseEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;

public class MetricReport extends BaseEntity<MetricReport> {
   private String m_product;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private Map<String, MetricItem> m_metricItems = new LinkedHashMap<String, MetricItem>();

   private Map<String, Statistic> m_statistics = new LinkedHashMap<String, Statistic>();

   public MetricReport() {
   }

   public MetricReport(String product) {
      m_product = product;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMetricReport(this);
   }

   public MetricReport addMetricItem(MetricItem metricItem) {
      m_metricItems.put(metricItem.getId(), metricItem);
      return this;
   }

   public MetricReport addStatistic(Statistic statistic) {
      m_statistics.put(statistic.getId(), statistic);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MetricReport) {
         MetricReport _o = (MetricReport) obj;
         String product = _o.getProduct();

         return m_product == product || m_product != null && m_product.equals(product);
      }

      return false;
   }

   public MetricItem findMetricItem(String id) {
      return m_metricItems.get(id);
   }

   public Statistic findStatistic(String id) {
      return m_statistics.get(id);
   }

   public MetricItem findOrCreateMetricItem(String id) {
      MetricItem metricItem = m_metricItems.get(id);

      if (metricItem == null) {
         synchronized (m_metricItems) {
            metricItem = m_metricItems.get(id);

            if (metricItem == null) {
               metricItem = new MetricItem(id);
               m_metricItems.put(id, metricItem);
            }
         }
      }

      return metricItem;
   }

   public Statistic findOrCreateStatistic(String id) {
      Statistic statistic = m_statistics.get(id);

      if (statistic == null) {
         synchronized (m_statistics) {
            statistic = m_statistics.get(id);

            if (statistic == null) {
               statistic = new Statistic(id);
               m_statistics.put(id, statistic);
            }
         }
      }

      return statistic;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public Map<String, MetricItem> getMetricItems() {
      return m_metricItems;
   }

   public String getProduct() {
      return m_product;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   public Map<String, Statistic> getStatistics() {
      return m_statistics;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_product == null ? 0 : m_product.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(MetricReport other) {
      assertAttributeEquals(other, ENTITY_METRIC_REPORT, ATTR_PRODUCT, m_product, other.getProduct());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public boolean removeMetricItem(String id) {
      if (m_metricItems.containsKey(id)) {
         m_metricItems.remove(id);
         return true;
      }

      return false;
   }

   public boolean removeStatistic(String id) {
      if (m_statistics.containsKey(id)) {
         m_statistics.remove(id);
         return true;
      }

      return false;
   }

   public MetricReport setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public MetricReport setProduct(String product) {
      m_product = product;
      return this;
   }

   public MetricReport setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
