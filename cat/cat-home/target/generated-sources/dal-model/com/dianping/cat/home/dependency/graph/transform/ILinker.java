package com.dianping.cat.home.dependency.graph.transform;

import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public interface ILinker {

   public boolean onTopologyEdge(TopologyGraph parent, TopologyEdge topologyEdge);

   public boolean onTopologyNode(TopologyGraph parent, TopologyNode topologyNode);
}
