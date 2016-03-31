package com.dianping.cat.app;

import static com.dianping.cat.app.WebApiDataEntity.ACCESS_NUMBER;
import static com.dianping.cat.app.WebApiDataEntity.ACCESS_NUMBER_SUM;
import static com.dianping.cat.app.WebApiDataEntity.API_ID;
import static com.dianping.cat.app.WebApiDataEntity.CITY;
import static com.dianping.cat.app.WebApiDataEntity.CODE;
import static com.dianping.cat.app.WebApiDataEntity.END_MINUTE_ORDER;
import static com.dianping.cat.app.WebApiDataEntity.ID;
import static com.dianping.cat.app.WebApiDataEntity.KEY_ID;
import static com.dianping.cat.app.WebApiDataEntity.MINUTE_ORDER;
import static com.dianping.cat.app.WebApiDataEntity.OPERATOR;
import static com.dianping.cat.app.WebApiDataEntity.PERIOD;
import static com.dianping.cat.app.WebApiDataEntity.RESPONSE_SUM_TIME;
import static com.dianping.cat.app.WebApiDataEntity.RESPONSE_SUM_TIME_SUM;
import static com.dianping.cat.app.WebApiDataEntity.START_MINUTE_ORDER;
import static com.dianping.cat.app.WebApiDataEntity.UPDATETIME;

import org.unidal.dal.jdbc.DataObject;

public class WebApiData extends DataObject {
   private int m_id;

   private java.util.Date m_period;

   private int m_minuteOrder;

   private int m_city;

   private int m_operator;

   private int m_code;

   private long m_accessNumber;

   private long m_responseSumTime;

   private java.util.Date m_updatetime;

   private int m_keyId;

   private long m_accessNumberSum;

   private long m_responseSumTimeSum;

   private int m_apiId;

   private int m_startMinuteOrder;

   private int m_endMinuteOrder;

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

   public int getApiId() {
      return m_apiId;
   }

   public int getCity() {
      return m_city;
   }

   public int getCode() {
      return m_code;
   }

   public int getEndMinuteOrder() {
      return m_endMinuteOrder;
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

   public int getOperator() {
      return m_operator;
   }

   public java.util.Date getPeriod() {
      return m_period;
   }

   public long getResponseSumTime() {
      return m_responseSumTime;
   }

   public long getResponseSumTimeSum() {
      return m_responseSumTimeSum;
   }

   public int getStartMinuteOrder() {
      return m_startMinuteOrder;
   }

   public java.util.Date getUpdatetime() {
      return m_updatetime;
   }

   public WebApiData setAccessNumber(long accessNumber) {
      setFieldUsed(ACCESS_NUMBER, true);
      m_accessNumber = accessNumber;
      return this;
   }

   public WebApiData setAccessNumberSum(long accessNumberSum) {
      setFieldUsed(ACCESS_NUMBER_SUM, true);
      m_accessNumberSum = accessNumberSum;
      return this;
   }

   public WebApiData setApiId(int apiId) {
      setFieldUsed(API_ID, true);
      m_apiId = apiId;
      return this;
   }

   public WebApiData setCity(int city) {
      setFieldUsed(CITY, true);
      m_city = city;
      return this;
   }

   public WebApiData setCode(int code) {
      setFieldUsed(CODE, true);
      m_code = code;
      return this;
   }

   public WebApiData setEndMinuteOrder(int endMinuteOrder) {
      setFieldUsed(END_MINUTE_ORDER, true);
      m_endMinuteOrder = endMinuteOrder;
      return this;
   }

   public WebApiData setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public WebApiData setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public WebApiData setMinuteOrder(int minuteOrder) {
      setFieldUsed(MINUTE_ORDER, true);
      m_minuteOrder = minuteOrder;
      return this;
   }

   public WebApiData setOperator(int operator) {
      setFieldUsed(OPERATOR, true);
      m_operator = operator;
      return this;
   }

   public WebApiData setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public WebApiData setResponseSumTime(long responseSumTime) {
      setFieldUsed(RESPONSE_SUM_TIME, true);
      m_responseSumTime = responseSumTime;
      return this;
   }

   public WebApiData setResponseSumTimeSum(long responseSumTimeSum) {
      setFieldUsed(RESPONSE_SUM_TIME_SUM, true);
      m_responseSumTimeSum = responseSumTimeSum;
      return this;
   }

   public WebApiData setStartMinuteOrder(int startMinuteOrder) {
      setFieldUsed(START_MINUTE_ORDER, true);
      m_startMinuteOrder = startMinuteOrder;
      return this;
   }

   public WebApiData setUpdatetime(java.util.Date updatetime) {
      setFieldUsed(UPDATETIME, true);
      m_updatetime = updatetime;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("WebApiData[");
      sb.append("access-number: ").append(m_accessNumber);
      sb.append(", access-number-sum: ").append(m_accessNumberSum);
      sb.append(", api-id: ").append(m_apiId);
      sb.append(", city: ").append(m_city);
      sb.append(", code: ").append(m_code);
      sb.append(", end-minute-order: ").append(m_endMinuteOrder);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", minute-order: ").append(m_minuteOrder);
      sb.append(", operator: ").append(m_operator);
      sb.append(", period: ").append(m_period);
      sb.append(", response-sum-time: ").append(m_responseSumTime);
      sb.append(", response-sum-time-sum: ").append(m_responseSumTimeSum);
      sb.append(", start-minute-order: ").append(m_startMinuteOrder);
      sb.append(", updatetime: ").append(m_updatetime);
      sb.append("]");
      return sb.toString();
   }

}
