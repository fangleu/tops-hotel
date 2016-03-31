package com.dianping.cat.home.storage.alert.entity;

import static com.dianping.cat.home.storage.alert.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_STORAGE_ALERT_INFO;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.storage.alert.BaseEntity;
import com.dianping.cat.home.storage.alert.IVisitor;

public class StorageAlertInfo extends BaseEntity<StorageAlertInfo> {
   private String m_id;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private Map<String, Storage> m_storages = new LinkedHashMap<String, Storage>();

   public StorageAlertInfo() {
   }

   public StorageAlertInfo(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitStorageAlertInfo(this);
   }

   public StorageAlertInfo addStorage(Storage storage) {
      m_storages.put(storage.getId(), storage);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof StorageAlertInfo) {
         StorageAlertInfo _o = (StorageAlertInfo) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Storage findStorage(String id) {
      return m_storages.get(id);
   }

   public Storage findOrCreateStorage(String id) {
      Storage storage = m_storages.get(id);

      if (storage == null) {
         synchronized (m_storages) {
            storage = m_storages.get(id);

            if (storage == null) {
               storage = new Storage(id);
               m_storages.put(id, storage);
            }
         }
      }

      return storage;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public String getId() {
      return m_id;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   public Map<String, Storage> getStorages() {
      return m_storages;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(StorageAlertInfo other) {
      assertAttributeEquals(other, ENTITY_STORAGE_ALERT_INFO, ATTR_ID, m_id, other.getId());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public boolean removeStorage(String id) {
      if (m_storages.containsKey(id)) {
         m_storages.remove(id);
         return true;
      }

      return false;
   }

   public StorageAlertInfo setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public StorageAlertInfo setId(String id) {
      m_id = id;
      return this;
   }

   public StorageAlertInfo setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
