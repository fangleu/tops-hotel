package com.dianping.cat.home.dal.report;

import static com.dianping.cat.home.dal.report.UserDefineRuleEntity.CONTENT;
import static com.dianping.cat.home.dal.report.UserDefineRuleEntity.CREATION_DATE;
import static com.dianping.cat.home.dal.report.UserDefineRuleEntity.ID;
import static com.dianping.cat.home.dal.report.UserDefineRuleEntity.KEY_ID;
import static com.dianping.cat.home.dal.report.UserDefineRuleEntity.MAX_ID;

import org.unidal.dal.jdbc.DataObject;

public class UserDefineRule extends DataObject {
   private int m_id;

   private String m_content;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private int m_maxId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public String getContent() {
      return m_content;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public int getId() {
      return m_id;
   }

   public int getKeyId() {
      return m_keyId;
   }

   public int getMaxId() {
      return m_maxId;
   }

   public UserDefineRule setContent(String content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public UserDefineRule setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public UserDefineRule setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public UserDefineRule setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public UserDefineRule setMaxId(int maxId) {
      setFieldUsed(MAX_ID, true);
      m_maxId = maxId;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("UserDefineRule[");
      sb.append("content: ").append(m_content);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", max-id: ").append(m_maxId);
      sb.append("]");
      return sb.toString();
   }

}
