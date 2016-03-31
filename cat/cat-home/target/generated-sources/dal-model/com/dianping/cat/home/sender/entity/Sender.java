package com.dianping.cat.home.sender.entity;

import static com.dianping.cat.home.sender.Constants.ATTR_ID;
import static com.dianping.cat.home.sender.Constants.ENTITY_SENDER;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.sender.BaseEntity;
import com.dianping.cat.home.sender.IVisitor;

public class Sender extends BaseEntity<Sender> {
   private String m_id;

   private String m_url;

   private String m_type;

   private String m_successCode;

   private boolean m_batchSend;

   private List<Par> m_pars = new ArrayList<Par>();

   public Sender() {
   }

   public Sender(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSender(this);
   }

   public Sender addPar(Par par) {
      m_pars.add(par);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Sender) {
         Sender _o = (Sender) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public boolean getBatchSend() {
      return m_batchSend;
   }

   public String getId() {
      return m_id;
   }

   public List<Par> getPars() {
      return m_pars;
   }

   public String getSuccessCode() {
      return m_successCode;
   }

   public String getType() {
      return m_type;
   }

   public String getUrl() {
      return m_url;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isBatchSend() {
      return m_batchSend;
   }

   @Override
   public void mergeAttributes(Sender other) {
      assertAttributeEquals(other, ENTITY_SENDER, ATTR_ID, m_id, other.getId());

      if (other.getUrl() != null) {
         m_url = other.getUrl();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getSuccessCode() != null) {
         m_successCode = other.getSuccessCode();
      }

      m_batchSend = other.getBatchSend();
   }

   public Sender setBatchSend(boolean batchSend) {
      m_batchSend = batchSend;
      return this;
   }

   public Sender setId(String id) {
      m_id = id;
      return this;
   }

   public Sender setSuccessCode(String successCode) {
      m_successCode = successCode;
      return this;
   }

   public Sender setType(String type) {
      m_type = type;
      return this;
   }

   public Sender setUrl(String url) {
      m_url = url;
      return this;
   }

}
