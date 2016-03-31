package com.dianping.cat.home.app.entity;

import static com.dianping.cat.home.app.Constants.ATTR_URL;
import static com.dianping.cat.home.app.Constants.ENTITY_TRANSACTION;

import com.dianping.cat.home.app.BaseEntity;
import com.dianping.cat.home.app.IVisitor;

public class Transaction extends BaseEntity<Transaction> {
   private String m_url;

   private long m_count;

   private double m_avg;

   public Transaction() {
   }

   public Transaction(String url) {
      m_url = url;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTransaction(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Transaction) {
         Transaction _o = (Transaction) obj;
         String url = _o.getUrl();

         return m_url == url || m_url != null && m_url.equals(url);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getCount() {
      return m_count;
   }

   public String getUrl() {
      return m_url;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_url == null ? 0 : m_url.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Transaction other) {
      assertAttributeEquals(other, ENTITY_TRANSACTION, ATTR_URL, m_url, other.getUrl());

      m_count = other.getCount();

      m_avg = other.getAvg();
   }

   public Transaction setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Transaction setCount(long count) {
      m_count = count;
      return this;
   }

   public Transaction setUrl(String url) {
      m_url = url;
      return this;
   }

}
