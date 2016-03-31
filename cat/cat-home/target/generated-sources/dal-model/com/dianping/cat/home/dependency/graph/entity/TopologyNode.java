package com.dianping.cat.home.dependency.graph.entity;

import static com.dianping.cat.home.dependency.graph.Constants.ATTR_ID;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_NODE;

import com.dianping.cat.home.dependency.graph.BaseEntity;
import com.dianping.cat.home.dependency.graph.IVisitor;

public class TopologyNode extends BaseEntity<TopologyNode> {
   private String m_id;

   private String m_type;

   private int m_status;

   private String m_des = "";

   private String m_link = "";

   private int m_weight;

   public TopologyNode() {
   }

   public TopologyNode(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTopologyNode(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TopologyNode) {
         TopologyNode _o = (TopologyNode) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getDes() {
      return m_des;
   }

   public String getId() {
      return m_id;
   }

   public String getLink() {
      return m_link;
   }

   public int getStatus() {
      return m_status;
   }

   public String getType() {
      return m_type;
   }

   public int getWeight() {
      return m_weight;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(TopologyNode other) {
      assertAttributeEquals(other, ENTITY_TOPOLOGY_NODE, ATTR_ID, m_id, other.getId());

      if (other.getType() != null) {
         m_type = other.getType();
      }

      m_status = other.getStatus();

      if (other.getDes() != null) {
         m_des = other.getDes();
      }

      if (other.getLink() != null) {
         m_link = other.getLink();
      }

      m_weight = other.getWeight();
   }

   public TopologyNode setDes(String des) {
      m_des = des;
      return this;
   }

   public TopologyNode setId(String id) {
      m_id = id;
      return this;
   }

   public TopologyNode setLink(String link) {
      m_link = link;
      return this;
   }

   public TopologyNode setStatus(int status) {
      m_status = status;
      return this;
   }

   public TopologyNode setType(String type) {
      m_type = type;
      return this;
   }

   public TopologyNode setWeight(int weight) {
      m_weight = weight;
      return this;
   }

}
