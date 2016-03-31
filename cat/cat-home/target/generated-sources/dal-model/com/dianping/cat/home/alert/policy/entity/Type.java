package com.dianping.cat.home.alert.policy.entity;

import static com.dianping.cat.home.alert.policy.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_TYPE;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.alert.policy.BaseEntity;
import com.dianping.cat.home.alert.policy.IVisitor;

public class Type extends BaseEntity<Type> {
   private String m_id;

   private Map<String, Group> m_groups = new LinkedHashMap<String, Group>();

   public Type() {
   }

   public Type(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitType(this);
   }

   public Type addGroup(Group group) {
      m_groups.put(group.getId(), group);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Type) {
         Type _o = (Type) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Group findGroup(String id) {
      return m_groups.get(id);
   }

   public Group findOrCreateGroup(String id) {
      Group group = m_groups.get(id);

      if (group == null) {
         synchronized (m_groups) {
            group = m_groups.get(id);

            if (group == null) {
               group = new Group(id);
               m_groups.put(id, group);
            }
         }
      }

      return group;
   }

   public Map<String, Group> getGroups() {
      return m_groups;
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
   public void mergeAttributes(Type other) {
      assertAttributeEquals(other, ENTITY_TYPE, ATTR_ID, m_id, other.getId());

   }

   public boolean removeGroup(String id) {
      if (m_groups.containsKey(id)) {
         m_groups.remove(id);
         return true;
      }

      return false;
   }

   public Type setId(String id) {
      m_id = id;
      return this;
   }

}
