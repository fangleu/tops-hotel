package com.dianping.cat.home.storage.alert.entity;

import static com.dianping.cat.home.storage.alert.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_OPERATION;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.storage.alert.BaseEntity;
import com.dianping.cat.home.storage.alert.IVisitor;

public class Operation extends BaseEntity<Operation> {
   private String m_id;

   private int m_level;

   private int m_count;

   private Map<String, Target> m_targets = new LinkedHashMap<String, Target>();

   public Operation() {
   }

   public Operation(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitOperation(this);
   }

   public Operation addTarget(Target target) {
      m_targets.put(target.getId(), target);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Operation) {
         Operation _o = (Operation) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Target findTarget(String id) {
      return m_targets.get(id);
   }

   public Target findOrCreateTarget(String id) {
      Target target = m_targets.get(id);

      if (target == null) {
         synchronized (m_targets) {
            target = m_targets.get(id);

            if (target == null) {
               target = new Target(id);
               m_targets.put(id, target);
            }
         }
      }

      return target;
   }

   public int getCount() {
      return m_count;
   }

   public String getId() {
      return m_id;
   }

   public int getLevel() {
      return m_level;
   }

   public Map<String, Target> getTargets() {
      return m_targets;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public Operation incCount() {
      m_count++;
      return this;
   }

   public Operation incCount(int count) {
      m_count += count;
      return this;
   }

   @Override
   public void mergeAttributes(Operation other) {
      assertAttributeEquals(other, ENTITY_OPERATION, ATTR_ID, m_id, other.getId());

      m_level = other.getLevel();

      m_count = other.getCount();
   }

   public boolean removeTarget(String id) {
      if (m_targets.containsKey(id)) {
         m_targets.remove(id);
         return true;
      }

      return false;
   }

   public Operation setCount(int count) {
      m_count = count;
      return this;
   }

   public Operation setId(String id) {
      m_id = id;
      return this;
   }

   public Operation setLevel(int level) {
      m_level = level;
      return this;
   }

}
