package com.dianping.cat.home.network.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class NetTopology extends BaseEntity<NetTopology> {
   private String m_name;

   private List<Anchor> m_anchors = new ArrayList<Anchor>();

   private List<Switch> m_switchs = new ArrayList<Switch>();

   private List<Connection> m_connections = new ArrayList<Connection>();

   public NetTopology() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitNetTopology(this);
   }

   public NetTopology addAnchor(Anchor anchor) {
      m_anchors.add(anchor);
      return this;
   }

   public NetTopology addConnection(Connection connection) {
      m_connections.add(connection);
      return this;
   }

   public NetTopology addSwitch(Switch _switch) {
      m_switchs.add(_switch);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof NetTopology) {
         NetTopology _o = (NetTopology) obj;
         String name = _o.getName();
         List<Anchor> anchors = _o.getAnchors();
         List<Switch> switchs = _o.getSwitchs();
         List<Connection> connections = _o.getConnections();
         boolean result = true;

         result &= (m_name == name || m_name != null && m_name.equals(name));
         result &= (m_anchors == anchors || m_anchors != null && m_anchors.equals(anchors));
         result &= (m_switchs == switchs || m_switchs != null && m_switchs.equals(switchs));
         result &= (m_connections == connections || m_connections != null && m_connections.equals(connections));

         return result;
      }

      return false;
   }

   public List<Anchor> getAnchors() {
      return m_anchors;
   }

   public List<Connection> getConnections() {
      return m_connections;
   }

   public String getName() {
      return m_name;
   }

   public List<Switch> getSwitchs() {
      return m_switchs;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      hash = hash * 31 + (m_anchors == null ? 0 : m_anchors.hashCode());
      hash = hash * 31 + (m_switchs == null ? 0 : m_switchs.hashCode());
      hash = hash * 31 + (m_connections == null ? 0 : m_connections.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(NetTopology other) {
      if (other.getName() != null) {
         m_name = other.getName();
      }
   }

   public NetTopology setName(String name) {
      m_name = name;
      return this;
   }

}
