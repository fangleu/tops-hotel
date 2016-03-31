package com.dianping.cat.home.dal.report;

import static com.dianping.cat.home.dal.report.ConfigModificationEntity.ACCOUNT_NAME;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.ACTION_NAME;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.ARGUMENT;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.CREATION_DATE;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.DATE;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.ID;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.KEY_ID;
import static com.dianping.cat.home.dal.report.ConfigModificationEntity.USER_NAME;

import org.unidal.dal.jdbc.DataObject;

public class ConfigModification extends DataObject {
   private int m_id;

   private String m_userName;

   private String m_accountName;

   private String m_actionName;

   private String m_argument;

   private java.util.Date m_date;

   private java.util.Date m_creationDate;

   private int m_keyId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public String getAccountName() {
      return m_accountName;
   }

   public String getActionName() {
      return m_actionName;
   }

   public String getArgument() {
      return m_argument;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public java.util.Date getDate() {
      return m_date;
   }

   public int getId() {
      return m_id;
   }

   public int getKeyId() {
      return m_keyId;
   }

   public String getUserName() {
      return m_userName;
   }

   public ConfigModification setAccountName(String accountName) {
      setFieldUsed(ACCOUNT_NAME, true);
      m_accountName = accountName;
      return this;
   }

   public ConfigModification setActionName(String actionName) {
      setFieldUsed(ACTION_NAME, true);
      m_actionName = actionName;
      return this;
   }

   public ConfigModification setArgument(String argument) {
      setFieldUsed(ARGUMENT, true);
      m_argument = argument;
      return this;
   }

   public ConfigModification setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public ConfigModification setDate(java.util.Date date) {
      setFieldUsed(DATE, true);
      m_date = date;
      return this;
   }

   public ConfigModification setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public ConfigModification setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public ConfigModification setUserName(String userName) {
      setFieldUsed(USER_NAME, true);
      m_userName = userName;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("ConfigModification[");
      sb.append("account-name: ").append(m_accountName);
      sb.append(", action-name: ").append(m_actionName);
      sb.append(", argument: ").append(m_argument);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", date: ").append(m_date);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", user-name: ").append(m_userName);
      sb.append("]");
      return sb.toString();
   }

}
