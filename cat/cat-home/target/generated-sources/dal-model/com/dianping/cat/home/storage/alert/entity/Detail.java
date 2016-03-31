package com.dianping.cat.home.storage.alert.entity;

import static com.dianping.cat.home.storage.alert.Constants.ATTR_CONTENT;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_DETAIL;

import com.dianping.cat.home.storage.alert.BaseEntity;
import com.dianping.cat.home.storage.alert.IVisitor;

public class Detail extends BaseEntity<Detail> {
   private String m_content;

   private int m_level;

   public Detail() {
   }

   public Detail(String content) {
      m_content = content;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDetail(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Detail) {
         Detail _o = (Detail) obj;
         String content = _o.getContent();

         return m_content == content || m_content != null && m_content.equals(content);
      }

      return false;
   }

   public String getContent() {
      return m_content;
   }

   public int getLevel() {
      return m_level;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_content == null ? 0 : m_content.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Detail other) {
      assertAttributeEquals(other, ENTITY_DETAIL, ATTR_CONTENT, m_content, other.getContent());

      m_level = other.getLevel();
   }

   public Detail setContent(String content) {
      m_content = content;
      return this;
   }

   public Detail setLevel(int level) {
      m_level = level;
      return this;
   }

}
