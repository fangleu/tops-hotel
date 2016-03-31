package com.dianping.cat.home.network.transform;

import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public interface IMaker<T> {

   public Anchor buildAnchor(T node);

   public Connection buildConnection(T node);

   public Interface buildInterface(T node);

   public NetGraph buildNetGraph(T node);

   public NetGraphSet buildNetGraphSet(T node);

   public NetTopology buildNetTopology(T node);

   public Switch buildSwitch(T node);
}
