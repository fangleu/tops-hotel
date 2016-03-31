package com.dianping.cat.app;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "app-connection-data", physicalName = "app_connection_data", alias = "acd2")
public class AppConnectionDataEntity {

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

   @Attribute(field = "connect_type", nullable = false)
   public static final DataField CONNECT_TYPE = new DataField("connect-type");

   @Attribute(field = "code", nullable = false)
   public static final DataField CODE = new DataField("code");

   @Attribute(field = "platform", nullable = false)
   public static final DataField PLATFORM = new DataField("platform");

   @Attribute(field = "access_number", nullable = false)
   public static final DataField ACCESS_NUMBER = new DataField("access-number");

   @Attribute(field = "response_sum_time", nullable = false)
   public static final DataField RESPONSE_SUM_TIME = new DataField("response-sum-time");

   @Attribute(field = "request_package", nullable = false)
   public static final DataField REQUEST_PACKAGE = new DataField("request-package");

   @Attribute(field = "response_package", nullable = false)
   public static final DataField RESPONSE_PACKAGE = new DataField("response-package");

   @Attribute(field = "status", nullable = false)
   public static final DataField STATUS = new DataField("status");

   @Attribute(field = "creation_date", nullable = false)
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "", nullable = false, selectExpr = "sum(access_number)")
   public static final DataField ACCESS_NUMBER_SUM = new DataField("access-number-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(response_sum_time)")
   public static final DataField RESPONSE_SUM_TIME_SUM = new DataField("response-sum-time-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(request_package)")
   public static final DataField REQUEST_PACKAGE_SUM = new DataField("request-package-sum");

   @Attribute(field = "", nullable = false, selectExpr = "sum(response_package)")
   public static final DataField RESPONSE_PACKAGE_SUM = new DataField("response-package-sum");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField COMMAND_ID = new DataField("command-id");

   @Variable
   public static final DataField START_MINUTE_ORDER = new DataField("start-minute-order");

   @Variable
   public static final DataField END_MINUTE_ORDER = new DataField("end-minute-order");

   public static final Readset<AppConnectionData> READSET_FULL = new Readset<AppConnectionData>(ID, PERIOD, MINUTE_ORDER, CITY, OPERATOR, NETWORK, APP_VERSION, CONNECT_TYPE, CODE, PLATFORM, ACCESS_NUMBER, RESPONSE_SUM_TIME, REQUEST_PACKAGE, RESPONSE_PACKAGE, STATUS, CREATION_DATE);

   public static final Readset<AppConnectionData> READSET_COUNT_DATA = new Readset<AppConnectionData>(MINUTE_ORDER, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_AVG_DATA = new Readset<AppConnectionData>(MINUTE_ORDER, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM);

   public static final Readset<AppConnectionData> READSET_SUCCESS_DATA = new Readset<AppConnectionData>(MINUTE_ORDER, CODE, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_CODE_DATA = new Readset<AppConnectionData>(CODE, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_OPERATOR_CODE_DATA = new Readset<AppConnectionData>(CODE, OPERATOR, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_NETWORK_CODE_DATA = new Readset<AppConnectionData>(CODE, NETWORK, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_APP_VERSION_CODE__DATA = new Readset<AppConnectionData>(CODE, APP_VERSION, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_CONNECT_TYPE_CODE_DATA = new Readset<AppConnectionData>(CODE, CONNECT_TYPE, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_PLATFORM_CODE_DATA = new Readset<AppConnectionData>(CODE, PLATFORM, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_CITY_CODE_DATA = new Readset<AppConnectionData>(CODE, CITY, ACCESS_NUMBER_SUM, RESPONSE_SUM_TIME_SUM, REQUEST_PACKAGE_SUM, RESPONSE_PACKAGE_SUM);

   public static final Readset<AppConnectionData> READSET_OPERATOR_DATA = new Readset<AppConnectionData>(OPERATOR, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_NETWORK_DATA = new Readset<AppConnectionData>(NETWORK, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_APP_VERSION_DATA = new Readset<AppConnectionData>(APP_VERSION, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_CONNECT_TYPE_DATA = new Readset<AppConnectionData>(CONNECT_TYPE, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_PLATFORM_DATA = new Readset<AppConnectionData>(PLATFORM, ACCESS_NUMBER_SUM);

   public static final Readset<AppConnectionData> READSET_CITY_DATA = new Readset<AppConnectionData>(CITY, ACCESS_NUMBER_SUM);

   public static final Updateset<AppConnectionData> UPDATESET_FULL = new Updateset<AppConnectionData>(ID, PERIOD, MINUTE_ORDER, CITY, OPERATOR, NETWORK, APP_VERSION, CONNECT_TYPE, CODE, PLATFORM, ACCESS_NUMBER, RESPONSE_SUM_TIME, REQUEST_PACKAGE, RESPONSE_PACKAGE, STATUS, CREATION_DATE);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", AppConnectionDataEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", AppConnectionDataEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", AppConnectionDataEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT_OR_UPDATE = new QueryDef("insertOrUpdate", AppConnectionDataEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>) ON DUPLICATE KEY UPDATE access_number = access_number + ${access-number}, response_sum_time = response_sum_time + ${response-sum-time}, request_package = request_package + ${request-package}, response_package = response_package + ${response-package}");

   public static final QueryDef DELETE_BEFORE_PERIOD = new QueryDef("deleteBeforePeriod", AppConnectionDataEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='period'/> <= ${period}");

   public static final QueryDef FIND_DATA_BY_MINUTE = new QueryDef("findDataByMinute", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='minute-order'/>");

   public static final QueryDef FIND_DATA_BY_MINUTE_CODE = new QueryDef("findDataByMinuteCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='minute-order'/>,<FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_CODE = new QueryDef("findDataByCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_OPERATOR_CODE = new QueryDef("findDataByOperatorCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='operator'/>, <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_NETWORK_CODE = new QueryDef("findDataByNetworkCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='network'/>, <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_APP_VERSION_CODE = new QueryDef("findDataByAppVersionCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='app-version'/>, <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_CONNECT_TYPE_CODE = new QueryDef("findDataByConnectTypeCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='connect-type'/>, <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_PLATFORM_CODE = new QueryDef("findDataByPlatformCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='platform'/>, <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_CITY_CODE = new QueryDef("findDataByCityCode", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='city'/>, <FIELD name='code'/>");

   public static final QueryDef FIND_DATA_BY_OPERATOR = new QueryDef("findDataByOperator", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='operator'/>");

   public static final QueryDef FIND_DATA_BY_NETWORK = new QueryDef("findDataByNetwork", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='network'/>");

   public static final QueryDef FIND_DATA_BY_APP_VERSION = new QueryDef("findDataByAppVersion", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='app-version'/>");

   public static final QueryDef FIND_DATA_BY_CONNECT_TYPE = new QueryDef("findDataByConnectType", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='connect-type'/>");

   public static final QueryDef FIND_DATA_BY_PLATFORM = new QueryDef("findDataByPlatform", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='platform'/>");

   public static final QueryDef FIND_DATA_BY_CITY = new QueryDef("findDataByCity", AppConnectionDataEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} <IF type='NE' field='start-minute-order' value='-1'> AND <FIELD name='minute-order'/> >= ${start-minute-order} </IF> <IF type='NE' field='end-minute-order' value='-1'> AND <FIELD name='minute-order'/> <= ${end-minute-order} </IF> <IF type='NE' field='city' value='-1'> AND <FIELD name='city'/> = ${city} </IF> <IF type='NE' field='operator' value='-1'> AND <FIELD name='operator'/> = ${operator} </IF> <IF type='NE' field='network' value='-1'> AND <FIELD name='network'/> = ${network} </IF> <IF type='NE' field='app-version' value='-1'> AND <FIELD name='app-version'/> = ${app-version} </IF> <IF type='NE' field='connect-type' value='-1'> AND <FIELD name='connect-type'/> = ${connect-type} </IF> <IF type='NE' field='code' value='-1'> AND <FIELD name='code'/> = ${code} </IF> <IF type='NE' field='platform' value='-1'> AND <FIELD name='platform'/> = ${platform} </IF> group by <FIELD name='city'/>");

}
