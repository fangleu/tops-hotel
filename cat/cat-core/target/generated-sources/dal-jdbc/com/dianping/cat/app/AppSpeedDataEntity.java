package com.dianping.cat.app;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "app-speed-data", physicalName = "app_speed_data", alias = "asd")
public class AppSpeedDataEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "period", nullable = false)
   public static final DataField PERIOD = new DataField("period");

   @Attribute(field = "minute_order", nullable = false)
   public static final DataField MINUTE_ORDER = new DataField("minute-order");

   @Attribute(field = "city", nullable = false)
   public static final DataField CITY = new DataField("city");

   @Attribute(field = "operator", nullable = false)
   public static final DataField OPERATOR = new DataField("operator");

   @Attribute(field = "network", nullable = false)
   public static final DataField NETWORK = new DataField("network");

   @Attribute(field = "app_version", nullable = false)
   public static final DataField APP_VERSION = new DataField("app-version");

   @Attribute(field = "platform", nullable = false)
   public static final DataField PLATFORM = new DataField("platform");

   @Attribute(field = "access_number", nullable = false)
   public static final DataField ACCESS_NUMBER = new DataField("access-number");

   @Attribute(field = "slow_access_number", nullable = false)
   public static final DataField SLOW_ACCESS_NUMBER = new DataField("slow-access-number");

   @Attribute(field = "response_sum_time", nullable = false)
   public static final DataField RESPONSE_SUM_TIME = new DataField("response-sum-time");

   @Attribute(field = "slow_response_sum_time", nullable = false)
   public static final DataField SLOW_RESPONSE_SUM_TIME = new DataField("slow-response-sum-time");

   @Attribute(field = "status", nullable = false)
   public static final DataField STATUS = new DataField("status");

   @Attribute(field = "creation_date", nullable = false)
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "", nullable = false, selectExpr = "sum(access_number)")
   public static final DataField ACCESS_NUMBER_SUM = new DataField("access-number-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(slow_access_number)")
   public static final DataField SLOW_ACCESS_NUMBER_SUM = new DataField("slow-access-number-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(response_sum_time)")
   public static final DataField RESPONSE_SUM_TIME_SUM = new DataField("response-sum-time-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(slow_response_sum_time)")
   public static final DataField SLOW_RESPONSE_SUM_TIME_SUM = new DataField("slow-response-sum-time-sum");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField SPEED_ID = new DataField("speed-id");

   public static final Readset<AppSpeedData> READSET_FULL = new Readset<AppSpeedData>(ID, PERIOD, MINUTE_ORDER, CITY, OPERATOR, NETWORK, APP_VERSION, PLATFORM, ACCESS_NUMBER, SLOW_ACCESS_NUMBER, RESPONSE_SUM_TIME, SLOW_RESPONSE_SUM_TIME, STATUS, CREATION_DATE);

   public static final Readset<AppSpeedData> READSET_COUNT_DATA = new Readset<AppSpeedData>(MINUTE_ORDER, ACCESS_NUMBER_SUM, SLOW_ACCESS_NUMBER_SUM);

   public static final Readset<AppSpeedData> READSET_AVG_DATA = new Readset<AppSpeedData>(MINUTE_ORDER, ACCESS_NUMBER_SUM, SLOW_ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, SLOW_RESPONSE_SUM_TIME_SUM);

   public static final Updateset<AppSpeedData> UPDATESET_FULL = new Updateset<AppSpeedData>(ID, PERIOD, MINUTE_ORDER, CITY, OPERATOR, NETWORK, APP_VERSION, PLATFORM, ACCESS_NUMBER, SLOW_ACCESS_NUMBER, RESPONSE_SUM_TIME, SLOW_RESPONSE_SUM_TIME, STATUS, CREATION_DATE);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", AppSpeedDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", AppSpeedDataEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", AppSpeedDataEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", AppSpeedDataEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BEFORE_PERIOD = new QueryDef("deleteBeforePeriod", AppSpeedDataEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='period'/> <= ${period}");

   public static final QueryDef INSERT_OR_UPDATE = new QueryDef("insertOrUpdate", AppSpeedDataEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>) ON DUPLICATE KEY UPDATE access_number = access_number + ${access-number}, slow_access_number = slow_access_number + ${slow-access-number}, response_sum_time = response_sum_time + ${response-sum-time}, slow_response_sum_time = slow_response_sum_time + ${slow-response-sum-time}");

   public static final QueryDef FIND_DATA_BY_MINUTE = new QueryDef("findDataByMinute", AppSpeedDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='minute-order'/>");

}
