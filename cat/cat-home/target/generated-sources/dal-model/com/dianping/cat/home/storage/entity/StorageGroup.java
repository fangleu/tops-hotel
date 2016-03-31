package com.dianping.cat.home.storage.entity;

import static com.dianping.cat.home.storage.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE_GROUP;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.storage.BaseEntity;
import com.dianping.cat.home.storage.IVisitor;

public class StorageGroup extends BaseEntity<StorageGroup> {
   private String m_id;

   private Link m_link;

   private Map<String, Storage> m_storages = new LinkedHashMap<String, Storage>();

   public StorageGroup() {
   }

   public StorageGroup(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitStorageGroup(this);
   }

   public StorageGroup addStorage(Storage storage) {
      m_storages.put(storage.getId(), storage);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof StorageGroup) {
         StorageGroup _o = (StorageGroup) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Storage findStorage(String id) {
      return m_storages.get(id);
   }

   public String getId() {
      return m_id;
   }

   public Link getLink() {
      return m_link;
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
   public void mergeAttributes(StorageGroup other) {
      assertAttributeEquals(other, ENTITY_STORAGE_GROUP, ATTR_ID, m_id, other.getId());

   }

   public boolean removeStorage(String id) {
      if (m_storages.containsKey(id)) {
         m_storages.remove(id);
         return true;
      }

      return false;
   }

   public StorageGroup setId(String id) {
      m_id = id;
      return this;
   }

   public StorageGroup setLink(Link link) {
      m_link = link;
      return this;
   }

}
