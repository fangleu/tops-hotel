package com.dianping.cat.home.dependency.graph.entity;

import static com.dianping.cat.home.dependency.graph.Constants.ATTR_KEY;
import static com.dianping.cat.home.dependency.graph.Constants.ENTITY_TOPOLOGY_EDGE;

import com.dianping.cat.home.dependency.graph.BaseEntity;
import com.dianping.cat.home.dependency.graph.IVisitor;

public class TopologyEdge extends BaseEntity<TopologyEdge> {
   private String m_key;

   private String m_type;

   private String m_target;

   private boolean m_opposite;

   private int m_weight;

   private int m_status;

   private String m_des = "";

   private String m_link = "";

   private String m_self;

   private boolean m_dashed = false;

   public TopologyEdge() {
   }

   public TopologyEdge(String key) {
      m_key = key;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTopologyEdge(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TopologyEdge) {
         TopologyEdge _o = (TopologyEdge) obj;
         String key = _o.getKey();

         return m_key == key || m_key != null && m_key.equals(key);
      }

      return false;
   }

   public boolean getDashed() {
      return m_dashed;
   }

   public String getDes() {
      return m_des;
   }

   public String getKey() {
      return m_key;
   }

   public String getLink() {
      return m_link;
   }

   public boolean getOpposite() {
      return m_opposite;
   }

   public String getSelf() {
      return m_self;
   }

   public int getStatus() {
      return m_status;
   }

   public String getTarget() {
      return m_target;
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

      hash = hash * 31 + (m_key == null ? 0 : m_key.hashCode());

      return hash;
   }

   public boolean isDashed() {
      return m_dashed;
   }

   public boolean isOpposite() {
      return m_opposite;
   }

   @Override
   public void mergeAttributes(TopologyEdge other) {
      assertAttributeEquals(other, ENTITY_TOPOLOGY_EDGE, ATTR_KEY, m_key, other.getKey());

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getTarget() != null) {
         m_target = other.getTarget();
      }

      m_opposite = other.getOpposite();

      m_weight = other.getWeight();

      m_status = other.getStatus();

      if (other.getDes() != null) {
         m_des = other.getDes();
      }

      if (other.getLink() != null) {
         m_link = other.getLink();
      }

      if (other.getSelf() != null) {
         m_self = other.getSelf();
      }

      m_dashed = other.getDashed();
   }

   public TopologyEdge setDashed(boolean dashed) {
      m_dashed = dashed;
      return this;
   }

   public TopologyEdge setDes(String des) {
      m_des = des;
      return this;
   }

   public TopologyEdge setKey(String key) {
      m_key = key;
      return this;
   }

   public TopologyEdge setLink(String link) {
      m_link = link;
      return this;
   }

   public TopologyEdge setOpposite(boolean opposite) {
      m_opposite = opposite;
      return this;
   }

   public TopologyEdge setSelf(String self) {
      m_self = self;
      return this;
   }

   public TopologyEdge setStatus(int status) {
      m_status = status;
      return this;
   }

   public TopologyEdge setTarget(String target) {
      m_target = target;
      return this;
   }

   public TopologyEdge setType(String type) {
      m_type = type;
      return this;
   }

   public TopologyEdge setWeight(int weight) {
      m_weight = weight;
      return this;
   }

}
