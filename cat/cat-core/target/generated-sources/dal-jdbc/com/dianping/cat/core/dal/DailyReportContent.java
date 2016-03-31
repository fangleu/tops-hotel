package com.dianping.cat.core.dal;

import static com.dianping.cat.core.dal.DailyReportContentEntity.CAPACITY;
import static com.dianping.cat.core.dal.DailyReportContentEntity.CONTENT;
import static com.dianping.cat.core.dal.DailyReportContentEntity.CONTENT_LENGTH;
import static com.dianping.cat.core.dal.DailyReportContentEntity.CREATION_DATE;
import static com.dianping.cat.core.dal.DailyReportContentEntity.END_ID;
import static com.dianping.cat.core.dal.DailyReportContentEntity.KEY_REPORT_ID;
import static com.dianping.cat.core.dal.DailyReportContentEntity.REPORT_ID;
import static com.dianping.cat.core.dal.DailyReportContentEntity.START_ID;

import org.unidal.dal.jdbc.DataObject;

public class DailyReportContent extends DataObject {
   private int m_reportId;

   private byte[] m_content;

   private java.util.Date m_creationDate;

   private int m_keyReportId;

   private double m_contentLength;

   private int m_startId;

   private int m_endId;

   private double m_capacity;

   @Override
   public void afterLoad() {
      m_keyReportId = m_reportId;
      super.clearUsage();
   }

   public double getCapacity() {
      return m_capacity;
   }

   public byte[] getContent() {
      return m_content;
   }

   public double getContentLength() {
      return m_contentLength;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public int getEndId() {
      return m_endId;
   }

   public int getKeyReportId() {
      return m_keyReportId;
   }

   public int getReportId() {
      return m_reportId;
   }

   public int getStartId() {
      return m_startId;
   }

   public DailyReportContent setCapacity(double capacity) {
      setFieldUsed(CAPACITY, true);
      m_capacity = capacity;
      return this;
   }

   public DailyReportContent setContent(byte[] content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public DailyReportContent setContentLength(double contentLength) {
      setFieldUsed(CONTENT_LENGTH, true);
      m_contentLength = contentLength;
      return this;
   }

   public DailyReportContent setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public DailyReportContent setEndId(int endId) {
      setFieldUsed(END_ID, true);
      m_endId = endId;
      return this;
   }

   public DailyReportContent setKeyReportId(int keyReportId) {
      setFieldUsed(KEY_REPORT_ID, true);
      m_keyReportId = keyReportId;
      return this;
   }

   public DailyReportContent setReportId(int reportId) {
      setFieldUsed(REPORT_ID, true);
      m_reportId = reportId;

      setFieldUsed(KEY_REPORT_ID, true);
      m_keyReportId = reportId;
      return this;
   }

   public DailyReportContent setStartId(int startId) {
      setFieldUsed(START_ID, true);
      m_startId = startId;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("DailyReportContent[");
      sb.append("capacity: ").append(m_capacity);
      sb.append(", content: ").append(m_content == null ? null : java.util.Arrays.asList(m_content));
      sb.append(", content-length: ").append(m_contentLength);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", end-id: ").append(m_endId);
      sb.append(", key-report-id: ").append(m_keyReportId);
      sb.append(", report-id: ").append(m_reportId);
      sb.append(", start-id: ").append(m_startId);
      sb.append("]");
      return sb.toString();
   }

}
