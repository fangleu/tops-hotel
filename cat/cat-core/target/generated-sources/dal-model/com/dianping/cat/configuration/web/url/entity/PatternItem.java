package com.dianping.cat.configuration.web.url.entity;

import static com.dianping.cat.configuration.web.url.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.web.url.Constants.ENTITY_PATTERN_ITEM;

import com.dianping.cat.configuration.web.url.BaseEntity;
import com.dianping.cat.configuration.web.url.IVisitor;

public class PatternItem extends BaseEntity<PatternItem> {
   private String m_group;

   private String m_name;

   private String m_pattern;

   private String m_domain;

   private int m_id;

   public PatternItem() {
   }

   public PatternItem(String name) {
      m_name = name;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitPatternItem(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof PatternItem) {
         PatternItem _o = (PatternItem) obj;
         String name = _o.getName();

         return m_name == name || m_name != null && m_name.equals(name);
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getGroup() {
      return m_group;
   }

   public int getId() {
      return m_id;
   }

   public String getName() {
      return m_name;
   }

   public String getPattern() {
      return m_pattern;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(PatternItem other) {
      assertAttributeEquals(other, ENTITY_PATTERN_ITEM, ATTR_NAME, m_name, other.getName());

      if (other.getGroup() != null) {
         m_group = other.getGroup();
      }

      if (other.getPattern() != null) {
         m_pattern = other.getPattern();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      m_id = other.getId();
   }

   public PatternItem setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public PatternItem setGroup(String group) {
      m_group = group;
      return this;
   }

   public PatternItem setId(int id) {
      m_id = id;
      return this;
   }

   public PatternItem setName(String name) {
      m_name = name;
      return this;
   }

   public PatternItem setPattern(String pattern) {
      m_pattern = pattern;
      return this;
   }

}
