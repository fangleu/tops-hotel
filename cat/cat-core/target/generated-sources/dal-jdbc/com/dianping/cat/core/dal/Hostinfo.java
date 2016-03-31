package com.dianping.cat.core.dal;

import static com.dianping.cat.core.dal.HostinfoEntity.CREATION_DATE;
import static com.dianping.cat.core.dal.HostinfoEntity.DOMAIN;
import static com.dianping.cat.core.dal.HostinfoEntity.HOSTNAME;
import static com.dianping.cat.core.dal.HostinfoEntity.ID;
import static com.dianping.cat.core.dal.HostinfoEntity.IP;
import static com.dianping.cat.core.dal.HostinfoEntity.KEY_ID;
import static com.dianping.cat.core.dal.HostinfoEntity.LAST_MODIFIED_DATE;

import org.unidal.dal.jdbc.DataObject;

public class Hostinfo extends DataObject {
   private int m_id;

   private String m_ip;

   private String m_domain;

   private String m_hostname;

   private java.util.Date m_creationDate;

   private java.util.Date m_lastModifiedDate;

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

   public java.util.Date getLastModifiedDate() {
      return m_lastModifiedDate;
   }

   public Hostinfo setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public Hostinfo setDomain(String domain) {
      setFieldUsed(DOMAIN, true);
      m_domain = domain;
      return this;
   }

   public Hostinfo setHostname(String hostname) {
      setFieldUsed(HOSTNAME, true);
      m_hostname = hostname;
      return this;
   }

   public Hostinfo setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public Hostinfo setIp(String ip) {
      setFieldUsed(IP, true);
      m_ip = ip;
      return this;
   }

   public Hostinfo setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public Hostinfo setLastModifiedDate(java.util.Date lastModifiedDate) {
      setFieldUsed(LAST_MODIFIED_DATE, true);
      m_lastModifiedDate = lastModifiedDate;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("Hostinfo[");
      sb.append("creation-date: ").append(m_creationDate);
      sb.append(", domain: ").append(m_domain);
      sb.append(", hostname: ").append(m_hostname);
      sb.append(", id: ").append(m_id);
      sb.append(", ip: ").append(m_ip);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", last-modified-date: ").append(m_lastModifiedDate);
      sb.append("]");
      return sb.toString();
   }

}
