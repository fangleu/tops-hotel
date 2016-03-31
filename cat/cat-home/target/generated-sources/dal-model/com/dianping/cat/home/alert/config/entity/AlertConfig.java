package com.dianping.cat.home.alert.config.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.alert.config.BaseEntity;
import com.dianping.cat.home.alert.config.IVisitor;

public class AlertConfig extends BaseEntity<AlertConfig> {
   private Map<String, Receiver> m_receivers = new LinkedHashMap<String, Receiver>();

   private Boolean m_enable;

   public AlertConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAlertConfig(this);
   }

   public AlertConfig addReceiver(Receiver receiver) {
      m_receivers.put(receiver.getId(), receiver);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AlertConfig) {
         AlertConfig _o = (AlertConfig) obj;
         Map<String, Receiver> receivers = _o.getReceivers();
         Boolean enable = _o.getEnable();
         boolean result = true;

         result &= (m_receivers == receivers || m_receivers != null && m_receivers.equals(receivers));
         result &= (m_enable == enable || m_enable != null && m_enable.equals(enable));

         return result;
      }

      return false;
   }

   public Receiver findReceiver(String id) {
      return m_receivers.get(id);
   }

   public Receiver findOrCreateReceiver(String id) {
      Receiver receiver = m_receivers.get(id);

      if (receiver == null) {
         synchronized (m_receivers) {
            receiver = m_receivers.get(id);

            if (receiver == null) {
               receiver = new Receiver(id);
               m_receivers.put(id, receiver);
            }
         }
      }

      return receiver;
   }

   public Boolean getEnable() {
      return m_enable;
   }

   public Map<String, Receiver> getReceivers() {
      return m_receivers;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_receivers == null ? 0 : m_receivers.hashCode());
      hash = hash * 31 + (m_enable == null ? 0 : m_enable.hashCode());

      return hash;
   }

   public boolean isEnable() {
      return m_enable != null && m_enable.booleanValue();
   }

   @Override
   public void mergeAttributes(AlertConfig other) {
      if (other.getEnable() != null) {
         m_enable = other.getEnable();
      }
   }

   public boolean removeReceiver(String id) {
      if (m_receivers.containsKey(id)) {
         m_receivers.remove(id);
         return true;
      }

      return false;
   }

   public AlertConfig setEnable(Boolean enable) {
      m_enable = enable;
      return this;
   }

}
