package com.dianping.cat.home.network.transform;

import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public interface ILinker {

   public boolean onAnchor(NetTopology parent, Anchor anchor);

   public boolean onConnection(NetTopology parent, Connection connection);

   public boolean onInterface(Connection parent, Interface _interface);

   public boolean onNetGraph(NetGraphSet parent, NetGraph netGraph);

   public boolean onNetTopology(NetGraph parent, NetTopology netTopology);

   public boolean onSwitch(NetTopology parent, Switch _switch);
}
