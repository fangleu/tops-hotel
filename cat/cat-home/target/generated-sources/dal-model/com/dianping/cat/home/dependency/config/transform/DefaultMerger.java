package com.dianping.cat.home.dependency.config.transform;

import java.util.Stack;

import com.dianping.cat.home.dependency.config.IEntity;
import com.dianping.cat.home.dependency.config.IVisitor;
import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private TopologyGraphConfig m_topologyGraphConfig;

   public DefaultMerger() {
   }

   public DefaultMerger(TopologyGraphConfig topologyGraphConfig) {
      m_topologyGraphConfig = topologyGraphConfig;
      m_objs.push(topologyGraphConfig);
   }

   public TopologyGraphConfig getTopologyGraphConfig() {
      return m_topologyGraphConfig;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDomainConfig(DomainConfig to, DomainConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeEdgeConfig(EdgeConfig to, EdgeConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeNodeConfig(NodeConfig to, NodeConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeTopologyGraphConfig(TopologyGraphConfig to, TopologyGraphConfig from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDomainConfig(DomainConfig from) {
      DomainConfig to = (DomainConfig) m_objs.peek();

      mergeDomainConfig(to, from);
      visitDomainConfigChildren(to, from);
   }

   protected void visitDomainConfigChildren(DomainConfig to, DomainConfig from) {
   }

   @Override
   public void visitEdgeConfig(EdgeConfig from) {
      EdgeConfig to = (EdgeConfig) m_objs.peek();

      mergeEdgeConfig(to, from);
      visitEdgeConfigChildren(to, from);
   }

   protected void visitEdgeConfigChildren(EdgeConfig to, EdgeConfig from) {
   }

   @Override
   public void visitNodeConfig(NodeConfig from) {
      NodeConfig to = (NodeConfig) m_objs.peek();

      mergeNodeConfig(to, from);
      visitNodeConfigChildren(to, from);
   }

   protected void visitNodeConfigChildren(NodeConfig to, NodeConfig from) {
      for (DomainConfig source : from.getDomainConfigs().values()) {
         DomainConfig target = to.findDomainConfig(source.getId());

         if (target == null) {
            target = new DomainConfig(source.getId());
            to.addDomainConfig(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitTopologyGraphConfig(TopologyGraphConfig from) {
      TopologyGraphConfig to = (TopologyGraphConfig) m_objs.peek();

      mergeTopologyGraphConfig(to, from);
      visitTopologyGraphConfigChildren(to, from);
   }

   protected void visitTopologyGraphConfigChildren(TopologyGraphConfig to, TopologyGraphConfig from) {
      for (NodeConfig source : from.getNodeConfigs().values()) {
         NodeConfig target = to.findNodeConfig(source.getType());

         if (target == null) {
            target = new NodeConfig(source.getType());
            to.addNodeConfig(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (EdgeConfig source : from.getEdgeConfigs().values()) {
         EdgeConfig target = to.findEdgeConfig(source.getKey());

         if (target == null) {
            target = new EdgeConfig(source.getKey());
            to.addEdgeConfig(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
