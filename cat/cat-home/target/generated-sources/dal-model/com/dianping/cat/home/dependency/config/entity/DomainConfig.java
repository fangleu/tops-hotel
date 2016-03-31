package com.dianping.cat.home.dependency.config.entity;

import static com.dianping.cat.home.dependency.config.Constants.ATTR_ID;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_DOMAIN_CONFIG;

import com.dianping.cat.home.dependency.config.BaseEntity;
import com.dianping.cat.home.dependency.config.IVisitor;

public class DomainConfig extends BaseEntity<DomainConfig> {
   private String m_id;

   private int m_warningThreshold;

   private int m_errorThreshold;

   private double m_warningResponseTime;

   private double m_errorResponseTime;

   private int m_minCountThreshold = 100;

   public DomainConfig() {
   }

   public DomainConfig(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomainConfig(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof DomainConfig) {
         DomainConfig _o = (DomainConfig) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public double getErrorResponseTime() {
      return m_errorResponseTime;
   }

   public int getErrorThreshold() {
      return m_errorThreshold;
   }

   public String getId() {
      return m_id;
   }

   public int getMinCountThreshold() {
      return m_minCountThreshold;
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

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(DomainConfig other) {
      assertAttributeEquals(other, ENTITY_DOMAIN_CONFIG, ATTR_ID, m_id, other.getId());

      m_warningThreshold = other.getWarningThreshold();

      m_errorThreshold = other.getErrorThreshold();

      m_warningResponseTime = other.getWarningResponseTime();

      m_errorResponseTime = other.getErrorResponseTime();

      m_minCountThreshold = other.getMinCountThreshold();
   }

   public DomainConfig setErrorResponseTime(double errorResponseTime) {
      m_errorResponseTime = errorResponseTime;
      return this;
   }

   public DomainConfig setErrorThreshold(int errorThreshold) {
      m_errorThreshold = errorThreshold;
      return this;
   }

   public DomainConfig setId(String id) {
      m_id = id;
      return this;
   }

   public DomainConfig setMinCountThreshold(int minCountThreshold) {
      m_minCountThreshold = minCountThreshold;
      return this;
   }

   public DomainConfig setWarningResponseTime(double warningResponseTime) {
      m_warningResponseTime = warningResponseTime;
      return this;
   }

   public DomainConfig setWarningThreshold(int warningThreshold) {
      m_warningThreshold = warningThreshold;
      return this;
   }

}
