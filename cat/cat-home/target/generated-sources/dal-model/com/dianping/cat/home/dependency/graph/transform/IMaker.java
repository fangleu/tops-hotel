package com.dianping.cat.home.dependency.graph.transform;

import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public interface IMaker<T> {

   public TopologyEdge buildTopologyEdge(T node);

   public TopologyGraph buildTopologyGraph(T node);

   public TopologyNode buildTopologyNode(T node);
}
