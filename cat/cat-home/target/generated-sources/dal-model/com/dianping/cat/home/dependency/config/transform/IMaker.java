package com.dianping.cat.home.dependency.config.transform;

import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

public interface IMaker<T> {

   public DomainConfig buildDomainConfig(T node);

   public EdgeConfig buildEdgeConfig(T node);

   public NodeConfig buildNodeConfig(T node);

   public TopologyGraphConfig buildTopologyGraphConfig(T node);
}
