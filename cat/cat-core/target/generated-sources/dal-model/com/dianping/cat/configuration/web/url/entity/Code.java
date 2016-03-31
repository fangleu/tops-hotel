package com.dianping.cat.configuration.web.url.entity;

import static com.dianping.cat.configuration.web.url.Constants.ATTR_ID;
import static com.dianping.cat.configuration.web.url.Constants.ENTITY_CODE;

import com.dianping.cat.configuration.web.url.BaseEntity;
import com.dianping.cat.configuration.web.url.IVisitor;

public class Code extends BaseEntity<Code> {
   private Integer m_id;

   private String m_name;

   private Integer m_status;

   public Code() {
   }

   public Code(Integer id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCode(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Code) {
         Code _o = (Code) obj;
         Integer id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Integer getId() {
      return m_id;
   }

   public String getName() {
      return m_name;
   }

   public Integer getStatus() {
      return m_status;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Code other) {
      assertAttributeEquals(other, ENTITY_CODE, ATTR_ID, m_id, other.getId());

      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getStatus() != null) {
         m_status = other.getStatus();
      }
   }

   public Code setId(Integer id) {
      m_id = id;
      return this;
   }

   public Code setName(String name) {
      m_name = name;
      return this;
   }

   public Code setStatus(Integer status) {
      m_status = status;
      return this;
   }

}
