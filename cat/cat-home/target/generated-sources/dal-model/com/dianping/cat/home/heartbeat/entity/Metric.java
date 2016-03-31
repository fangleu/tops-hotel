package com.dianping.cat.home.heartbeat.entity;

import static com.dianping.cat.home.heartbeat.Constants.ATTR_ID;
import static com.dianping.cat.home.heartbeat.Constants.ENTITY_METRIC;

import com.dianping.cat.home.heartbeat.BaseEntity;
import com.dianping.cat.home.heartbeat.IVisitor;

public class Metric extends BaseEntity<Metric> {
   private String m_id;

   private String m_unit = "1";

   private boolean m_delta = false;

   private int m_order;

   private String m_title;

   private String m_lable = "MB";

   private boolean m_alert = false;

   public Metric() {
   }

   public Metric(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMetric(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Metric) {
         Metric _o = (Metric) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public boolean getAlert() {
      return m_alert;
   }

   public boolean getDelta() {
      return m_delta;
   }

   public String getId() {
      return m_id;
   }

   public String getLable() {
      return m_lable;
   }

   public int getOrder() {
      return m_order;
   }

   public String getTitle() {
      return m_title;
   }

   public String getUnit() {
      return m_unit;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isAlert() {
      return m_alert;
   }

   public boolean isDelta() {
      return m_delta;
   }

   @Override
   public void mergeAttributes(Metric other) {
      assertAttributeEquals(other, ENTITY_METRIC, ATTR_ID, m_id, other.getId());

      if (other.getUnit() != null) {
         m_unit = other.getUnit();
      }

      m_delta = other.getDelta();

      m_order = other.getOrder();

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      if (other.getLable() != null) {
         m_lable = other.getLable();
      }

      m_alert = other.getAlert();
   }

   public Metric setAlert(boolean alert) {
      m_alert = alert;
      return this;
   }

   public Metric setDelta(boolean delta) {
      m_delta = delta;
      return this;
   }

   public Metric setId(String id) {
      m_id = id;
      return this;
   }

   public Metric setLable(String lable) {
      m_lable = lable;
      return this;
   }

   public Metric setOrder(int order) {
      m_order = order;
      return this;
   }

   public Metric setTitle(String title) {
      m_title = title;
      return this;
   }

   public Metric setUnit(String unit) {
      m_unit = unit;
      return this;
   }

}
