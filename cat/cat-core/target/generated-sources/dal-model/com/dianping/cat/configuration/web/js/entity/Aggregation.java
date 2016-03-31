package com.dianping.cat.configuration.web.js.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.web.js.BaseEntity;
import com.dianping.cat.configuration.web.js.IVisitor;

public class Aggregation extends BaseEntity<Aggregation> {
   private Map<String, AggregationRule> m_aggregationRules = new LinkedHashMap<String, AggregationRule>();

   public Aggregation() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAggregation(this);
   }

   public Aggregation addAggregationRule(AggregationRule aggregationRule) {
      m_aggregationRules.put(aggregationRule.getPattern(), aggregationRule);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Aggregation) {
         Aggregation _o = (Aggregation) obj;
         Map<String, AggregationRule> aggregationRules = _o.getAggregationRules();
         boolean result = true;

         result &= (m_aggregationRules == aggregationRules || m_aggregationRules != null && m_aggregationRules.equals(aggregationRules));

         return result;
      }

      return false;
   }

   public AggregationRule findAggregationRule(String pattern) {
      return m_aggregationRules.get(pattern);
   }

   public AggregationRule findOrCreateAggregationRule(String pattern) {
      AggregationRule aggregationRule = m_aggregationRules.get(pattern);

      if (aggregationRule == null) {
         synchronized (m_aggregationRules) {
            aggregationRule = m_aggregationRules.get(pattern);

            if (aggregationRule == null) {
               aggregationRule = new AggregationRule(pattern);
               m_aggregationRules.put(pattern, aggregationRule);
            }
         }
      }

      return aggregationRule;
   }

   public Map<String, AggregationRule> getAggregationRules() {
      return m_aggregationRules;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_aggregationRules == null ? 0 : m_aggregationRules.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Aggregation other) {
   }

   public boolean removeAggregationRule(String pattern) {
      if (m_aggregationRules.containsKey(pattern)) {
         m_aggregationRules.remove(pattern);
         return true;
      }

      return false;
   }

}
