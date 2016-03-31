package com.dianping.cat.configuration.web.js.entity;

import static com.dianping.cat.configuration.web.js.Constants.ATTR_PATTERN;
import static com.dianping.cat.configuration.web.js.Constants.ENTITY_AGGREGATION_RULE;

import com.dianping.cat.configuration.web.js.BaseEntity;
import com.dianping.cat.configuration.web.js.IVisitor;

public class AggregationRule extends BaseEntity<AggregationRule> {
   private int m_type;

   private String m_domain;

   private String m_pattern;

   private String m_sample;

   private String m_displayName;

   private java.util.Date m_creationDate;

   private int m_warn = 200;

   private String m_mails;

   public AggregationRule() {
   }

   public AggregationRule(String pattern) {
      m_pattern = pattern;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAggregationRule(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AggregationRule) {
         AggregationRule _o = (AggregationRule) obj;
         String pattern = _o.getPattern();

         return m_pattern == pattern || m_pattern != null && m_pattern.equals(pattern);
      }

      return false;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public String getDisplayName() {
      return m_displayName;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getMails() {
      return m_mails;
   }

   public String getPattern() {
      return m_pattern;
   }

   public String getSample() {
      return m_sample;
   }

   public int getType() {
      return m_type;
   }

   public int getWarn() {
      return m_warn;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_pattern == null ? 0 : m_pattern.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AggregationRule other) {
      assertAttributeEquals(other, ENTITY_AGGREGATION_RULE, ATTR_PATTERN, m_pattern, other.getPattern());

      m_type = other.getType();

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getSample() != null) {
         m_sample = other.getSample();
      }

      if (other.getDisplayName() != null) {
         m_displayName = other.getDisplayName();
      }

      if (other.getCreationDate() != null) {
         m_creationDate = other.getCreationDate();
      }

      m_warn = other.getWarn();

      if (other.getMails() != null) {
         m_mails = other.getMails();
      }
   }

   public AggregationRule setCreationDate(java.util.Date creationDate) {
      m_creationDate = creationDate;
      return this;
   }

   public AggregationRule setDisplayName(String displayName) {
      m_displayName = displayName;
      return this;
   }

   public AggregationRule setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public AggregationRule setMails(String mails) {
      m_mails = mails;
      return this;
   }

   public AggregationRule setPattern(String pattern) {
      m_pattern = pattern;
      return this;
   }

   public AggregationRule setSample(String sample) {
      m_sample = sample;
      return this;
   }

   public AggregationRule setType(int type) {
      m_type = type;
      return this;
   }

   public AggregationRule setWarn(int warn) {
      m_warn = warn;
      return this;
   }

}
