package com.dianping.cat.home.alert.policy.entity;

import static com.dianping.cat.home.alert.policy.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_GROUP;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.alert.policy.BaseEntity;
import com.dianping.cat.home.alert.policy.IVisitor;

public class Group extends BaseEntity<Group> {
   private String m_id;

   private Map<String, Level> m_levels = new LinkedHashMap<String, Level>();

   public Group() {
   }

   public Group(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitGroup(this);
   }

   public Group addLevel(Level level) {
      m_levels.put(level.getId(), level);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Group) {
         Group _o = (Group) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Level findLevel(String id) {
      return m_levels.get(id);
   }

   public Level findOrCreateLevel(String id) {
      Level level = m_levels.get(id);

      if (level == null) {
         synchronized (m_levels) {
            level = m_levels.get(id);

            if (level == null) {
               level = new Level(id);
               m_levels.put(id, level);
            }
         }
      }

      return level;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Level> getLevels() {
      return m_levels;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Group other) {
      assertAttributeEquals(other, ENTITY_GROUP, ATTR_ID, m_id, other.getId());

   }

   public boolean removeLevel(String id) {
      if (m_levels.containsKey(id)) {
         m_levels.remove(id);
         return true;
      }

      return false;
   }

   public Group setId(String id) {
      m_id = id;
      return this;
   }

}
