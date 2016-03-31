package com.dianping.cat.home.system.entity;

import static com.dianping.cat.home.system.Constants.ATTR_ID;
import static com.dianping.cat.home.system.Constants.ENTITY_ENTITY;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.system.BaseEntity;
import com.dianping.cat.home.system.IVisitor;

public class Entity extends BaseEntity<Entity> {
   private String m_id;

   private Rush m_rush;

   private Day m_day;

   private Map<String, Machine> m_machines = new LinkedHashMap<String, Machine>();

   public Entity() {
   }

   public Entity(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitEntity(this);
   }

   public Entity addMachine(Machine machine) {
      m_machines.put(machine.getIp(), machine);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Entity) {
         Entity _o = (Entity) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Machine findMachine(String ip) {
      return m_machines.get(ip);
   }

   public Machine findOrCreateMachine(String ip) {
      Machine machine = m_machines.get(ip);

      if (machine == null) {
         synchronized (m_machines) {
            machine = m_machines.get(ip);

            if (machine == null) {
               machine = new Machine(ip);
               m_machines.put(ip, machine);
            }
         }
      }

      return machine;
   }

   public Day getDay() {
      return m_day;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Machine> getMachines() {
      return m_machines;
   }

   public Rush getRush() {
      return m_rush;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Entity other) {
      assertAttributeEquals(other, ENTITY_ENTITY, ATTR_ID, m_id, other.getId());

   }

   public boolean removeMachine(String ip) {
      if (m_machines.containsKey(ip)) {
         m_machines.remove(ip);
         return true;
      }

      return false;
   }

   public Entity setDay(Day day) {
      m_day = day;
      return this;
   }

   public Entity setId(String id) {
      m_id = id;
      return this;
   }

   public Entity setRush(Rush rush) {
      m_rush = rush;
      return this;
   }

}
