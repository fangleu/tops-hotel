package com.dianping.cat.home.dal.report;

import static com.dianping.cat.home.dal.report.TopologyGraphEntity.CONTENT;
import static com.dianping.cat.home.dal.report.TopologyGraphEntity.CREATION_DATE;
import static com.dianping.cat.home.dal.report.TopologyGraphEntity.ID;
import static com.dianping.cat.home.dal.report.TopologyGraphEntity.IP;
import static com.dianping.cat.home.dal.report.TopologyGraphEntity.KEY_ID;
import static com.dianping.cat.home.dal.report.TopologyGraphEntity.PERIOD;
import static com.dianping.cat.home.dal.report.TopologyGraphEntity.TYPE;

import org.unidal.dal.jdbc.DataObject;

public class TopologyGraph extends DataObject {
   private int m_id;

   private String m_ip;

   private java.util.Date m_period;

   private int m_type;

   private byte[] m_content;

   private java.util.Date m_creationDate;

   private int m_keyId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public byte[] getContent() {
      return m_content;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
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

   public java.util.Date getPeriod() {
      return m_period;
   }

   public int getType() {
      return m_type;
   }

   public TopologyGraph setContent(byte[] content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public TopologyGraph setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public TopologyGraph setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public TopologyGraph setIp(String ip) {
      setFieldUsed(IP, true);
      m_ip = ip;
      return this;
   }

   public TopologyGraph setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public TopologyGraph setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public TopologyGraph setType(int type) {
      setFieldUsed(TYPE, true);
      m_type = type;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("TopologyGraph[");
      sb.append("content: ").append(m_content == null ? null : java.util.Arrays.asList(m_content));
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", id: ").append(m_id);
      sb.append(", ip: ").append(m_ip);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", period: ").append(m_period);
      sb.append(", type: ").append(m_type);
      sb.append("]");
      return sb.toString();
   }

}
