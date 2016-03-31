package com.dianping.cat.home.dependency.config.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.dependency.config.BaseEntity;
import com.dianping.cat.home.dependency.config.IVisitor;

public class TopologyGraphConfig extends BaseEntity<TopologyGraphConfig> {
   private Map<String, NodeConfig> m_nodeConfigs = new LinkedHashMap<String, NodeConfig>();

   private Map<String, EdgeConfig> m_edgeConfigs = new LinkedHashMap<String, EdgeConfig>();

   public TopologyGraphConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTopologyGraphConfig(this);
   }

   public TopologyGraphConfig addEdgeConfig(EdgeConfig edgeConfig) {
      m_edgeConfigs.put(edgeConfig.getKey(), edgeConfig);
      return this;
   }

   public TopologyGraphConfig addNodeConfig(NodeConfig nodeConfig) {
      m_nodeConfigs.put(nodeConfig.getType(), nodeConfig);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TopologyGraphConfig) {
         TopologyGraphConfig _o = (TopologyGraphConfig) obj;
         Map<String, NodeConfig> nodeConfigs = _o.getNodeConfigs();
         Map<String, EdgeConfig> edgeConfigs = _o.getEdgeConfigs();
         boolean result = true;

         result &= (m_nodeConfigs == nodeConfigs || m_nodeConfigs != null && m_nodeConfigs.equals(nodeConfigs));
         result &= (m_edgeConfigs == edgeConfigs || m_edgeConfigs != null && m_edgeConfigs.equals(edgeConfigs));

         return result;
      }

      return false;
   }

   public EdgeConfig findEdgeConfig(String key) {
      return m_edgeConfigs.get(key);
   }

   public NodeConfig findNodeConfig(String type) {
      return m_nodeConfigs.get(type);
   }

   public EdgeConfig findOrCreateEdgeConfig(String key) {
      EdgeConfig edgeConfig = m_edgeConfigs.get(key);

      if (edgeConfig == null) {
         synchronized (m_edgeConfigs) {
            edgeConfig = m_edgeConfigs.get(key);

            if (edgeConfig == null) {
               edgeConfig = new EdgeConfig(key);
               m_edgeConfigs.put(key, edgeConfig);
            }
         }
      }

      return edgeConfig;
   }

   public NodeConfig findOrCreateNodeConfig(String type) {
      NodeConfig nodeConfig = m_nodeConfigs.get(type);

      if (nodeConfig == null) {
         synchronized (m_nodeConfigs) {
            nodeConfig = m_nodeConfigs.get(type);

            if (nodeConfig == null) {
               nodeConfig = new NodeConfig(type);
               m_nodeConfigs.put(type, nodeConfig);
            }
         }
      }

      return nodeConfig;
   }

   public Map<String, EdgeConfig> getEdgeConfigs() {
      return m_edgeConfigs;
   }

   public Map<String, NodeConfig> getNodeConfigs() {
      return m_nodeConfigs;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_nodeConfigs == null ? 0 : m_nodeConfigs.hashCode());
      hash = hash * 31 + (m_edgeConfigs == null ? 0 : m_edgeConfigs.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(TopologyGraphConfig other) {
   }

   public boolean removeEdgeConfig(String key) {
      if (m_edgeConfigs.containsKey(key)) {
         m_edgeConfigs.remove(key);
         return true;
      }

      return false;
   }

   public boolean removeNodeConfig(String type) {
      if (m_nodeConfigs.containsKey(type)) {
         m_nodeConfigs.remove(type);
         return true;
      }

      return false;
   }

}
