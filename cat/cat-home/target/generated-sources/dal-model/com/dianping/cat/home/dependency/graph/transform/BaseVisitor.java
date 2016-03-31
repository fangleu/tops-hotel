package com.dianping.cat.home.dependency.graph.transform;

import com.dianping.cat.home.dependency.graph.IVisitor;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitTopologyEdge(TopologyEdge topologyEdge) {
   }

   @Override
   public void visitTopologyGraph(TopologyGraph topologyGraph) {
      for (TopologyNode topologyNode : topologyGraph.getNodes().values()) {
         visitTopologyNode(topologyNode);
      }

      for (TopologyEdge topologyEdge : topologyGraph.getEdges().values()) {
         visitTopologyEdge(topologyEdge);
      }
   }

   @Override
   public void visitTopologyNode(TopologyNode topologyNode) {
   }
}
