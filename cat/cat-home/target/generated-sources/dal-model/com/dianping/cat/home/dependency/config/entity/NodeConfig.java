package com.dianping.cat.home.dependency.config.entity;

import static com.dianping.cat.home.dependency.config.Constants.ATTR_TYPE;
import static com.dianping.cat.home.dependency.config.Constants.ENTITY_NODE_CONFIG;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.dependency.config.BaseEntity;
import com.dianping.cat.home.dependency.config.IVisitor;

public class NodeConfig extends BaseEntity<NodeConfig> {
   private String m_type;

   private Integer m_defaultWarningThreshold;

   private Integer m_defaultErrorThreshold;

   private Double m_defaultWarningResponseTime;

   private Double m_defaultErrorResponseTime;

   private Map<String, DomainConfig> m_domainConfigs = new LinkedHashMap<String, DomainConfig>();

   private int m_defaultMinCountThreshold = 100;

   public NodeConfig() {
   }

   public NodeConfig(String type) {
      m_type = type;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitNodeConfig(this);
   }

   public NodeConfig addDomainConfig(DomainConfig domainConfig) {
      m_domainConfigs.put(domainConfig.getId(), domainConfig);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof NodeConfig) {
         NodeConfig _o = (NodeConfig) obj;
         String type = _o.getType();

         return m_type == type || m_type != null && m_type.equals(type);
      }

      return false;
   }

   public DomainConfig findDomainConfig(String id) {
      return m_domainConfigs.get(id);
   }

   public DomainConfig findOrCreateDomainConfig(String id) {
      DomainConfig domainConfig = m_domainConfigs.get(id);

      if (domainConfig == null) {
         synchronized (m_domainConfigs) {
            domainConfig = m_domainConfigs.get(id);

            if (domainConfig == null) {
               domainConfig = new DomainConfig(id);
               m_domainConfigs.put(id, domainConfig);
            }
         }
      }

      return domainConfig;
   }

   public Double getDefaultErrorResponseTime() {
      return m_defaultErrorResponseTime;
   }

   public Integer getDefaultErrorThreshold() {
      return m_defaultErrorThreshold;
   }

   public int getDefaultMinCountThreshold() {
      return m_defaultMinCountThreshold;
   }

   public Double getDefaultWarningResponseTime() {
      return m_defaultWarningResponseTime;
   }

   public Integer getDefaultWarningThreshold() {
      return m_defaultWarningThreshold;
   }

   public Map<String, DomainConfig> getDomainConfigs() {
      return m_domainConfigs;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(NodeConfig other) {
      assertAttributeEquals(other, ENTITY_NODE_CONFIG, ATTR_TYPE, m_type, other.getType());

      if (other.getDefaultWarningThreshold() != null) {
         m_defaultWarningThreshold = other.getDefaultWarningThreshold();
      }

      if (other.getDefaultErrorThreshold() != null) {
         m_defaultErrorThreshold = other.getDefaultErrorThreshold();
      }

      if (other.getDefaultWarningResponseTime() != null) {
         m_defaultWarningResponseTime = other.getDefaultWarningResponseTime();
      }

      if (other.getDefaultErrorResponseTime() != null) {
         m_defaultErrorResponseTime = other.getDefaultErrorResponseTime();
      }

      m_defaultMinCountThreshold = other.getDefaultMinCountThreshold();
   }

   public boolean removeDomainConfig(String id) {
      if (m_domainConfigs.containsKey(id)) {
         m_domainConfigs.remove(id);
         return true;
      }

      return false;
   }

   public NodeConfig setDefaultErrorResponseTime(Double defaultErrorResponseTime) {
      m_defaultErrorResponseTime = defaultErrorResponseTime;
      return this;
   }

   public NodeConfig setDefaultErrorThreshold(Integer defaultErrorThreshold) {
      m_defaultErrorThreshold = defaultErrorThreshold;
      return this;
   }

   public NodeConfig setDefaultMinCountThreshold(int defaultMinCountThreshold) {
      m_defaultMinCountThreshold = defaultMinCountThreshold;
      return this;
   }

   public NodeConfig setDefaultWarningResponseTime(Double defaultWarningResponseTime) {
      m_defaultWarningResponseTime = defaultWarningResponseTime;
      return this;
   }

   public NodeConfig setDefaultWarningThreshold(Integer defaultWarningThreshold) {
      m_defaultWarningThreshold = defaultWarningThreshold;
      return this;
   }

   public NodeConfig setType(String type) {
      m_type = type;
      return this;
   }

}
