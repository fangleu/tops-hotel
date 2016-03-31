package com.dianping.cat.home.dependency.graph.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.dependency.graph.BaseEntity;
import com.dianping.cat.home.dependency.graph.IVisitor;

public class TopologyGraph extends BaseEntity<TopologyGraph> {
   private String m_id;

   private String m_type;

   private int m_status;

   private String m_des;

   private Map<String, TopologyNode> m_nodes = new LinkedHashMap<String, TopologyNode>();

   private Map<String, TopologyEdge> m_edges = new LinkedHashMap<String, TopologyEdge>();

   public TopologyGraph() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTopologyGraph(this);
   }

   public TopologyGraph addTopologyEdge(TopologyEdge topologyEdge) {
      m_edges.put(topologyEdge.getKey(), topologyEdge);
      return this;
   }

   public TopologyGraph addTopologyNode(TopologyNode topologyNode) {
      m_nodes.put(topologyNode.getId(), topologyNode);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TopologyGraph) {
         TopologyGraph _o = (TopologyGraph) obj;
         String id = _o.getId();
         String type = _o.getType();
         int status = _o.getStatus();
         String des = _o.getDes();
         Map<String, TopologyNode> nodes = _o.getNodes();
         Map<String, TopologyEdge> edges = _o.getEdges();
         boolean result = true;

         result &= (m_id == id || m_id != null && m_id.equals(id));
         result &= (m_type == type || m_type != null && m_type.equals(type));
         result &= (m_status == status);
         result &= (m_des == des || m_des != null && m_des.equals(des));
         result &= (m_nodes == nodes || m_nodes != null && m_nodes.equals(nodes));
         result &= (m_edges == edges || m_edges != null && m_edges.equals(edges));

         return result;
      }

      return false;
   }

   public TopologyEdge findTopologyEdge(String key) {
      return m_edges.get(key);
   }

   public TopologyNode findTopologyNode(String id) {
      return m_nodes.get(id);
   }

   public TopologyEdge findOrCreateTopologyEdge(String key) {
      TopologyEdge topologyEdge = m_edges.get(key);

      if (topologyEdge == null) {
         synchronized (m_edges) {
            topologyEdge = m_edges.get(key);

            if (topologyEdge == null) {
               topologyEdge = new TopologyEdge(key);
               m_edges.put(key, topologyEdge);
            }
         }
      }

      return topologyEdge;
   }

   public TopologyNode findOrCreateTopologyNode(String id) {
      TopologyNode topologyNode = m_nodes.get(id);

      if (topologyNode == null) {
         synchronized (m_nodes) {
            topologyNode = m_nodes.get(id);

            if (topologyNode == null) {
               topologyNode = new TopologyNode(id);
               m_nodes.put(id, topologyNode);
            }
         }
      }

      return topologyNode;
   }

   public String getDes() {
      return m_des;
   }

   public Map<String, TopologyEdge> getEdges() {
      return m_edges;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, TopologyNode> getNodes() {
      return m_nodes;
   }

   public int getStatus() {
      return m_status;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());
      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
      hash = hash * 31 + m_status;
      hash = hash * 31 + (m_des == null ? 0 : m_des.hashCode());
      hash = hash * 31 + (m_nodes == null ? 0 : m_nodes.hashCode());
      hash = hash * 31 + (m_edges == null ? 0 : m_edges.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(TopologyGraph other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      m_status = other.getStatus();

      if (other.getDes() != null) {
         m_des = other.getDes();
      }
   }

   public boolean removeTopologyEdge(String key) {
      if (m_edges.containsKey(key)) {
         m_edges.remove(key);
         return true;
      }

      return false;
   }

   public boolean removeTopologyNode(String id) {
      if (m_nodes.containsKey(id)) {
         m_nodes.remove(id);
         return true;
      }

      return false;
   }

   public TopologyGraph setDes(String des) {
      m_des = des;
      return this;
   }

   public TopologyGraph setId(String id) {
      m_id = id;
      return this;
   }

   public TopologyGraph setStatus(int status) {
      m_status = status;
      return this;
   }

   public TopologyGraph setType(String type) {
      m_type = type;
      return this;
   }

}
