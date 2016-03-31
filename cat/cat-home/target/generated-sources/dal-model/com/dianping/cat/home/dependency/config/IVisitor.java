package com.dianping.cat.home.dependency.config;

import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

public interface IVisitor {

   public void visitDomainConfig(DomainConfig domainConfig);

   public void visitEdgeConfig(EdgeConfig edgeConfig);

   public void visitNodeConfig(NodeConfig nodeConfig);

   public void visitTopologyGraphConfig(TopologyGraphConfig topologyGraphConfig);
}
