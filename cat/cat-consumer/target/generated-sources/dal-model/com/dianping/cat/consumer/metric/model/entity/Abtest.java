package com.dianping.cat.consumer.metric.model.entity;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_RUN_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_ABTEST;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.metric.model.BaseEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;

public class Abtest extends BaseEntity<Abtest> {
   private String m_runId;

   private String m_name;

   private Map<String, Group> m_groups = new LinkedHashMap<String, Group>();

   public Abtest() {
   }

   public Abtest(String runId) {
      m_runId = runId;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAbtest(this);
   }

   public Abtest addGroup(Group group) {
      m_groups.put(group.getName(), group);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Abtest) {
         Abtest _o = (Abtest) obj;
         String runId = _o.getRunId();

         return m_runId == runId || m_runId != null && m_runId.equals(runId);
      }

      return false;
   }

   public Group findGroup(String name) {
      return m_groups.get(name);
   }

   public Group findOrCreateGroup(String name) {
      Group group = m_groups.get(name);

      if (group == null) {
         synchronized (m_groups) {
            group = m_groups.get(name);

            if (group == null) {
               group = new Group(name);
               m_groups.put(name, group);
            }
         }
      }

      return group;
   }

   public Map<String, Group> getGroups() {
      return m_groups;
   }

   public String getName() {
      return m_name;
   }

   public String getRunId() {
      return m_runId;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_runId == null ? 0 : m_runId.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Abtest other) {
      assertAttributeEquals(other, ENTITY_ABTEST, ATTR_RUN_ID, m_runId, other.getRunId());

      if (other.getName() != null) {
         m_name = other.getName();
      }
   }

   public boolean removeGroup(String name) {
      if (m_groups.containsKey(name)) {
         m_groups.remove(name);
         return true;
      }

      return false;
   }

   public Abtest setName(String name) {
      m_name = name;
      return this;
   }

   public Abtest setRunId(String runId) {
      m_runId = runId;
      return this;
   }

}
