package com.dianping.cat.home.router.entity;

import com.dianping.cat.home.router.BaseEntity;
import com.dianping.cat.home.router.IVisitor;

public class DefaultServer extends BaseEntity<DefaultServer> {
   private String m_id;

   private int m_port;

   private boolean m_enable;

   private double m_weight = 1d;

   public DefaultServer() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDefaultServer(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof DefaultServer) {
         DefaultServer _o = (DefaultServer) obj;
         String id = _o.getId();
         int port = _o.getPort();
         boolean enable = _o.getEnable();
         double weight = _o.getWeight();
         boolean result = true;

         result &= (m_id == id || m_id != null && m_id.equals(id));
         result &= (m_port == port);
         result &= (m_enable == enable);
         result &= (m_weight == weight);

         return result;
      }

      return false;
   }

   public boolean getEnable() {
      return m_enable;
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
      hash = hash * 31 + (m_enable ? 1 : 0);
      hash = hash * 31 + (int) (Double.doubleToLongBits(m_weight) ^ (Double.doubleToLongBits(m_weight) >>> 32));

      return hash;
   }

   public boolean isEnable() {
      return m_enable;
   }

   @Override
   public void mergeAttributes(DefaultServer other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }

      m_port = other.getPort();

      m_enable = other.getEnable();

      m_weight = other.getWeight();
   }

   public DefaultServer setEnable(boolean enable) {
      m_enable = enable;
      return this;
   }

   public DefaultServer setId(String id) {
      m_id = id;
      return this;
   }

   public DefaultServer setPort(int port) {
      m_port = port;
      return this;
   }

   public DefaultServer setWeight(double weight) {
      m_weight = weight;
      return this;
   }

}
