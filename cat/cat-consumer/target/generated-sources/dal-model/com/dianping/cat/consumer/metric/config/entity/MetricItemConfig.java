package com.dianping.cat.consumer.metric.config.entity;

import static com.dianping.cat.consumer.metric.config.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.config.Constants.ENTITY_METRIC_ITEM_CONFIG;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.consumer.metric.config.BaseEntity;
import com.dianping.cat.consumer.metric.config.IVisitor;

public class MetricItemConfig extends BaseEntity<MetricItemConfig> {
   private String m_id;

   private String m_domain;

   private String m_type;

   private double m_viewOrder;

   private String m_metricKey;

   private String m_title;

   private boolean m_showCount;

   private boolean m_showAvg;

   private boolean m_showSum;

   private double m_showDashboardOrder;

   private boolean m_alarm = false;

   private List<Tag> m_tags = new ArrayList<Tag>();

   public MetricItemConfig() {
   }

   public MetricItemConfig(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMetricItemConfig(this);
   }

   public MetricItemConfig addTag(Tag tag) {
      m_tags.add(tag);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MetricItemConfig) {
         MetricItemConfig _o = (MetricItemConfig) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public boolean getAlarm() {
      return m_alarm;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getId() {
      return m_id;
   }

   public String getMetricKey() {
      return m_metricKey;
   }

   public boolean getShowAvg() {
      return m_showAvg;
   }

   public boolean getShowCount() {
      return m_showCount;
   }

   public double getShowDashboardOrder() {
      return m_showDashboardOrder;
   }

   public boolean getShowSum() {
      return m_showSum;
   }

   public List<Tag> getTags() {
      return m_tags;
   }

   public String getTitle() {
      return m_title;
   }

   public String getType() {
      return m_type;
   }

   public double getViewOrder() {
      return m_viewOrder;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isAlarm() {
      return m_alarm;
   }

   public boolean isShowAvg() {
      return m_showAvg;
   }

   public boolean isShowCount() {
      return m_showCount;
   }

   public boolean isShowSum() {
      return m_showSum;
   }

   @Override
   public void mergeAttributes(MetricItemConfig other) {
      assertAttributeEquals(other, ENTITY_METRIC_ITEM_CONFIG, ATTR_ID, m_id, other.getId());

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      m_viewOrder = other.getViewOrder();

      if (other.getMetricKey() != null) {
         m_metricKey = other.getMetricKey();
      }

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      m_showCount = other.getShowCount();

      m_showAvg = other.getShowAvg();

      m_showSum = other.getShowSum();

      m_showDashboardOrder = other.getShowDashboardOrder();

      m_alarm = other.getAlarm();
   }

   public MetricItemConfig setAlarm(boolean alarm) {
      m_alarm = alarm;
      return this;
   }

   public MetricItemConfig setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public MetricItemConfig setId(String id) {
      m_id = id;
      return this;
   }

   public MetricItemConfig setMetricKey(String metricKey) {
      m_metricKey = metricKey;
      return this;
   }

   public MetricItemConfig setShowAvg(boolean showAvg) {
      m_showAvg = showAvg;
      return this;
   }

   public MetricItemConfig setShowCount(boolean showCount) {
      m_showCount = showCount;
      return this;
   }

   public MetricItemConfig setShowDashboardOrder(double showDashboardOrder) {
      m_showDashboardOrder = showDashboardOrder;
      return this;
   }

   public MetricItemConfig setShowSum(boolean showSum) {
      m_showSum = showSum;
      return this;
   }

   public MetricItemConfig setTitle(String title) {
      m_title = title;
      return this;
   }

   public MetricItemConfig setType(String type) {
      m_type = type;
      return this;
   }

   public MetricItemConfig setViewOrder(double viewOrder) {
      m_viewOrder = viewOrder;
      return this;
   }

}
