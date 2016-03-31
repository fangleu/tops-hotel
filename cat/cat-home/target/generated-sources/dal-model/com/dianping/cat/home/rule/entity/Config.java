package com.dianping.cat.home.rule.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.rule.BaseEntity;
import com.dianping.cat.home.rule.IVisitor;

public class Config extends BaseEntity<Config> {
   private String m_starttime;

   private String m_endtime;

   private List<Condition> m_conditions = new ArrayList<Condition>();

   public Config() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitConfig(this);
   }

   public Config addCondition(Condition condition) {
      m_conditions.add(condition);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Config) {
         Config _o = (Config) obj;
         String starttime = _o.getStarttime();
         String endtime = _o.getEndtime();
         List<Condition> conditions = _o.getConditions();
         boolean result = true;

         result &= (m_starttime == starttime || m_starttime != null && m_starttime.equals(starttime));
         result &= (m_endtime == endtime || m_endtime != null && m_endtime.equals(endtime));
         result &= (m_conditions == conditions || m_conditions != null && m_conditions.equals(conditions));

         return result;
      }

      return false;
   }

   public List<Condition> getConditions() {
      return m_conditions;
   }

   public String getEndtime() {
      return m_endtime;
   }

   public String getStarttime() {
      return m_starttime;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_starttime == null ? 0 : m_starttime.hashCode());
      hash = hash * 31 + (m_endtime == null ? 0 : m_endtime.hashCode());
      hash = hash * 31 + (m_conditions == null ? 0 : m_conditions.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Config other) {
      if (other.getStarttime() != null) {
         m_starttime = other.getStarttime();
      }

      if (other.getEndtime() != null) {
         m_endtime = other.getEndtime();
      }
   }

   public Config setEndtime(String endtime) {
      m_endtime = endtime;
      return this;
   }

   public Config setStarttime(String starttime) {
      m_starttime = starttime;
      return this;
   }

}
