package com.dianping.cat.home.bug.entity;

import static com.dianping.cat.home.bug.Constants.ATTR_ID;
import static com.dianping.cat.home.bug.Constants.ENTITY_EXCEPTION_ITEM;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.bug.BaseEntity;
import com.dianping.cat.home.bug.IVisitor;

public class ExceptionItem extends BaseEntity<ExceptionItem> {
   private String m_id;

   private int m_count;

   private List<String> m_messages = new ArrayList<String>();

   public ExceptionItem() {
   }

   public ExceptionItem(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitExceptionItem(this);
   }

   public ExceptionItem addMessage(String message) {
      m_messages.add(message);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ExceptionItem) {
         ExceptionItem _o = (ExceptionItem) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public int getCount() {
      return m_count;
   }

   public String getId() {
      return m_id;
   }

   public List<String> getMessages() {
      return m_messages;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ExceptionItem other) {
      assertAttributeEquals(other, ENTITY_EXCEPTION_ITEM, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();
   }

   public ExceptionItem setCount(int count) {
      m_count = count;
      return this;
   }

   public ExceptionItem setId(String id) {
      m_id = id;
      return this;
   }

}
