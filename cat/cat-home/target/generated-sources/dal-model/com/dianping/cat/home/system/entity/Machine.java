package com.dianping.cat.home.system.entity;

import static com.dianping.cat.home.system.Constants.ATTR_IP;
import static com.dianping.cat.home.system.Constants.ENTITY_MACHINE;

import com.dianping.cat.home.system.BaseEntity;
import com.dianping.cat.home.system.IVisitor;

public class Machine extends BaseEntity<Machine> {
   private String m_ip;

   public Machine() {
   }

   public Machine(String ip) {
      m_ip = ip;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMachine(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Machine) {
         Machine _o = (Machine) obj;
         String ip = _o.getIp();

         return m_ip == ip || m_ip != null && m_ip.equals(ip);
      }

      return false;
   }

   public String getIp() {
      return m_ip;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_ip == null ? 0 : m_ip.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Machine other) {
      assertAttributeEquals(other, ENTITY_MACHINE, ATTR_IP, m_ip, other.getIp());

   }

   public Machine setIp(String ip) {
      m_ip = ip;
      return this;
   }

}
