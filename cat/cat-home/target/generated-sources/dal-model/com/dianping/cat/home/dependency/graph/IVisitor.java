package com.dianping.cat.home.dependency.graph;

import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public interface IVisitor {

   public void visitTopologyEdge(TopologyEdge topologyEdge);

   public void visitTopologyGraph(TopologyGraph topologyGraph);

   public void visitTopologyNode(TopologyNode topologyNode);
}
