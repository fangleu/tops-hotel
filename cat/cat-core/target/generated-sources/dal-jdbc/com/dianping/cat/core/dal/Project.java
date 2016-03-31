package com.dianping.cat.core.dal;

import static com.dianping.cat.core.dal.ProjectEntity.BU;
import static com.dianping.cat.core.dal.ProjectEntity.CMDB_DOMAIN;
import static com.dianping.cat.core.dal.ProjectEntity.CMDB_PRODUCTLINE;
import static com.dianping.cat.core.dal.ProjectEntity.CREATION_DATE;
import static com.dianping.cat.core.dal.ProjectEntity.DOMAIN;
import static com.dianping.cat.core.dal.ProjectEntity.EMAIL;
import static com.dianping.cat.core.dal.ProjectEntity.ID;
import static com.dianping.cat.core.dal.ProjectEntity.KEY_ID;
import static com.dianping.cat.core.dal.ProjectEntity.LEVEL;
import static com.dianping.cat.core.dal.ProjectEntity.MODIFY_DATE;
import static com.dianping.cat.core.dal.ProjectEntity.OWNER;
import static com.dianping.cat.core.dal.ProjectEntity.PHONE;

import org.unidal.dal.jdbc.DataObject;

public class Project extends DataObject {
   private int m_id;

   private String m_domain;

   private String m_cmdbDomain;

   private int m_level;

   private String m_bu;

   private String m_cmdbProductline;

   private String m_owner;

   private String m_email;

   private String m_phone;

   private java.util.Date m_creationDate;

   private java.util.Date m_modifyDate;

   private int m_keyId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public String getBu() {
      return m_bu;
   }

   public String getCmdbDomain() {
      return m_cmdbDomain;
   }

   public String getCmdbProductline() {
      return m_cmdbProductline;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getEmail() {
      return m_email;
   }

   public int getId() {
      return m_id;
   }

   public int getKeyId() {
      return m_keyId;
   }

   public int getLevel() {
      return m_level;
   }

   public java.util.Date getModifyDate() {
      return m_modifyDate;
   }

   public String getOwner() {
      return m_owner;
   }

   public String getPhone() {
      return m_phone;
   }

   public Project setBu(String bu) {
      setFieldUsed(BU, true);
      m_bu = bu;
      return this;
   }

   public Project setCmdbDomain(String cmdbDomain) {
      setFieldUsed(CMDB_DOMAIN, true);
      m_cmdbDomain = cmdbDomain;
      return this;
   }

   public Project setCmdbProductline(String cmdbProductline) {
      setFieldUsed(CMDB_PRODUCTLINE, true);
      m_cmdbProductline = cmdbProductline;
      return this;
   }

   public Project setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public Project setDomain(String domain) {
      setFieldUsed(DOMAIN, true);
      m_domain = domain;
      return this;
   }

   public Project setEmail(String email) {
      setFieldUsed(EMAIL, true);
      m_email = email;
      return this;
   }

   public Project setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public Project setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public Project setLevel(int level) {
      setFieldUsed(LEVEL, true);
      m_level = level;
      return this;
   }

   public Project setModifyDate(java.util.Date modifyDate) {
      setFieldUsed(MODIFY_DATE, true);
      m_modifyDate = modifyDate;
      return this;
   }

   public Project setOwner(String owner) {
      setFieldUsed(OWNER, true);
      m_owner = owner;
      return this;
   }

   public Project setPhone(String phone) {
      setFieldUsed(PHONE, true);
      m_phone = phone;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("Project[");
      sb.append("bu: ").append(m_bu);
      sb.append(", cmdb-domain: ").append(m_cmdbDomain);
      sb.append(", cmdb-productline: ").append(m_cmdbProductline);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", domain: ").append(m_domain);
      sb.append(", email: ").append(m_email);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", level: ").append(m_level);
      sb.append(", modify-date: ").append(m_modifyDate);
      sb.append(", owner: ").append(m_owner);
      sb.append(", phone: ").append(m_phone);
      sb.append("]");
      return sb.toString();
   }

}
