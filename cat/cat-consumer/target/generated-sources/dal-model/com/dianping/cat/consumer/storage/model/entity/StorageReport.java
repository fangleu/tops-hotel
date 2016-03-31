package com.dianping.cat.consumer.storage.model.entity;

import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_STORAGE_REPORT;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dianping.cat.consumer.storage.model.BaseEntity;
import com.dianping.cat.consumer.storage.model.IVisitor;

public class StorageReport extends BaseEntity<StorageReport> {
   private String m_id;

   private String m_name;

   private String m_type;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private Set<String> m_ids = new LinkedHashSet<String>();

   private Set<String> m_ips = new LinkedHashSet<String>();

   private Set<String> m_ops = new LinkedHashSet<String>();

   private Map<String, Machine> m_machines = new LinkedHashMap<String, Machine>();

   public StorageReport() {
   }

   public StorageReport(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitStorageReport(this);
   }

   public StorageReport addId(String id) {
      m_ids.add(id);
      return this;
   }

   public StorageReport addIp(String ip) {
      m_ips.add(ip);
      return this;
   }

   public StorageReport addMachine(Machine machine) {
      m_machines.put(machine.getId(), machine);
      return this;
   }

   public StorageReport addOp(String op) {
      m_ops.add(op);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof StorageReport) {
         StorageReport _o = (StorageReport) obj;
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

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public String getId() {
      return m_id;
   }

   public Set<String> getIds() {
      return m_ids;
   }

   public Set<String> getIps() {
      return m_ips;
   }

   public Map<String, Machine> getMachines() {
      return m_machines;
   }

   public String getName() {
      return m_name;
   }

   public Set<String> getOps() {
      return m_ops;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(StorageReport other) {
      assertAttributeEquals(other, ENTITY_STORAGE_REPORT, ATTR_ID, m_id, other.getId());

      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public boolean removeMachine(String id) {
      if (m_machines.containsKey(id)) {
         m_machines.remove(id);
         return true;
      }

      return false;
   }

   public StorageReport setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public StorageReport setId(String id) {
      m_id = id;
      return this;
   }

   public StorageReport setName(String name) {
      m_name = name;
      return this;
   }

   public StorageReport setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

   public StorageReport setType(String type) {
      m_type = type;
      return this;
   }

}
