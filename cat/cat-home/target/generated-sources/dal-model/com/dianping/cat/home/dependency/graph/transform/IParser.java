package com.dianping.cat.home.dependency.graph.transform;

import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public interface IParser<T> {
   public TopologyGraph parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForTopologyEdge(IMaker<T> maker, ILinker linker, TopologyEdge parent, T node);

   public void parseForTopologyNode(IMaker<T> maker, ILinker linker, TopologyNode parent, T node);
}
