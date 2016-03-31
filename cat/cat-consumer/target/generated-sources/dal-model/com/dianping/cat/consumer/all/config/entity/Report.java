package com.dianping.cat.consumer.all.config.entity;

import static com.dianping.cat.consumer.all.config.Constants.ATTR_ID;
import static com.dianping.cat.consumer.all.config.Constants.ENTITY_REPORT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.all.config.BaseEntity;
import com.dianping.cat.consumer.all.config.IVisitor;

public class Report extends BaseEntity<Report> {
   private String m_id;

   private Map<String, Type> m_types = new LinkedHashMap<String, Type>();

   public Report() {
   }

   public Report(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitReport(this);
   }

   public Report addType(Type type) {
      m_types.put(type.getId(), type);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Report) {
         Report _o = (Report) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Type findType(String id) {
      return m_types.get(id);
   }

   public Type findOrCreateType(String id) {
      Type type = m_types.get(id);

      if (type == null) {
         synchronized (m_types) {
            type = m_types.get(id);

            if (type == null) {
               type = new Type(id);
               m_types.put(id, type);
            }
         }
      }

      return type;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Type> getTypes() {
      return m_types;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Report other) {
      assertAttributeEquals(other, ENTITY_REPORT, ATTR_ID, m_id, other.getId());

   }

   public boolean removeType(String id) {
      if (m_types.containsKey(id)) {
         m_types.remove(id);
         return true;
      }

      return false;
   }

   public Report setId(String id) {
      m_id = id;
      return this;
   }

}
