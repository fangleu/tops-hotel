package com.dianping.cat.consumer.problem.model.entity;

import static com.dianping.cat.consumer.problem.model.Constants.ATTR_IP;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_MACHINE;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dianping.cat.consumer.problem.model.BaseEntity;
import com.dianping.cat.consumer.problem.model.IVisitor;

public class Machine extends BaseEntity<Machine> {
   private String m_ip;

   private List<Entry> m_entries = new ArrayList<Entry>();

   private Map<String, Entity> m_entities = new LinkedHashMap<String, Entity>();

   public Machine() {
   }

   public Machine(String ip) {
      m_ip = ip;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMachine(this);
   }

   public Machine addEntity(Entity entity) {
      m_entities.put(entity.getId(), entity);
      return this;
   }

   public Machine addEntry(Entry entry) {
      m_entries.add(entry);
      return this;
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

   public Entity findEntity(String id) {
      return m_entities.get(id);
   }

   public Entity findOrCreateEntity(String id) {
      Entity entity = m_entities.get(id);

      if (entity == null) {
         synchronized (m_entities) {
            entity = m_entities.get(id);

            if (entity == null) {
               entity = new Entity(id);
               m_entities.put(id, entity);
            }
         }
      }

      return entity;
   }

   public Map<String, Entity> getEntities() {
      return m_entities;
   }

   public List<Entry> getEntries() {
      return m_entries;
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

   public boolean removeEntity(String id) {
      if (m_entities.containsKey(id)) {
         m_entities.remove(id);
         return true;
      }

      return false;
   }

   public Machine setIp(String ip) {
      m_ip = ip;
      return this;
   }

}
