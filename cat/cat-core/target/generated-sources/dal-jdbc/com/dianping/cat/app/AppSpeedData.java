package com.dianping.cat.app;

import static com.dianping.cat.app.AppSpeedDataEntity.ACCESS_NUMBER;
import static com.dianping.cat.app.AppSpeedDataEntity.ACCESS_NUMBER_SUM;
import static com.dianping.cat.app.AppSpeedDataEntity.APP_VERSION;
import static com.dianping.cat.app.AppSpeedDataEntity.CITY;
import static com.dianping.cat.app.AppSpeedDataEntity.CREATION_DATE;
import static com.dianping.cat.app.AppSpeedDataEntity.ID;
import static com.dianping.cat.app.AppSpeedDataEntity.KEY_ID;
import static com.dianping.cat.app.AppSpeedDataEntity.MINUTE_ORDER;
import static com.dianping.cat.app.AppSpeedDataEntity.NETWORK;
import static com.dianping.cat.app.AppSpeedDataEntity.OPERATOR;
import static com.dianping.cat.app.AppSpeedDataEntity.PERIOD;
import static com.dianping.cat.app.AppSpeedDataEntity.PLATFORM;
import static com.dianping.cat.app.AppSpeedDataEntity.RESPONSE_SUM_TIME;
import static com.dianping.cat.app.AppSpeedDataEntity.RESPONSE_SUM_TIME_SUM;
import static com.dianping.cat.app.AppSpeedDataEntity.SLOW_ACCESS_NUMBER;
import static com.dianping.cat.app.AppSpeedDataEntity.SLOW_ACCESS_NUMBER_SUM;
import static com.dianping.cat.app.AppSpeedDataEntity.SLOW_RESPONSE_SUM_TIME;
import static com.dianping.cat.app.AppSpeedDataEntity.SLOW_RESPONSE_SUM_TIME_SUM;
import static com.dianping.cat.app.AppSpeedDataEntity.SPEED_ID;
import static com.dianping.cat.app.AppSpeedDataEntity.STATUS;

import org.unidal.dal.jdbc.DataObject;

public class AppSpeedData extends DataObject {
   private int m_id;

   private java.util.Date m_period;

   private int m_minuteOrder;

   private int m_city;

   private int m_operator;

   private int m_network;

   private int m_appVersion;

   private int m_platform;

   private long m_accessNumber;

   private long m_slowAccessNumber;

   private long m_responseSumTime;

   private long m_slowResponseSumTime;

   private int m_status;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private long m_accessNumberSum;

   private long m_slowAccessNumberSum;

   private long m_responseSumTimeSum;

   private long m_slowResponseSumTimeSum;

   private int m_speedId;

   @Override
   public void afterLoad() {
      m_keyId = m_id;
      super.clearUsage();
   }

   public long getAccessNumber() {
      return m_accessNumber;
   }

   public long getAccessNumberSum() {
      return m_accessNumberSum;
   }

   public int getAppVersion() {
      return m_appVersion;
   }

