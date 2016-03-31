package com.dianping.cat.consumer.storage.model.entity;

import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_SQL;

import com.dianping.cat.consumer.storage.model.BaseEntity;
import com.dianping.cat.consumer.storage.model.IVisitor;

public class Sql extends BaseEntity<Sql> {
   private String m_id;

   private String m_statement;

   private int m_count;

   public Sql() {
   }

   public Sql(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSql(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Sql) {
         Sql _o = (Sql) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public int getCount() {
      return m_count;
   }

   public String getId() {
      return m_id;
   }

   public String getStatement() {
      return m_statement;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public Sql incCount() {
      m_count++;
      return this;
   }

   public Sql incCount(int count) {
      m_count += count;
      return this;
   }

   @Override
   public void mergeAttributes(Sql other) {
      assertAttributeEquals(other, ENTITY_SQL, ATTR_ID, m_id, other.getId());

      if (other.getStatement() != null) {
         m_statement = other.getStatement();
      }

      m_count = other.getCount();
   }

   public Sql setCount(int count) {
      m_count = count;
      return this;
   }

   public Sql setId(String id) {
      m_id = id;
      return this;
   }

   public Sql setStatement(String statement) {
      m_statement = statement;
      return this;
   }

}
