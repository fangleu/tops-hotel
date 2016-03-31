package com.dianping.cat.app;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "web-api-data", physicalName = "web_api_data", alias = "wad")
public class WebApiDataEntity {

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

   @Attribute(field = "code", nullable = false)
   public static final DataField CODE = new DataField("code");

   @Attribute(field = "access_number", nullable = false)
   public static final DataField ACCESS_NUMBER = new DataField("access-number");

   @Attribute(field = "response_sum_time", nullable = false)
   public static final DataField RESPONSE_SUM_TIME = new DataField("response-sum-time");

   @Attribute(field = "updatetime", nullable = false)
   public static final DataField UPDATETIME = new DataField("updatetime");

   @Attribute(field = "", nullable = false, selectExpr = "sum(access_number)")
   public static final DataField ACCESS_NUMBER_SUM = new DataField("access-number-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(response_sum_time)")
   public static final DataField RESPONSE_SUM_TIME_SUM = new DataField("response-sum-time-sum");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField API_ID = new DataField("api-id");

   @Variable
   public static final DataField START_MINUTE_ORDER = new DataField("start-minute-order");

   @Variable
   public static final DataField END_MINUTE_ORDER = new DataField("end-minute-order");

   public static final Readset<WebApiData> READSET_FULL = new Readset<WebApiData>(ID, PERIOD, MINUTE_ORDER, CITY, OPERATOR, CODE, ACCESS_NUMBER, RESPONSE_SUM_TIME, UPDATETIME);

   public static final Readset<WebApiData> READSET_COUNT_DATA = new Readset<WebApiData>(MINUTE_ORDER, ACCESS_NUMBER_SUM);

   public static final Readset<WebApiData> READSET_AVG_DATA = new Readset<WebApiData>(MINUTE_ORDER, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM);

   public static final Readset<WebApiData> READSET_SUCCESS_DATA = new Readset<WebApiData>(MINUTE_ORDER, CODE, ACCESS_NUMBER_SUM);

   public static final Readset<WebApiData> READSET_CODE_DATA = new Readset<WebApiData>(CODE, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM);

   public static final Readset<WebApiData> READSET_OPERATOR_DATA = new Readset<WebApiData>(OPERATOR, ACCESS_NUMBER_SUM);

   public static final Readset<WebApiData> READSET_CITY_DATA = new Readset<WebApiData>(CITY, ACCESS_NUMBER_SUM);

   public static final Updateset<WebApiData> UPDATESET_FULL = new Updateset<WebApiData>(ID, PERIOD, MINUTE_ORDER, CITY, OPERATOR, CODE, ACCESS_NUMBER, RESPONSE_SUM_TIME, UPDATETIME);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", WebApiDataEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", WebApiDataEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", WebApiDataEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT_OR_UPDATE = new QueryDef("insertOrUpdate", WebApiDataEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>) ON DUPLICATE KEY UPDATE access_number = access_number + ${access-number}");

   public static final QueryDef DELETE_BEFORE_PERIOD = new QueryDef("deleteBeforePeriod", WebApiDataEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='period'/> <= ${period}");

   public static final QueryDef FIND_DATA_BY_MINUTE = new QueryDef("findDataByMinute", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> group by <FIELD name='minute-order'/>");

   public static final QueryDef FIND_DAILY_DATA_BY_CODE = new QueryDef("findDailyDataByCode", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} group by <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_MINUTE_CODE = new QueryDef("findDataByMinuteCode", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> group by <FIELD name='minute-order'/>,<FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_CODE = new QueryDef("findDataByCode", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> group by <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_OPERATOR = new QueryDef("findDataByOperator", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> group by <FIELD name='operator'/>");

   public static final QueryDef FIND_DATA_BY_CITY = new QueryDef("findDataByCity", WebApiDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> group by <FIELD name='city'/>");

}
