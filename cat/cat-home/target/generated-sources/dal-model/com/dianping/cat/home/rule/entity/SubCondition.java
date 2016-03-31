package com.dianping.cat.home.rule.entity;

import com.dianping.cat.home.rule.BaseEntity;
import com.dianping.cat.home.rule.IVisitor;

public class SubCondition extends BaseEntity<SubCondition> {
   private String m_type;

   private String m_text;

   public SubCondition() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSubCondition(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof SubCondition) {
         SubCondition _o = (SubCondition) obj;
         String type = _o.getType();
         String text = _o.getText();
         boolean result = true;

         result &= (m_type == type || m_type != null && m_type.equals(type));
         result &= (m_text == text || m_text != null && m_text.equals(text));

         return result;
      }

      return false;
   }

   public String getText() {
      return m_text;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
      hash = hash * 31 + (m_text == null ? 0 : m_text.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(SubCondition other) {
      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getText() != null) {
         m_text = other.getText();
      }
   }

   public SubCondition setText(String text) {
      m_text = text;
      return this;
   }

   public SubCondition setType(String type) {
      m_type = type;
      return this;
   }

}
