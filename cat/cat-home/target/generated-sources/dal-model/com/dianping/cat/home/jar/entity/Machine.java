package com.dianping.cat.home.jar.entity;

import static com.dianping.cat.home.jar.Constants.ATTR_ID;
import static com.dianping.cat.home.jar.Constants.ENTITY_MACHINE;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.jar.BaseEntity;
import com.dianping.cat.home.jar.IVisitor;

public class Machine extends BaseEntity<Machine> {
   private String m_id;

   private List<Jar> m_jars = new ArrayList<Jar>();

   public Machine() {
   }

   public Machine(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMachine(this);
   }

   public Machine addJar(Jar jar) {
      m_jars.add(jar);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Machine) {
         Machine _o = (Machine) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public List<Jar> getJars() {
      return m_jars;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Machine other) {
      assertAttributeEquals(other, ENTITY_MACHINE, ATTR_ID, m_id, other.getId());

   }

   public Machine setId(String id) {
      m_id = id;
      return this;
   }

}
