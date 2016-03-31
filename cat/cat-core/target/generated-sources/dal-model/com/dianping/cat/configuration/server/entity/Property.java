package com.dianping.cat.configuration.server.entity;

import static com.dianping.cat.configuration.server.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.server.Constants.ENTITY_PROPERTY;

import com.dianping.cat.configuration.server.BaseEntity;
import com.dianping.cat.configuration.server.IVisitor;

public class Property extends BaseEntity<Property> {
   private String m_name;

   private String m_value;

   public Property() {
   }

   public Property(String name) {
      m_name = name;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitProperty(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Property) {
         Property _o = (Property) obj;
         String name = _o.getName();

         return m_name == name || m_name != null && m_name.equals(name);
      }

      return false;
   }

   public String getName() {
      return m_name;
   }

   public String getValue() {
      return m_value;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Property other) {
      assertAttributeEquals(other, ENTITY_PROPERTY, ATTR_NAME, m_name, other.getName());

      if (other.getValue() != null) {
         m_value = other.getValue();
      }
   }

   public Property setName(String name) {
      m_name = name;
      return this;
   }

   public Property setValue(String value) {
      m_value = value;
      return this;
   }

}
