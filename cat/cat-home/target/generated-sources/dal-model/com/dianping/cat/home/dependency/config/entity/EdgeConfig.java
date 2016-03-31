package com.dianping.cat.home.dependency.config.entity;

import static com.dianping.cat.home.dependency.config.Constants.ATTR_KEY;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_EDGE_CONFIG;

import com.dianping.cat.home.dependency.config.BaseEntity;
import com.dianping.cat.home.dependency.config.IVisitor;

public class EdgeConfig extends BaseEntity<EdgeConfig> {
   private String m_key;

   private String m_type;

   private String m_from;

   private String m_to;

   private int m_warningThreshold;

   private int m_errorThreshold;

   private double m_warningResponseTime;

   private double m_errorResponseTime;

   private int m_minCountThreshold = 100;

   public EdgeConfig() {
   }

   public EdgeConfig(String key) {
      m_key = key;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitEdgeConfig(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof EdgeConfig) {
         EdgeConfig _o = (EdgeConfig) obj;
         String key = _o.getKey();

         return m_key == key || m_key != null && m_key.equals(key);
      }

      return false;
   }

   public double getErrorResponseTime() {
      return m_errorResponseTime;
   }

   public int getErrorThreshold() {
      return m_errorThreshold;
   }

   public String getFrom() {
      return m_from;
   }

   public String getKey() {
      return m_key;
   }

   public int getMinCountThreshold() {
      return m_minCountThreshold;
   }

   public String getTo() {
      return m_to;
   }

   public String getType() {
      return m_type;
   }

   public double getWarningResponseTime() {
      return m_warningResponseTime;
   }

   public int getWarningThreshold() {
      return m_warningThreshold;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_key == null ? 0 : m_key.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(EdgeConfig other) {
      assertAttributeEquals(other, ENTITY_EDGE_CONFIG, ATTR_KEY, m_key, other.getKey());

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getFrom() != null) {
         m_from = other.getFrom();
      }

      if (other.getTo() != null) {
         m_to = other.getTo();
      }

      m_warningThreshold = other.getWarningThreshold();

      m_errorThreshold = other.getErrorThreshold();

      m_warningResponseTime = other.getWarningResponseTime();

      m_errorResponseTime = other.getErrorResponseTime();

      m_minCountThreshold = other.getMinCountThreshold();
   }

   public EdgeConfig setErrorResponseTime(double errorResponseTime) {
      m_errorResponseTime = errorResponseTime;
      return this;
   }

   public EdgeConfig setErrorThreshold(int errorThreshold) {
      m_errorThreshold = errorThreshold;
      return this;
   }

   public EdgeConfig setFrom(String from) {
      m_from = from;
      return this;
   }

   public EdgeConfig setKey(String key) {
      m_key = key;
      return this;
   }

   public EdgeConfig setMinCountThreshold(int minCountThreshold) {
      m_minCountThreshold = minCountThreshold;
      return this;
   }

   public EdgeConfig setTo(String to) {
      m_to = to;
      return this;
   }

   public EdgeConfig setType(String type) {
      m_type = type;
      return this;
   }

   public EdgeConfig setWarningResponseTime(double warningResponseTime) {
      m_warningResponseTime = warningResponseTime;
      return this;
   }

   public EdgeConfig setWarningThreshold(int warningThreshold) {
      m_warningThreshold = warningThreshold;
      return this;
   }

}