   public int getCity() {
      return m_city;
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

   public int getMinuteOrder() {
      return m_minuteOrder;
   }

   public int getNetwork() {
      return m_network;
   }

   public int getOperator() {
      return m_operator;
   }

   public java.util.Date getPeriod() {
      return m_period;
   }

   public int getPlatform() {
      return m_platform;
   }

   public long getResponseSumTime() {
      return m_responseSumTime;
   }

   public long getResponseSumTimeSum() {
      return m_responseSumTimeSum;
   }

   public long getSlowAccessNumber() {
      return m_slowAccessNumber;
   }

   public long getSlowAccessNumberSum() {
      return m_slowAccessNumberSum;
   }

   public long getSlowResponseSumTime() {
      return m_slowResponseSumTime;
   }

   public long getSlowResponseSumTimeSum() {
      return m_slowResponseSumTimeSum;
   }

   public int getSpeedId() {
      return m_speedId;
   }

   public int getStatus() {
      return m_status;
   }

   public AppSpeedData setAccessNumber(long accessNumber) {
      setFieldUsed(ACCESS_NUMBER, true);
      m_accessNumber = accessNumber;
      return this;
   }

   public AppSpeedData setAccessNumberSum(long accessNumberSum) {
      setFieldUsed(ACCESS_NUMBER_SUM, true);
      m_accessNumberSum = accessNumberSum;
      return this;
   }

   public AppSpeedData setAppVersion(int appVersion) {
      setFieldUsed(APP_VERSION, true);
      m_appVersion = appVersion;
      return this;
   }

   public AppSpeedData setCity(int city) {
      setFieldUsed(CITY, true);
      m_city = city;
      return this;
   }

   public AppSpeedData setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public AppSpeedData setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public AppSpeedData setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public AppSpeedData setMinuteOrder(int minuteOrder) {
      setFieldUsed(MINUTE_ORDER, true);
      m_minuteOrder = minuteOrder;
      return this;
   }

   public AppSpeedData setNetwork(int network) {
      setFieldUsed(NETWORK, true);
      m_network = network;
      return this;
   }

   public AppSpeedData setOperator(int operator) {
      setFieldUsed(OPERATOR, true);
      m_operator = operator;
      return this;
   }

   public AppSpeedData setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public AppSpeedData setPlatform(int platform) {
      setFieldUsed(PLATFORM, true);
      m_platform = platform;
      return this;
   }

   public AppSpeedData setResponseSumTime(long responseSumTime) {
      setFieldUsed(RESPONSE_SUM_TIME, true);
      m_responseSumTime = responseSumTime;
      return this;
   }

   public AppSpeedData setResponseSumTimeSum(long responseSumTimeSum) {
      setFieldUsed(RESPONSE_SUM_TIME_SUM, true);
      m_responseSumTimeSum = responseSumTimeSum;
      return this;
   }

   public AppSpeedData setSlowAccessNumber(long slowAccessNumber) {
      setFieldUsed(SLOW_ACCESS_NUMBER, true);
      m_slowAccessNumber = slowAccessNumber;
      return this;
   }

   public AppSpeedData setSlowAccessNumberSum(long slowAccessNumberSum) {
      setFieldUsed(SLOW_ACCESS_NUMBER_SUM, true);
      m_slowAccessNumberSum = slowAccessNumberSum;
      return this;
   }

   public AppSpeedData setSlowResponseSumTime(long slowResponseSumTime) {
      setFieldUsed(SLOW_RESPONSE_SUM_TIME, true);
      m_slowResponseSumTime = slowResponseSumTime;
      return this;
   }

   public AppSpeedData setSlowResponseSumTimeSum(long slowResponseSumTimeSum) {
      setFieldUsed(SLOW_RESPONSE_SUM_TIME_SUM, true);
      m_slowResponseSumTimeSum = slowResponseSumTimeSum;
      return this;
   }

   public AppSpeedData setSpeedId(int speedId) {
      setFieldUsed(SPEED_ID, true);
      m_speedId = speedId;
      return this;
   }

   public AppSpeedData setStatus(int status) {
      setFieldUsed(STATUS, true);
      m_status = status;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("AppSpeedData[");
      sb.append("access-number: ").append(m_accessNumber);
      sb.append(", access-number-sum: ").append(m_accessNumberSum);
      sb.append(", app-version: ").append(m_appVersion);
      sb.append(", city: ").append(m_city);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", minute-order: ").append(m_minuteOrder);
      sb.append(", network: ").append(m_network);
      sb.append(", operator: ").append(m_operator);
      sb.append(", period: ").append(m_period);
      sb.append(", platform: ").append(m_platform);
      sb.append(", response-sum-time: ").append(m_responseSumTime);
      sb.append(", response-sum-time-sum: ").append(m_responseSumTimeSum);
      sb.append(", slow-access-number: ").append(m_slowAccessNumber);
      sb.append(", slow-access-number-sum: ").append(m_slowAccessNumberSum);
      sb.append(", slow-response-sum-time: ").append(m_slowResponseSumTime);
      sb.append(", slow-response-sum-time-sum: ").append(m_slowResponseSumTimeSum);
      sb.append(", speed-id: ").append(m_speedId);
      sb.append(", status: ").append(m_status);
      sb.append("]");
      return sb.toString();
   }

}
