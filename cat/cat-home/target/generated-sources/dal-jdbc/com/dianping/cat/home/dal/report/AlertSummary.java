package com.dianping.cat.home.dal.report;

import static com.dianping.cat.home.dal.report.AlertSummaryEntity.ALERT_TIME;
import static com.dianping.cat.home.dal.report.AlertSummaryEntity.CONTENT;
import static com.dianping.cat.home.dal.report.AlertSummaryEntity.CREATION_DATE;
import static com.dianping.cat.home.dal.report.AlertSummaryEntity.DOMAIN;
import static com.dianping.cat.home.dal.report.AlertSummaryEntity.ID;
import static com.dianping.cat.home.dal.report.AlertSummaryEntity.KEY_ID;

import org.unidal.dal.jdbc.DataObject;

public class AlertSummary extends DataObject {
   private int m_id;

   private String m_domain;

   private java.util.Date m_alertTime;

   private String m_content;

   private java.util.Date m_creationDate;

   private int m_keyId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public java.util.Date getAlertTime() {
      return m_alertTime;
   }

   public String getContent() {
      return m_content;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public String getDomain() {
      return m_domain;
   }

   public int getId() {
      return m_id;
   }

   public int getKeyId() {
      return m_keyId;
   }

   public AlertSummary setAlertTime(java.util.Date alertTime) {
      setFieldUsed(ALERT_TIME, true);
      m_alertTime = alertTime;
      return this;
   }

   public AlertSummary setContent(String content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public AlertSummary setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public AlertSummary setDomain(String domain) {
      setFieldUsed(DOMAIN, true);
      m_domain = domain;
      return this;
   }

   public AlertSummary setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public AlertSummary setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("AlertSummary[");
      sb.append("alert-time: ").append(m_alertTime);
      sb.append(", content: ").append(m_content);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", domain: ").append(m_domain);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append("]");
      return sb.toString();
   }

}
