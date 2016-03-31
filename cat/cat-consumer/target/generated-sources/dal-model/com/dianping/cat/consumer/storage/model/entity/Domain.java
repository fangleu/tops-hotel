package com.dianping.cat.consumer.storage.model.entity;

import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_DOMAIN;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.storage.model.BaseEntity;
import com.dianping.cat.consumer.storage.model.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private Map<String, Operation> m_operations = new LinkedHashMap<String, Operation>();

   private Map<String, Sql> m_sqls = new LinkedHashMap<String, Sql>();

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addOperation(Operation operation) {
      m_operations.put(operation.getId(), operation);
      return this;
   }

   public Domain addSql(Sql sql) {
      m_sqls.put(sql.getId(), sql);
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

   public Operation findOperation(String id) {
      return m_operations.get(id);
   }

   public Sql findSql(String id) {
      return m_sqls.get(id);
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

   public Sql findOrCreateSql(String id) {
      Sql sql = m_sqls.get(id);

      if (sql == null) {
         synchronized (m_sqls) {
            sql = m_sqls.get(id);

            if (sql == null) {
               sql = new Sql(id);
               m_sqls.put(id, sql);
            }
         }
      }

      return sql;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Operation> getOperations() {
      return m_operations;
   }

   public Map<String, Sql> getSqls() {
      return m_sqls;
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

   }

   public boolean removeOperation(String id) {
      if (m_operations.containsKey(id)) {
         m_operations.remove(id);
         return true;
      }

      return false;
   }

   public boolean removeSql(String id) {
      if (m_sqls.containsKey(id)) {
         m_sqls.remove(id);
         return true;
      }

      return false;
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

}
