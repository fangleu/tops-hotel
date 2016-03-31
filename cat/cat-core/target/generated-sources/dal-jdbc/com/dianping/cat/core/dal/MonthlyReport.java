package com.dianping.cat.core.dal;

import static com.dianping.cat.core.dal.MonthlyReportEntity.CREATION_DATE;
import static com.dianping.cat.core.dal.MonthlyReportEntity.DOMAIN;
import static com.dianping.cat.core.dal.MonthlyReportEntity.ID;
import static com.dianping.cat.core.dal.MonthlyReportEntity.IP;
import static com.dianping.cat.core.dal.MonthlyReportEntity.KEY_ID;
import static com.dianping.cat.core.dal.MonthlyReportEntity.NAME;
import static com.dianping.cat.core.dal.MonthlyReportEntity.PERIOD;
import static com.dianping.cat.core.dal.MonthlyReportEntity.TYPE;

import org.unidal.dal.jdbc.DataObject;

public class MonthlyReport extends DataObject {
   private int m_id;

   private String m_name;

   private String m_ip;

   private String m_domain;

   private java.util.Date m_period;

   private int m_type;

   private java.util.Date m_creationDate;

   private int m_keyId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
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

   public String getIp() {
      return m_ip;
   }

   public int getKeyId() {
      return m_keyId;
   }

   public String getName() {
      return m_name;
   }

   public java.util.Date getPeriod() {
      return m_period;
   }

   public int getType() {
      return m_type;
   }

   public MonthlyReport setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public MonthlyReport setDomain(String domain) {
      setFieldUsed(DOMAIN, true);
      m_domain = domain;
      return this;
   }

   public MonthlyReport setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public MonthlyReport setIp(String ip) {
      setFieldUsed(IP, true);
      m_ip = ip;
      return this;
   }

   public MonthlyReport setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public MonthlyReport setName(String name) {
      setFieldUsed(NAME, true);
      m_name = name;
      return this;
   }

   public MonthlyReport setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public MonthlyReport setType(int type) {
      setFieldUsed(TYPE, true);
      m_type = type;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("MonthlyReport[");
      sb.append("creation-date: ").append(m_creationDate);
      sb.append(", domain: ").append(m_domain);
      sb.append(", id: ").append(m_id);
      sb.append(", ip: ").append(m_ip);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", name: ").append(m_name);
      sb.append(", period: ").append(m_period);
      sb.append(", type: ").append(m_type);
      sb.append("]");
      return sb.toString();
   }

}
