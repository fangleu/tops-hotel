package com.dianping.cat.home.rule.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.rule.BaseEntity;
import com.dianping.cat.home.rule.IVisitor;

public class MonitorRules extends BaseEntity<MonitorRules> {
   private Map<String, Rule> m_rules = new LinkedHashMap<String, Rule>();

   public MonitorRules() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMonitorRules(this);
   }

   public MonitorRules addRule(Rule rule) {
      m_rules.put(rule.getId(), rule);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MonitorRules) {
         MonitorRules _o = (MonitorRules) obj;
         Map<String, Rule> rules = _o.getRules();
         boolean result = true;

         result &= (m_rules == rules || m_rules != null && m_rules.equals(rules));

         return result;
      }

      return false;
   }

   public Rule findRule(String id) {
      return m_rules.get(id);
   }

   public Rule findOrCreateRule(String id) {
      Rule rule = m_rules.get(id);

      if (rule == null) {
         synchronized (m_rules) {
            rule = m_rules.get(id);

            if (rule == null) {
               rule = new Rule(id);
               m_rules.put(id, rule);
            }
         }
      }

      return rule;
   }

   public Map<String, Rule> getRules() {
      return m_rules;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_rules == null ? 0 : m_rules.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(MonitorRules other) {
   }

   public boolean removeRule(String id) {
      if (m_rules.containsKey(id)) {
         m_rules.remove(id);
         return true;
      }

      return false;
   }

}
