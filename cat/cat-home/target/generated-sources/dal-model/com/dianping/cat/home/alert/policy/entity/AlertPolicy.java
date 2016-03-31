package com.dianping.cat.home.alert.policy.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.alert.policy.BaseEntity;
import com.dianping.cat.home.alert.policy.IVisitor;

public class AlertPolicy extends BaseEntity<AlertPolicy> {
   private Map<String, Type> m_types = new LinkedHashMap<String, Type>();

   public AlertPolicy() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAlertPolicy(this);
   }

   public AlertPolicy addType(Type type) {
      m_types.put(type.getId(), type);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AlertPolicy) {
         AlertPolicy _o = (AlertPolicy) obj;
         Map<String, Type> types = _o.getTypes();
         boolean result = true;

         result &= (m_types == types || m_types != null && m_types.equals(types));

         return result;
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

   public Map<String, Type> getTypes() {
      return m_types;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_types == null ? 0 : m_types.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AlertPolicy other) {
   }

   public boolean removeType(String id) {
      if (m_types.containsKey(id)) {
         m_types.remove(id);
         return true;
      }

      return false;
   }

}
