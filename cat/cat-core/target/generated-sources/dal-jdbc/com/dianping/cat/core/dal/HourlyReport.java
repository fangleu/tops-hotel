package com.dianping.cat.core.dal;

import static com.dianping.cat.core.dal.HourlyReportEntity.CREATION_DATE;
import static com.dianping.cat.core.dal.HourlyReportEntity.DOMAIN;
import static com.dianping.cat.core.dal.HourlyReportEntity.END_DATE;
import static com.dianping.cat.core.dal.HourlyReportEntity.ID;
import static com.dianping.cat.core.dal.HourlyReportEntity.IP;
import static com.dianping.cat.core.dal.HourlyReportEntity.KEY_ID;
import static com.dianping.cat.core.dal.HourlyReportEntity.NAME;
import static com.dianping.cat.core.dal.HourlyReportEntity.PERIOD;
import static com.dianping.cat.core.dal.HourlyReportEntity.START_DATE;
import static com.dianping.cat.core.dal.HourlyReportEntity.TYPE;

import org.unidal.dal.jdbc.DataObject;

public class HourlyReport extends DataObject {
   private int m_id;

   private int m_type;

   private String m_name;

   private String m_ip;

   private String m_domain;

   private java.util.Date m_period;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private java.util.Date m_startDate;

   private java.util.Date m_endDate;

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

   public java.util.Date getEndDate() {
      return m_endDate;
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

   public java.util.Date getStartDate() {
      return m_startDate;
   }

   public int getType() {
      return m_type;
   }

   public HourlyReport setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public HourlyReport setDomain(String domain) {
      setFieldUsed(DOMAIN, true);
      m_domain = domain;
      return this;
   }

   public HourlyReport setEndDate(java.util.Date endDate) {
      setFieldUsed(END_DATE, true);
      m_endDate = endDate;
      return this;
   }

   public HourlyReport setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public HourlyReport setIp(String ip) {
      setFieldUsed(IP, true);
      m_ip = ip;
      return this;
   }

   public HourlyReport setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public HourlyReport setName(String name) {
      setFieldUsed(NAME, true);
      m_name = name;
      return this;
   }

   public HourlyReport setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public HourlyReport setStartDate(java.util.Date startDate) {
      setFieldUsed(START_DATE, true);
      m_startDate = startDate;
      return this;
   }

   public HourlyReport setType(int type) {
      setFieldUsed(TYPE, true);
      m_type = type;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("HourlyReport[");
      sb.append("creation-date: ").append(m_creationDate);
      sb.append(", domain: ").append(m_domain);
      sb.append(", end-date: ").append(m_endDate);
      sb.append(", id: ").append(m_id);
      sb.append(", ip: ").append(m_ip);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", name: ").append(m_name);
      sb.append(", period: ").append(m_period);
      sb.append(", start-date: ").append(m_startDate);
      sb.append(", type: ").append(m_type);
      sb.append("]");
      return sb.toString();
   }

}
