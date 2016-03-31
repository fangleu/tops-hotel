package com.dianping.cat.app;

import static com.dianping.cat.app.AppConnectionDataEntity.ACCESS_NUMBER;
import static com.dianping.cat.app.AppConnectionDataEntity.ACCESS_NUMBER_SUM;
import static com.dianping.cat.app.AppConnectionDataEntity.APP_VERSION;
import static com.dianping.cat.app.AppConnectionDataEntity.CITY;
import static com.dianping.cat.app.AppConnectionDataEntity.CODE;
import static com.dianping.cat.app.AppConnectionDataEntity.COMMAND_ID;
import static com.dianping.cat.app.AppConnectionDataEntity.CONNECT_TYPE;
import static com.dianping.cat.app.AppConnectionDataEntity.CREATION_DATE;
import static com.dianping.cat.app.AppConnectionDataEntity.END_MINUTE_ORDER;
import static com.dianping.cat.app.AppConnectionDataEntity.ID;
import static com.dianping.cat.app.AppConnectionDataEntity.KEY_ID;
import static com.dianping.cat.app.AppConnectionDataEntity.MINUTE_ORDER;
import static com.dianping.cat.app.AppConnectionDataEntity.NETWORK;
import static com.dianping.cat.app.AppConnectionDataEntity.OPERATOR;
import static com.dianping.cat.app.AppConnectionDataEntity.PERIOD;
import static com.dianping.cat.app.AppConnectionDataEntity.PLATFORM;
import static com.dianping.cat.app.AppConnectionDataEntity.REQUEST_PACKAGE;
import static com.dianping.cat.app.AppConnectionDataEntity.REQUEST_PACKAGE_SUM;
import static com.dianping.cat.app.AppConnectionDataEntity.RESPONSE_PACKAGE;
import static com.dianping.cat.app.AppConnectionDataEntity.RESPONSE_PACKAGE_SUM;
import static com.dianping.cat.app.AppConnectionDataEntity.RESPONSE_SUM_TIME;
import static com.dianping.cat.app.AppConnectionDataEntity.RESPONSE_SUM_TIME_SUM;
import static com.dianping.cat.app.AppConnectionDataEntity.START_MINUTE_ORDER;
import static com.dianping.cat.app.AppConnectionDataEntity.STATUS;

import org.unidal.dal.jdbc.DataObject;

public class AppConnectionData extends DataObject {
   private int m_id;

   private java.util.Date m_period;

   private int m_minuteOrder;

   private int m_city;

   private int m_operator;

   private int m_network;

   private int m_appVersion;

   private int m_connectType;

   private int m_code;

   private int m_platform;

   private long m_accessNumber;

   private long m_responseSumTime;

   private long m_requestPackage;

   private long m_responsePackage;

   private int m_status;

   private java.util.Date m_creationDate;

   private int m_keyId;

   private long m_accessNumberSum;

   private long m_responseSumTimeSum;

   private long m_requestPackageSum;

   private long m_responsePackageSum;

   private int m_commandId;

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

   public int getAppVersion() {
      return m_appVersion;
   }

   public int getCity() {
      return m_city;
   }

   public int getCode() {
      return m_code;
   }

   public int getCommandId() {
      return m_commandId;
   }

