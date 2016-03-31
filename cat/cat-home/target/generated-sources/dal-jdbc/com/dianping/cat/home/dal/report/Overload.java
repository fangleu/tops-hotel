package com.dianping.cat.home.dal.report;

import static com.dianping.cat.home.dal.report.OverloadEntity.COUNT;
import static com.dianping.cat.home.dal.report.OverloadEntity.CREATION_DATE;
import static com.dianping.cat.home.dal.report.OverloadEntity.END_TIME;
import static com.dianping.cat.home.dal.report.OverloadEntity.ID;
import static com.dianping.cat.home.dal.report.OverloadEntity.KEY_ID;
import static com.dianping.cat.home.dal.report.OverloadEntity.MAX_ID;
import static com.dianping.cat.home.dal.report.OverloadEntity.PERIOD;
import static com.dianping.cat.home.dal.report.OverloadEntity.REPORT_ID;
import static com.dianping.cat.home.dal.report.OverloadEntity.REPORT_SIZE;
import static com.dianping.cat.home.dal.report.OverloadEntity.REPORT_TYPE;
import static com.dianping.cat.home.dal.report.OverloadEntity.START_TIME;
import static com.dianping.cat.home.dal.report.OverloadEntity.TYPE;

import org.unidal.dal.jdbc.DataObject;

public class Overload extends DataObject {
   private int m_id;

   private int m_reportId;

   private int m_reportType;

   private double m_reportSize;

   private java.util.Date m_period;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private int m_type;

   private int m_maxId;

   private int m_count;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public int getCount() {
      return m_count;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
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

   public java.util.Date getPeriod() {
      return m_period;
   }

   public int getReportId() {
      return m_reportId;
   }

   public double getReportSize() {
      return m_reportSize;
   }

   public int getReportType() {
      return m_reportType;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   public int getType() {
      return m_type;
   }

   public Overload setCount(int count) {
      setFieldUsed(COUNT, true);
      m_count = count;
      return this;
   }

   public Overload setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public Overload setEndTime(java.util.Date endTime) {
      setFieldUsed(END_TIME, true);
      m_endTime = endTime;
      return this;
   }

   public Overload setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public Overload setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public Overload setMaxId(int maxId) {
      setFieldUsed(MAX_ID, true);
      m_maxId = maxId;
      return this;
   }

   public Overload setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public Overload setReportId(int reportId) {
      setFieldUsed(REPORT_ID, true);
      m_reportId = reportId;
      return this;
   }

   public Overload setReportSize(double reportSize) {
      setFieldUsed(REPORT_SIZE, true);
      m_reportSize = reportSize;
      return this;
   }

   public Overload setReportType(int reportType) {
      setFieldUsed(REPORT_TYPE, true);
      m_reportType = reportType;
      return this;
   }

   public Overload setStartTime(java.util.Date startTime) {
      setFieldUsed(START_TIME, true);
      m_startTime = startTime;
      return this;
   }

   public Overload setType(int type) {
      setFieldUsed(TYPE, true);
      m_type = type;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("Overload[");
      sb.append("count: ").append(m_count);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", end-time: ").append(m_endTime);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", max-id: ").append(m_maxId);
      sb.append(", period: ").append(m_period);
      sb.append(", report-id: ").append(m_reportId);
      sb.append(", report-size: ").append(m_reportSize);
      sb.append(", report-type: ").append(m_reportType);
      sb.append(", start-time: ").append(m_startTime);
      sb.append(", type: ").append(m_type);
      sb.append("]");
      return sb.toString();
   }

}
