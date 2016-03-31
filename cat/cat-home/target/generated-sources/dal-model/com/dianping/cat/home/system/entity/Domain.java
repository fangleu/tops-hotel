package com.dianping.cat.home.system.entity;

import static com.dianping.cat.home.system.Constants.ATTR_ID;
import static com.dianping.cat.home.system.Constants.ENTITY_DOMAIN;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.system.BaseEntity;
import com.dianping.cat.home.system.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private Map<String, Entity> m_entities = new LinkedHashMap<String, Entity>();

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addEntity(Entity entity) {
      m_entities.put(entity.getId(), entity);
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

   public String getId() {
      return m_id;
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

   public boolean removeEntity(String id) {
      if (m_entities.containsKey(id)) {
         m_entities.remove(id);
         return true;
      }

      return false;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

}
