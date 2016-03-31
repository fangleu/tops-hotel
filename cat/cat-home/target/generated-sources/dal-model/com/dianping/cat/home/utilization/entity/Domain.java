package com.dianping.cat.home.utilization.entity;

import static com.dianping.cat.home.utilization.Constants.ATTR_ID;
import static com.dianping.cat.home.utilization.Constants.ENTITY_DOMAIN;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.utilization.BaseEntity;
import com.dianping.cat.home.utilization.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private int m_machineNumber;

   private String m_cmdbId;

   private Map<String, MachineState> m_machineStates = new LinkedHashMap<String, MachineState>();

   private Map<String, ApplicationState> m_applicationStates = new LinkedHashMap<String, ApplicationState>();

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addApplicationState(ApplicationState applicationState) {
      m_applicationStates.put(applicationState.getId(), applicationState);
      return this;
   }

   public Domain addMachineState(MachineState machineState) {
      m_machineStates.put(machineState.getId(), machineState);
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

   public ApplicationState findApplicationState(String id) {
      return m_applicationStates.get(id);
   }

   public MachineState findMachineState(String id) {
      return m_machineStates.get(id);
   }

   public ApplicationState findOrCreateApplicationState(String id) {
      ApplicationState applicationState = m_applicationStates.get(id);

      if (applicationState == null) {
         synchronized (m_applicationStates) {
            applicationState = m_applicationStates.get(id);

            if (applicationState == null) {
               applicationState = new ApplicationState(id);
               m_applicationStates.put(id, applicationState);
            }
         }
      }

      return applicationState;
   }

   public MachineState findOrCreateMachineState(String id) {
      MachineState machineState = m_machineStates.get(id);

      if (machineState == null) {
         synchronized (m_machineStates) {
            machineState = m_machineStates.get(id);

            if (machineState == null) {
               machineState = new MachineState(id);
               m_machineStates.put(id, machineState);
            }
         }
      }

      return machineState;
   }

   public Map<String, ApplicationState> getApplicationStates() {
      return m_applicationStates;
   }

   public String getCmdbId() {
      return m_cmdbId;
   }

   public String getId() {
      return m_id;
   }

   public int getMachineNumber() {
      return m_machineNumber;
   }

   public Map<String, MachineState> getMachineStates() {
      return m_machineStates;
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

      m_machineNumber = other.getMachineNumber();

      if (other.getCmdbId() != null) {
         m_cmdbId = other.getCmdbId();
      }
   }

   public boolean removeApplicationState(String id) {
      if (m_applicationStates.containsKey(id)) {
         m_applicationStates.remove(id);
         return true;
      }

      return false;
   }

   public boolean removeMachineState(String id) {
      if (m_machineStates.containsKey(id)) {
         m_machineStates.remove(id);
         return true;
      }

      return false;
   }

   public Domain setCmdbId(String cmdbId) {
      m_cmdbId = cmdbId;
      return this;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

   public Domain setMachineNumber(int machineNumber) {
      m_machineNumber = machineNumber;
      return this;
   }

}
