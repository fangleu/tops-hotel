package com.dianping.cat.home.network.transform;

import com.dianping.cat.home.network.IVisitor;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitAnchor(Anchor anchor) {
   }

   @Override
   public void visitConnection(Connection connection) {
      for (Interface interface_ : connection.getInterfaces()) {
         visitInterface(interface_);
      }
   }

   @Override
   public void visitInterface(Interface _interface) {
   }

   @Override
   public void visitNetGraph(NetGraph netGraph) {
      for (NetTopology netTopology : netGraph.getNetTopologies()) {
         visitNetTopology(netTopology);
      }
   }

   @Override
   public void visitNetGraphSet(NetGraphSet netGraphSet) {
      for (NetGraph netGraph : netGraphSet.getNetGraphs().values()) {
         visitNetGraph(netGraph);
      }
   }

   @Override
   public void visitNetTopology(NetTopology netTopology) {
      for (Anchor anchor : netTopology.getAnchors()) {
         visitAnchor(anchor);
      }

      for (Switch switch_ : netTopology.getSwitchs()) {
         visitSwitch(switch_);
      }

      for (Connection connection : netTopology.getConnections()) {
         visitConnection(connection);
      }
   }

   @Override
   public void visitSwitch(Switch _switch) {
   }
}
