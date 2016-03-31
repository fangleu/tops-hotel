package com.dianping.cat.consumer.dal;

import static com.dianping.cat.consumer.dal.BusinessReportEntity.CONTENT;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.CREATION_DATE;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.END_DATE;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.ID;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.IP;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.KEY_ID;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.NAME;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.PERIOD;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.PRODUCT_LINE;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.START_DATE;
import static com.dianping.cat.consumer.dal.BusinessReportEntity.TYPE;

import org.unidal.dal.jdbc.DataObject;

public class BusinessReport extends DataObject {
   private int m_id;

   private int m_type;

   private String m_name;

   private String m_ip;

   private String m_productLine;

   private java.util.Date m_period;

   private byte[] m_content;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private java.util.Date m_startDate;

   private java.util.Date m_endDate;

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

   public String getProductLine() {
      return m_productLine;
   }

   public java.util.Date getStartDate() {
      return m_startDate;
   }

   public int getType() {
      return m_type;
   }

   public BusinessReport setContent(byte[] content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public BusinessReport setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public BusinessReport setEndDate(java.util.Date endDate) {
      setFieldUsed(END_DATE, true);
      m_endDate = endDate;
      return this;
   }

   public BusinessReport setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public BusinessReport setIp(String ip) {
      setFieldUsed(IP, true);
      m_ip = ip;
      return this;
   }

   public BusinessReport setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public BusinessReport setName(String name) {
      setFieldUsed(NAME, true);
      m_name = name;
      return this;
   }

   public BusinessReport setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public BusinessReport setProductLine(String productLine) {
      setFieldUsed(PRODUCT_LINE, true);
      m_productLine = productLine;
      return this;
   }

   public BusinessReport setStartDate(java.util.Date startDate) {
      setFieldUsed(START_DATE, true);
      m_startDate = startDate;
      return this;
   }

   public BusinessReport setType(int type) {
      setFieldUsed(TYPE, true);
      m_type = type;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("BusinessReport[");
      sb.append("content: ").append(m_content == null ? null : java.util.Arrays.asList(m_content));
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", end-date: ").append(m_endDate);
      sb.append(", id: ").append(m_id);
      sb.append(", ip: ").append(m_ip);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", name: ").append(m_name);
      sb.append(", period: ").append(m_period);
      sb.append(", product-line: ").append(m_productLine);
      sb.append(", start-date: ").append(m_startDate);
      sb.append(", type: ").append(m_type);
      sb.append("]");
      return sb.toString();
   }

}
