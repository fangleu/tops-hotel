package com.dianping.cat.configuration.server.entity;

import com.dianping.cat.configuration.server.BaseEntity;
import com.dianping.cat.configuration.server.IVisitor;

public class ConsumerConfig extends BaseEntity<ConsumerConfig> {
   private LongConfig m_longConfig;

   public ConsumerConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitConsumer(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ConsumerConfig) {
         ConsumerConfig _o = (ConsumerConfig) obj;
         LongConfig longConfig = _o.getLongConfig();
         boolean result = true;

         result &= (m_longConfig == longConfig || m_longConfig != null && m_longConfig.equals(longConfig));

         return result;
      }

      return false;
   }

   public LongConfig getLongConfig() {
      return m_longConfig;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_longConfig == null ? 0 : m_longConfig.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ConsumerConfig other) {
   }

   public ConsumerConfig setLongConfig(LongConfig longConfig) {
      m_longConfig = longConfig;
      return this;
   }

}
