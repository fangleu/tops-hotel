package com.dianping.cat.home.alert.config.entity;

import static com.dianping.cat.home.alert.config.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.config.Constants.ENTITY_RECEIVER;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.alert.config.BaseEntity;
import com.dianping.cat.home.alert.config.IVisitor;

public class Receiver extends BaseEntity<Receiver> {
   private String m_id;

   private Boolean m_enable;

   private List<String> m_emails = new ArrayList<String>();

   private List<String> m_phones = new ArrayList<String>();

   private List<String> m_weixins = new ArrayList<String>();

   public Receiver() {
   }

   public Receiver(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitReceiver(this);
   }

   public Receiver addEmail(String email) {
      m_emails.add(email);
      return this;
   }

   public Receiver addPhone(String phone) {
      m_phones.add(phone);
      return this;
   }

   public Receiver addWeixin(String weixin) {
      m_weixins.add(weixin);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Receiver) {
         Receiver _o = (Receiver) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public List<String> getEmails() {
      return m_emails;
   }

   public Boolean getEnable() {
      return m_enable;
   }

   public String getId() {
      return m_id;
   }

   public List<String> getPhones() {
      return m_phones;
   }

   public List<String> getWeixins() {
      return m_weixins;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isEnable() {
      return m_enable != null && m_enable.booleanValue();
   }

   @Override
   public void mergeAttributes(Receiver other) {
      assertAttributeEquals(other, ENTITY_RECEIVER, ATTR_ID, m_id, other.getId());

      if (other.getEnable() != null) {
         m_enable = other.getEnable();
      }
   }

   public Receiver setEnable(Boolean enable) {
      m_enable = enable;
      return this;
   }

   public Receiver setId(String id) {
      m_id = id;
      return this;
   }

}
