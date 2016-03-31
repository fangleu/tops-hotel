package com.dianping.cat.home.sender.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.sender.BaseEntity;
import com.dianping.cat.home.sender.IVisitor;

public class SenderConfig extends BaseEntity<SenderConfig> {
   private Map<String, Sender> m_senders = new LinkedHashMap<String, Sender>();

   public SenderConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSenderConfig(this);
   }

   public SenderConfig addSender(Sender sender) {
      m_senders.put(sender.getId(), sender);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof SenderConfig) {
         SenderConfig _o = (SenderConfig) obj;
         Map<String, Sender> senders = _o.getSenders();
         boolean result = true;

         result &= (m_senders == senders || m_senders != null && m_senders.equals(senders));

         return result;
      }

      return false;
   }

   public Sender findSender(String id) {
      return m_senders.get(id);
   }

   public Map<String, Sender> getSenders() {
      return m_senders;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_senders == null ? 0 : m_senders.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(SenderConfig other) {
   }

   public boolean removeSender(String id) {
      if (m_senders.containsKey(id)) {
         m_senders.remove(id);
         return true;
      }

      return false;
   }

}
