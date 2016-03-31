package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "alert", physicalName = "alert", alias = "al")
public class AlertEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "domain", nullable = false)
   public static final DataField DOMAIN = new DataField("domain");

   @Attribute(field = "alert_time", nullable = false)
   public static final DataField ALERT_TIME = new DataField("alert-time");

   @Attribute(field = "category", nullable = false)
   public static final DataField CATEGORY = new DataField("category");

   @Attribute(field = "type", nullable = false)
   public static final DataField TYPE = new DataField("type");

   @Attribute(field = "content", nullable = false)
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "metric", nullable = false)
   public static final DataField METRIC = new DataField("metric");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_TIME = new DataField("start-time");

   @Variable
   public static final DataField END_TIME = new DataField("end-time");

   @Variable
   public static final DataField CATEGORIES = new DataField("categories");

   public static final Readset<Alert> READSET_FULL = new Readset<Alert>(ID, DOMAIN, ALERT_TIME, CATEGORY, TYPE, CONTENT, METRIC, CREATION_DATE);

   public static final Updateset<Alert> UPDATESET_FULL = new Updateset<Alert>(ID, DOMAIN, ALERT_TIME, CATEGORY, TYPE, CONTENT, METRIC);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", AlertEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", AlertEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", AlertEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", AlertEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef QUERY_ALERTS_BY_TIME_DOMAIN = new QueryDef("queryAlertsByTimeDomain", AlertEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='alert-time'/> >= ${start-time} AND <FIELD name='alert-time'/> <= ${end-time} <IF type='NOT_NULL' field='domain'> AND <FIELD name='domain'/> = ${domain} </IF> ORDER BY <FIELD name='alert-time'/> desc");

   public static final QueryDef QUERY_ALERTS_BY_TIME_DOMAIN_CATEGORIES = new QueryDef("queryAlertsByTimeDomainCategories", AlertEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='alert-time'/> >= ${start-time} AND <FIELD name='alert-time'/> <= ${end-time} <IF type='NOT_NULL' field='domain'> AND <FIELD name='domain'/> = ${domain} </IF> AND <FIELD name='category'/> in <IN>${categories}</IN> ORDER BY <FIELD name='alert-time'/> desc");

   public static final QueryDef QUERY_ALERTS_BY_TIME_CATEGORY_DOMAIN = new QueryDef("queryAlertsByTimeCategoryDomain", AlertEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='alert-time'/> >= ${start-time} AND <FIELD name='alert-time'/> <= ${end-time} <IF type='NOT_NULL' field='category'> AND <FIELD name='category'/> = ${category} </IF> <IF type='NOT_NULL' field='domain'> AND <FIELD name='domain'/> = ${domain} </IF> ORDER BY <FIELD name='alert-time'/> asc");

   public static final QueryDef QUERY_ALERTS_BY_TIME_CATEGORY = new QueryDef("queryAlertsByTimeCategory", AlertEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='alert-time'/> >= ${start-time} AND <FIELD name='alert-time'/> <= ${end-time} <IF type='NOT_NULL' field='category'> AND <FIELD name='category'/> = ${category} </IF> ORDER BY <FIELD name='alert-time'/> asc");

}