   public int getConnectType() {
      return m_connectType;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
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

   public long getRequestPackage() {
      return m_requestPackage;
   }

   public long getRequestPackageSum() {
      return m_requestPackageSum;
   }

   public long getResponsePackage() {
      return m_responsePackage;
   }

   public long getResponsePackageSum() {
      return m_responsePackageSum;
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

   public int getStatus() {
      return m_status;
   }

   public AppConnectionData setAccessNumber(long accessNumber) {
      setFieldUsed(ACCESS_NUMBER, true);
      m_accessNumber = accessNumber;
      return this;
   }

   public AppConnectionData setAccessNumberSum(long accessNumberSum) {
      setFieldUsed(ACCESS_NUMBER_SUM, true);
      m_accessNumberSum = accessNumberSum;
      return this;
   }

   public AppConnectionData setAppVersion(int appVersion) {
      setFieldUsed(APP_VERSION, true);
      m_appVersion = appVersion;
      return this;
   }

   public AppConnectionData setCity(int city) {
      setFieldUsed(CITY, true);
      m_city = city;
      return this;
   }

   public AppConnectionData setCode(int code) {
      setFieldUsed(CODE, true);
      m_code = code;
      return this;
   }

   public AppConnectionData setCommandId(int commandId) {
      setFieldUsed(COMMAND_ID, true);
      m_commandId = commandId;
      return this;
   }

   public AppConnectionData setConnectType(int connectType) {
      setFieldUsed(CONNECT_TYPE, true);
      m_connectType = connectType;
      return this;
   }

   public AppConnectionData setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public AppConnectionData setEndMinuteOrder(int endMinuteOrder) {
      setFieldUsed(END_MINUTE_ORDER, true);
      m_endMinuteOrder = endMinuteOrder;
      return this;
   }

   public AppConnectionData setId(int id) {
      setFieldUsed(ID, true);
      m_id = id;

      setFieldUsed(KEY_ID, true);
      m_keyId = id;
      return this;
   }

   public AppConnectionData setKeyId(int keyId) {
      setFieldUsed(KEY_ID, true);
      m_keyId = keyId;
      return this;
   }

   public AppConnectionData setMinuteOrder(int minuteOrder) {
      setFieldUsed(MINUTE_ORDER, true);
      m_minuteOrder = minuteOrder;
      return this;
   }

   public AppConnectionData setNetwork(int network) {
      setFieldUsed(NETWORK, true);
      m_network = network;
      return this;
   }

   public AppConnectionData setOperator(int operator) {
      setFieldUsed(OPERATOR, true);
      m_operator = operator;
      return this;
   }

   public AppConnectionData setPeriod(java.util.Date period) {
      setFieldUsed(PERIOD, true);
      m_period = period;
      return this;
   }

   public AppConnectionData setPlatform(int platform) {
      setFieldUsed(PLATFORM, true);
      m_platform = platform;
      return this;
   }

   public AppConnectionData setRequestPackage(long requestPackage) {
      setFieldUsed(REQUEST_PACKAGE, true);
      m_requestPackage = requestPackage;
      return this;
   }

   public AppConnectionData setRequestPackageSum(long requestPackageSum) {
      setFieldUsed(REQUEST_PACKAGE_SUM, true);
      m_requestPackageSum = requestPackageSum;
      return this;
   }

   public AppConnectionData setResponsePackage(long responsePackage) {
      setFieldUsed(RESPONSE_PACKAGE, true);
      m_responsePackage = responsePackage;
      return this;
   }

   public AppConnectionData setResponsePackageSum(long responsePackageSum) {
      setFieldUsed(RESPONSE_PACKAGE_SUM, true);
      m_responsePackageSum = responsePackageSum;
      return this;
   }

   public AppConnectionData setResponseSumTime(long responseSumTime) {
      setFieldUsed(RESPONSE_SUM_TIME, true);
      m_responseSumTime = responseSumTime;
      return this;
   }

   public AppConnectionData setResponseSumTimeSum(long responseSumTimeSum) {
      setFieldUsed(RESPONSE_SUM_TIME_SUM, true);
      m_responseSumTimeSum = responseSumTimeSum;
      return this;
   }

   public AppConnectionData setStartMinuteOrder(int startMinuteOrder) {
      setFieldUsed(START_MINUTE_ORDER, true);
      m_startMinuteOrder = startMinuteOrder;
      return this;
   }

   public AppConnectionData setStatus(int status) {
      setFieldUsed(STATUS, true);
      m_status = status;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("AppConnectionData[");
      sb.append("access-number: ").append(m_accessNumber);
      sb.append(", access-number-sum: ").append(m_accessNumberSum);
      sb.append(", app-version: ").append(m_appVersion);
      sb.append(", city: ").append(m_city);
      sb.append(", code: ").append(m_code);
      sb.append(", command-id: ").append(m_commandId);
      sb.append(", connect-type: ").append(m_connectType);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", end-minute-order: ").append(m_endMinuteOrder);
      sb.append(", id: ").append(m_id);
      sb.append(", key-id: ").append(m_keyId);
      sb.append(", minute-order: ").append(m_minuteOrder);
      sb.append(", network: ").append(m_network);
      sb.append(", operator: ").append(m_operator);
      sb.append(", period: ").append(m_period);
      sb.append(", platform: ").append(m_platform);
      sb.append(", request-package: ").append(m_requestPackage);
      sb.append(", request-package-sum: ").append(m_requestPackageSum);
      sb.append(", response-package: ").append(m_responsePackage);
      sb.append(", response-package-sum: ").append(m_responsePackageSum);
      sb.append(", response-sum-time: ").append(m_responseSumTime);
      sb.append(", response-sum-time-sum: ").append(m_responseSumTimeSum);
      sb.append(", start-minute-order: ").append(m_startMinuteOrder);
      sb.append(", status: ").append(m_status);
      sb.append("]");
      return sb.toString();
   }

}
