package com.dianping.cat.consumer.heartbeat.model.entity;

import static com.dianping.cat.consumer.heartbeat.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_EXTENSION;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.heartbeat.model.BaseEntity;
import com.dianping.cat.consumer.heartbeat.model.IVisitor;

public class Extension extends BaseEntity<Extension> {
   private String m_id;

   private Map<String, Detail> m_details = new LinkedHashMap<String, Detail>();

   public Extension() {
   }

   public Extension(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitExtension(this);
   }

   public Extension addDetail(Detail detail) {
      m_details.put(detail.getId(), detail);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Extension) {
         Extension _o = (Extension) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Detail findDetail(String id) {
      return m_details.get(id);
   }

   public Detail findOrCreateDetail(String id) {
      Detail detail = m_details.get(id);

      if (detail == null) {
         synchronized (m_details) {
            detail = m_details.get(id);

            if (detail == null) {
               detail = new Detail(id);
               m_details.put(id, detail);
            }
         }
      }

      return detail;
   }

   public Map<String, Detail> getDetails() {
      return m_details;
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
   public void mergeAttributes(Extension other) {
      assertAttributeEquals(other, ENTITY_EXTENSION, ATTR_ID, m_id, other.getId());

   }

   public boolean removeDetail(String id) {
      if (m_details.containsKey(id)) {
         m_details.remove(id);
         return true;
      }

      return false;
   }

   public Extension setId(String id) {
      m_id = id;
      return this;
   }

}
