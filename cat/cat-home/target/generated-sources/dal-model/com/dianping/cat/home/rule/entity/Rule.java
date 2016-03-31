package com.dianping.cat.home.rule.entity;

import static com.dianping.cat.home.rule.Constants.ATTR_ID;
import static com.dianping.cat.home.rule.Constants.ENTITY_RULE;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.rule.BaseEntity;
import com.dianping.cat.home.rule.IVisitor;

public class Rule extends BaseEntity<Rule> {
   private String m_id;

   private List<MetricItem> m_metricItems = new ArrayList<MetricItem>();

   private List<Config> m_configs = new ArrayList<Config>();

   public Rule() {
   }

   public Rule(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitRule(this);
   }

   public Rule addConfig(Config config) {
      m_configs.add(config);
      return this;
   }

   public Rule addMetricItem(MetricItem metricItem) {
      m_metricItems.add(metricItem);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Rule) {
         Rule _o = (Rule) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public List<Config> getConfigs() {
      return m_configs;
   }

   public String getId() {
      return m_id;
   }

   public List<MetricItem> getMetricItems() {
      return m_metricItems;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Rule other) {
      assertAttributeEquals(other, ENTITY_RULE, ATTR_ID, m_id, other.getId());

   }

   public Rule setId(String id) {
      m_id = id;
      return this;
   }

}
