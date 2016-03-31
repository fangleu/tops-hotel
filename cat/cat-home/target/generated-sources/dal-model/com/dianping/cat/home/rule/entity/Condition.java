package com.dianping.cat.home.rule.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.rule.BaseEntity;
import com.dianping.cat.home.rule.IVisitor;

public class Condition extends BaseEntity<Condition> {
   private Integer m_minute = 3;

   private List<SubCondition> m_subConditions = new ArrayList<SubCondition>();

   private String m_title;

   private String m_alertType = "error";

   public Condition() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCondition(this);
   }

   public Condition addSubCondition(SubCondition subCondition) {
      m_subConditions.add(subCondition);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Condition) {
         Condition _o = (Condition) obj;
         Integer minute = _o.getMinute();
         List<SubCondition> subConditions = _o.getSubConditions();
         String title = _o.getTitle();
         String alertType = _o.getAlertType();
         boolean result = true;

         result &= (m_minute == minute || m_minute != null && m_minute.equals(minute));
         result &= (m_subConditions == subConditions || m_subConditions != null && m_subConditions.equals(subConditions));
         result &= (m_title == title || m_title != null && m_title.equals(title));
         result &= (m_alertType == alertType || m_alertType != null && m_alertType.equals(alertType));

         return result;
      }

      return false;
   }

   public String getAlertType() {
      return m_alertType;
   }

   public Integer getMinute() {
      return m_minute;
   }

   public List<SubCondition> getSubConditions() {
      return m_subConditions;
   }

   public String getTitle() {
      return m_title;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_minute == null ? 0 : m_minute.hashCode());
      hash = hash * 31 + (m_subConditions == null ? 0 : m_subConditions.hashCode());
      hash = hash * 31 + (m_title == null ? 0 : m_title.hashCode());
      hash = hash * 31 + (m_alertType == null ? 0 : m_alertType.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Condition other) {
      if (other.getMinute() != null) {
         m_minute = other.getMinute();
      }

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      if (other.getAlertType() != null) {
         m_alertType = other.getAlertType();
      }
   }

   public Condition setAlertType(String alertType) {
      m_alertType = alertType;
      return this;
   }

   public Condition setMinute(Integer minute) {
      m_minute = minute;
      return this;
   }

   public Condition setTitle(String title) {
      m_title = title;
      return this;
   }

}
