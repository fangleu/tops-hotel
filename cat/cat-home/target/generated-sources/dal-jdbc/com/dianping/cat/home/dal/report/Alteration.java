package com.dianping.cat.home.dal.report;

import static com.dianping.cat.home.dal.report.AlterationEntity.ALT_GROUP;
import static com.dianping.cat.home.dal.report.AlterationEntity.CONTENT;
import static com.dianping.cat.home.dal.report.AlterationEntity.CREATION_DATE;
import static com.dianping.cat.home.dal.report.AlterationEntity.DATE;
import static com.dianping.cat.home.dal.report.AlterationEntity.DOMAIN;
import static com.dianping.cat.home.dal.report.AlterationEntity.END_TIME;
import static com.dianping.cat.home.dal.report.AlterationEntity.HOSTNAME;
import static com.dianping.cat.home.dal.report.AlterationEntity.ID;
import static com.dianping.cat.home.dal.report.AlterationEntity.IP;
import static com.dianping.cat.home.dal.report.AlterationEntity.KEY_ID;
import static com.dianping.cat.home.dal.report.AlterationEntity.START_TIME;
import static com.dianping.cat.home.dal.report.AlterationEntity.STATUS;
import static com.dianping.cat.home.dal.report.AlterationEntity.TITLE;
import static com.dianping.cat.home.dal.report.AlterationEntity.TYPE;
import static com.dianping.cat.home.dal.report.AlterationEntity.TYPES;
import static com.dianping.cat.home.dal.report.AlterationEntity.URL;
import static com.dianping.cat.home.dal.report.AlterationEntity.USER;

import org.unidal.dal.jdbc.DataObject;

public class Alteration extends DataObject {
   private int m_id;

   private String m_type;

   private String m_title;

   private String m_domain;

   private String m_hostname;

   private String m_ip;

   private java.util.Date m_date;

   private String m_user;

   private String m_altGroup;

   private String m_content;

   private String m_url;

   private int m_status;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private String[] m_types;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public String getAltGroup() {
      return m_altGroup;
   }

   public String getContent() {
      return m_content;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public java.util.Date getDate() {
      return m_date;
   }

   public String getDomain() {
      return m_domain;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public String getHostname() {
      return m_hostname;
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

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   public int getStatus() {
      return m_status;
   }

   public String getTitle() {
      return m_title;
   }

   public String getType() {
      return m_type;
   }

   public String[] getTypes() {
      return m_types;
   }

   public String getUrl() {
      return m_url;
   }

   public String getUser() {
      return m_user;
   }

   public Alteration setAltGroup(String altGroup) {
      setFieldUsed(ALT_GROUP, true);
      m_altGroup = altGroup;
      return this;
   }

   public Alteration setContent(String content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public Alteration setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public Alteration setDate(java.util.Date date) {
      setFieldUsed(DATE, true);
      m_date = date;
      return this;
   }

   public Alteration setDomain(String domain) {
      setFieldUsed(DOMAIN, true);
      m_domain = domain;
      return this;
   }

   public Alteration setEndTime(java.util.Date endTime) {
      setFieldUsed(END_TIME, true);
      m_endTime = endTime;
      return this;
   }

   public Alteration setHostname(String hostname) {
      setFieldUsed(HOSTNAME, true);
      m_hostname = hostname;
      return this;
   }

   public Alteration setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public Alteration setIp(String ip) {
      setFieldUsed(IP, true);
      m_ip = ip;
      return this;
   }

   public Alteration setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public Alteration setStartTime(java.util.Date startTime) {
      setFieldUsed(START_TIME, true);
      m_startTime = startTime;
      return this;
   }

   public Alteration setStatus(int status) {
      setFieldUsed(STATUS, true);
      m_status = status;
      return this;
   }

   public Alteration setTitle(String title) {
      setFieldUsed(TITLE, true);
      m_title = title;
      return this;
   }

   public Alteration setType(String type) {
      setFieldUsed(TYPE, true);
      m_type = type;
      return this;
   }

   public Alteration setTypes(String[] types) {
      setFieldUsed(TYPES, true);
      m_types = types;
      return this;
   }

   public Alteration setUrl(String url) {
      setFieldUsed(URL, true);
      m_url = url;
      return this;
   }

   public Alteration setUser(String user) {
      setFieldUsed(USER, true);
      m_user = user;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("Alteration[");
      sb.append("alt-group: ").append(m_altGroup);
      sb.append(", content: ").append(m_content);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", date: ").append(m_date);
      sb.append(", domain: ").append(m_domain);
      sb.append(", end-time: ").append(m_endTime);
      sb.append(", hostname: ").append(m_hostname);
      sb.append(", id: ").append(m_id);
      sb.append(", ip: ").append(m_ip);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", start-time: ").append(m_startTime);
      sb.append(", status: ").append(m_status);
      sb.append(", title: ").append(m_title);
      sb.append(", type: ").append(m_type);
      sb.append(", types: ").append(m_types == null ? null : java.util.Arrays.asList(m_types));
      sb.append(", url: ").append(m_url);
      sb.append(", user: ").append(m_user);
      sb.append("]");
      return sb.toString();
   }

}
