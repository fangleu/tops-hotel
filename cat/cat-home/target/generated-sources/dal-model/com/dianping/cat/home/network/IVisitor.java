package com.dianping.cat.home.network;

import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public interface IVisitor {

   public void visitAnchor(Anchor anchor);

   public void visitConnection(Connection connection);

   public void visitInterface(Interface _interface);

   public void visitNetGraph(NetGraph netGraph);

   public void visitNetGraphSet(NetGraphSet netGraphSet);

   public void visitNetTopology(NetTopology netTopology);

   public void visitSwitch(Switch _switch);
}
