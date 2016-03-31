package com.dianping.cat.home.storage.alert.entity;

import static com.dianping.cat.home.storage.alert.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_MACHINE;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.storage.alert.BaseEntity;
import com.dianping.cat.home.storage.alert.IVisitor;

public class Machine extends BaseEntity<Machine> {
   private String m_id;

   private int m_level;

   private int m_count;

   private Map<String, Operation> m_operations = new LinkedHashMap<String, Operation>();

   public Machine() {
   }

   public Machine(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMachine(this);
   }

   public Machine addOperation(Operation operation) {
      m_operations.put(operation.getId(), operation);
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

   public Operation findOperation(String id) {
      return m_operations.get(id);
   }

   public Operation findOrCreateOperation(String id) {
      Operation operation = m_operations.get(id);

      if (operation == null) {
         synchronized (m_operations) {
            operation = m_operations.get(id);

            if (operation == null) {
               operation = new Operation(id);
               m_operations.put(id, operation);
            }
         }
      }

      return operation;
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

   public Map<String, Operation> getOperations() {
      return m_operations;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public Machine incCount() {
      m_count++;
      return this;
   }

   public Machine incCount(int count) {
      m_count += count;
      return this;
   }

   @Override
   public void mergeAttributes(Machine other) {
      assertAttributeEquals(other, ENTITY_MACHINE, ATTR_ID, m_id, other.getId());

      m_level = other.getLevel();

      m_count = other.getCount();
   }

   public boolean removeOperation(String id) {
      if (m_operations.containsKey(id)) {
         m_operations.remove(id);
         return true;
      }

      return false;
   }

   public Machine setCount(int count) {
      m_count = count;
      return this;
   }

   public Machine setId(String id) {
      m_id = id;
      return this;
   }

   public Machine setLevel(int level) {
      m_level = level;
      return this;
   }

}
