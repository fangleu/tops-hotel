package com.dianping.cat.home.jar.entity;

import static com.dianping.cat.home.jar.Constants.ATTR_ID;
import static com.dianping.cat.home.jar.Constants.ENTITY_DOMAIN;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.jar.BaseEntity;
import com.dianping.cat.home.jar.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private Map<String, Machine> m_machines = new LinkedHashMap<String, Machine>();

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addMachine(Machine machine) {
      m_machines.put(machine.getId(), machine);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Domain) {
         Domain _o = (Domain) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Machine findMachine(String id) {
      return m_machines.get(id);
   }

   public Machine findOrCreateMachine(String id) {
      Machine machine = m_machines.get(id);

      if (machine == null) {
         synchronized (m_machines) {
            machine = m_machines.get(id);

            if (machine == null) {
               machine = new Machine(id);
               m_machines.put(id, machine);
            }
         }
      }

      return machine;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Machine> getMachines() {
      return m_machines;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Domain other) {
      assertAttributeEquals(other, ENTITY_DOMAIN, ATTR_ID, m_id, other.getId());

   }

   public boolean removeMachine(String id) {
      if (m_machines.containsKey(id)) {
         m_machines.remove(id);
         return true;
      }

      return false;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

}
