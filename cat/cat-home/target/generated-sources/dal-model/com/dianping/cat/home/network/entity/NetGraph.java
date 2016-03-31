package com.dianping.cat.home.network.entity;

import static com.dianping.cat.home.network.Constants.ATTR_MINUTE;
import static com.dianping.cat.home.network.Constants.ENTITY_NETGRAPH;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class NetGraph extends BaseEntity<NetGraph> {
   private Integer m_minute;

   private List<NetTopology> m_netTopologies = new ArrayList<NetTopology>();

   public NetGraph() {
   }

   public NetGraph(Integer minute) {
      m_minute = minute;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitNetGraph(this);
   }

   public NetGraph addNetTopology(NetTopology netTopology) {
      m_netTopologies.add(netTopology);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof NetGraph) {
         NetGraph _o = (NetGraph) obj;
         Integer minute = _o.getMinute();

         return m_minute == minute || m_minute != null && m_minute.equals(minute);
      }

      return false;
   }

   public Integer getMinute() {
      return m_minute;
   }

   public List<NetTopology> getNetTopologies() {
      return m_netTopologies;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_minute == null ? 0 : m_minute.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(NetGraph other) {
      assertAttributeEquals(other, ENTITY_NETGRAPH, ATTR_MINUTE, m_minute, other.getMinute());

   }

   public NetGraph setMinute(Integer minute) {
      m_minute = minute;
      return this;
   }

}
