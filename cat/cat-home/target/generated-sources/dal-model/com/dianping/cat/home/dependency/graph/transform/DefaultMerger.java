package com.dianping.cat.home.dependency.graph.transform;

import java.util.Stack;

import com.dianping.cat.home.dependency.graph.IEntity;
import com.dianping.cat.home.dependency.graph.IVisitor;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private TopologyGraph m_topologyGraph;

   public DefaultMerger() {
   }

   public DefaultMerger(TopologyGraph topologyGraph) {
      m_topologyGraph = topologyGraph;
      m_objs.push(topologyGraph);
   }

   public TopologyGraph getTopologyGraph() {
      return m_topologyGraph;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeTopologyEdge(TopologyEdge to, TopologyEdge from) {
      to.mergeAttributes(from);
   }

   protected void mergeTopologyGraph(TopologyGraph to, TopologyGraph from) {
      to.mergeAttributes(from);
   }

   protected void mergeTopologyNode(TopologyNode to, TopologyNode from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitTopologyEdge(TopologyEdge from) {
      TopologyEdge to = (TopologyEdge) m_objs.peek();

      mergeTopologyEdge(to, from);
      visitTopologyEdgeChildren(to, from);
   }

   protected void visitTopologyEdgeChildren(TopologyEdge to, TopologyEdge from) {
   }

   @Override
   public void visitTopologyGraph(TopologyGraph from) {
      TopologyGraph to = (TopologyGraph) m_objs.peek();

      mergeTopologyGraph(to, from);
      visitTopologyGraphChildren(to, from);
   }

   protected void visitTopologyGraphChildren(TopologyGraph to, TopologyGraph from) {
      for (TopologyNode source : from.getNodes().values()) {
         TopologyNode target = to.findTopologyNode(source.getId());

         if (target == null) {
            target = new TopologyNode(source.getId());
            to.addTopologyNode(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (TopologyEdge source : from.getEdges().values()) {
         TopologyEdge target = to.findTopologyEdge(source.getKey());

         if (target == null) {
            target = new TopologyEdge(source.getKey());
            to.addTopologyEdge(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitTopologyNode(TopologyNode from) {
      TopologyNode to = (TopologyNode) m_objs.peek();

      mergeTopologyNode(to, from);
      visitTopologyNodeChildren(to, from);
   }

   protected void visitTopologyNodeChildren(TopologyNode to, TopologyNode from) {
   }
}
