package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "alert-summary", physicalName = "alert_summary", alias = "as")
public class AlertSummaryEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "domain", nullable = false)
   public static final DataField DOMAIN = new DataField("domain");

   @Attribute(field = "alert_time", nullable = false)
   public static final DataField ALERT_TIME = new DataField("alert-time");

   @Attribute(field = "content", nullable = false)
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   public static final Readset<AlertSummary> READSET_FULL = new Readset<AlertSummary>(ID, DOMAIN, ALERT_TIME, CONTENT, CREATION_DATE);

   public static final Updateset<AlertSummary> UPDATESET_FULL = new Updateset<AlertSummary>(ID, DOMAIN, ALERT_TIME, CONTENT);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", AlertSummaryEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", AlertSummaryEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", AlertSummaryEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", AlertSummaryEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

}
