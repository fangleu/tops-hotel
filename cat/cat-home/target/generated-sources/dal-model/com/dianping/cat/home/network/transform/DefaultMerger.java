package com.dianping.cat.home.network.transform;

import java.util.Stack;

import com.dianping.cat.home.network.IEntity;
import com.dianping.cat.home.network.IVisitor;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private NetGraphSet m_netGraphSet;

   public DefaultMerger() {
   }

   public DefaultMerger(NetGraphSet netGraphSet) {
      m_netGraphSet = netGraphSet;
      m_objs.push(netGraphSet);
   }

   public NetGraphSet getNetGraphSet() {
      return m_netGraphSet;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeAnchor(Anchor to, Anchor from) {
      to.mergeAttributes(from);
   }

   protected void mergeConnection(Connection to, Connection from) {
      to.mergeAttributes(from);
   }

   protected void mergeInterface(Interface to, Interface from) {
      to.mergeAttributes(from);
   }

   protected void mergeNetGraph(NetGraph to, NetGraph from) {
      to.mergeAttributes(from);
   }

   protected void mergeNetGraphSet(NetGraphSet to, NetGraphSet from) {
      to.mergeAttributes(from);
   }

   protected void mergeNetTopology(NetTopology to, NetTopology from) {
      to.mergeAttributes(from);
   }

   protected void mergeSwitch(Switch to, Switch from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitAnchor(Anchor from) {
      Anchor to = (Anchor) m_objs.peek();

      mergeAnchor(to, from);
      visitAnchorChildren(to, from);
   }

   protected void visitAnchorChildren(Anchor to, Anchor from) {
   }

   @Override
   public void visitConnection(Connection from) {
      Connection to = (Connection) m_objs.peek();

      mergeConnection(to, from);
      visitConnectionChildren(to, from);
   }

   protected void visitConnectionChildren(Connection to, Connection from) {
      for (Interface source : from.getInterfaces()) {
         Interface target = null;

         if (target == null) {
            target = new Interface();
            to.addInterface(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitInterface(Interface from) {
      Interface to = (Interface) m_objs.peek();

      mergeInterface(to, from);
      visitInterfaceChildren(to, from);
   }

   protected void visitInterfaceChildren(Interface to, Interface from) {
   }

   @Override
   public void visitNetGraph(NetGraph from) {
      NetGraph to = (NetGraph) m_objs.peek();

      mergeNetGraph(to, from);
      visitNetGraphChildren(to, from);
   }

   protected void visitNetGraphChildren(NetGraph to, NetGraph from) {
      for (NetTopology source : from.getNetTopologies()) {
         NetTopology target = null;

         if (target == null) {
            target = new NetTopology();
            to.addNetTopology(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitNetGraphSet(NetGraphSet from) {
      NetGraphSet to = (NetGraphSet) m_objs.peek();

      mergeNetGraphSet(to, from);
      visitNetGraphSetChildren(to, from);
   }

   protected void visitNetGraphSetChildren(NetGraphSet to, NetGraphSet from) {
      for (NetGraph source : from.getNetGraphs().values()) {
         NetGraph target = to.findNetGraph(source.getMinute());

         if (target == null) {
            target = new NetGraph(source.getMinute());
            to.addNetGraph(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitNetTopology(NetTopology from) {
      NetTopology to = (NetTopology) m_objs.peek();

      mergeNetTopology(to, from);
      visitNetTopologyChildren(to, from);
   }

   protected void visitNetTopologyChildren(NetTopology to, NetTopology from) {
      for (Anchor source : from.getAnchors()) {
         Anchor target = null;

         if (target == null) {
            target = new Anchor();
            to.addAnchor(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Switch source : from.getSwitchs()) {
         Switch target = null;

         if (target == null) {
            target = new Switch();
            to.addSwitch(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Connection source : from.getConnections()) {
         Connection target = null;

         if (target == null) {
            target = new Connection();
            to.addConnection(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitSwitch(Switch from) {
      Switch to = (Switch) m_objs.peek();

      mergeSwitch(to, from);
      visitSwitchChildren(to, from);
   }

   protected void visitSwitchChildren(Switch to, Switch from) {
   }
}
