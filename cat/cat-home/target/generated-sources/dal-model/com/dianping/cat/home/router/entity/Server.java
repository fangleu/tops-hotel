package com.dianping.cat.home.router.entity;

import com.dianping.cat.home.router.BaseEntity;
import com.dianping.cat.home.router.IVisitor;

public class Server extends BaseEntity<Server> {
   private String m_id;

   private int m_port;

   private double m_weight = 1d;

   public Server() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitServer(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Server) {
         Server _o = (Server) obj;
         String id = _o.getId();
         int port = _o.getPort();
         double weight = _o.getWeight();
         boolean result = true;

         result &= (m_id == id || m_id != null && m_id.equals(id));
         result &= (m_port == port);
         result &= (m_weight == weight);

         return result;
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public int getPort() {
      return m_port;
   }

   public double getWeight() {
      return m_weight;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());
      hash = hash * 31 + m_port;
      hash = hash * 31 + (int) (Double.doubleToLongBits(m_weight) ^ (Double.doubleToLongBits(m_weight) >>> 32));

      return hash;
   }

   @Override
   public void mergeAttributes(Server other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }

      m_port = other.getPort();

      m_weight = other.getWeight();
   }

   public Server setId(String id) {
      m_id = id;
      return this;
   }

   public Server setPort(int port) {
      m_port = port;
      return this;
   }

   public Server setWeight(double weight) {
      m_weight = weight;
      return this;
   }

}
