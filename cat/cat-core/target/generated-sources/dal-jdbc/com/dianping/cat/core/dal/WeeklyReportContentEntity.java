package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "weekly-report-content", physicalName = "weekly_report_content", alias = "wrc")
public class WeeklyReportContentEntity {

   @Attribute(field = "report_id", nullable = false, primaryKey = true)
   public static final DataField REPORT_ID = new DataField("report-id");

   @Attribute(field = "content", nullable = false)
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "", nullable = false, selectExpr = "length(content)/1024/1024")
   public static final DataField CONTENT_LENGTH = new DataField("content-length");

   @Variable
   public static final DataField KEY_REPORT_ID = new DataField("key-report-id");

   @Variable
   public static final DataField CAPACITY = new DataField("capacity");

   @Variable
   public static final DataField START_ID = new DataField("start-id");

   public static final Readset<WeeklyReportContent> READSET_FULL = new Readset<WeeklyReportContent>(REPORT_ID, CONTENT, CREATION_DATE);

   public static final Readset<WeeklyReportContent> READSET_LENGTH = new Readset<WeeklyReportContent>(REPORT_ID, CONTENT_LENGTH);

   public static final Updateset<WeeklyReportContent> UPDATESET_FULL = new Updateset<WeeklyReportContent>(REPORT_ID, CONTENT);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", WeeklyReportContentEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='report-id'/> = ${key-report-id}");

   public static final QueryDef INSERT = new QueryDef("insert", WeeklyReportContentEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", WeeklyReportContentEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='report-id'/> = ${key-report-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", WeeklyReportContentEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='report-id'/> = ${key-report-id}");

   public static final QueryDef FIND_OVERLOAD_REPORT = new QueryDef("findOverloadReport", WeeklyReportContentEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='report-id'/> > ${start-id} ORDER BY <FIELD name='report-id'/> asc limit 1000");

}
