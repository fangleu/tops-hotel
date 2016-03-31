package com.dianping.cat.home.dependency.config.transform;

import com.dianping.cat.home.dependency.config.IVisitor;
import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDomainConfig(DomainConfig domainConfig) {
   }

   @Override
   public void visitEdgeConfig(EdgeConfig edgeConfig) {
   }

   @Override
   public void visitNodeConfig(NodeConfig nodeConfig) {
      for (DomainConfig domainConfig : nodeConfig.getDomainConfigs().values()) {
         visitDomainConfig(domainConfig);
      }
   }

   @Override
   public void visitTopologyGraphConfig(TopologyGraphConfig topologyGraphConfig) {
      for (NodeConfig nodeConfig : topologyGraphConfig.getNodeConfigs().values()) {
         visitNodeConfig(nodeConfig);
      }

      for (EdgeConfig edgeConfig : topologyGraphConfig.getEdgeConfigs().values()) {
         visitEdgeConfig(edgeConfig);
      }
   }
}
