package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "overload", physicalName = "overload", alias = "ot")
public class OverloadEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "report_id", nullable = false)
   public static final DataField REPORT_ID = new DataField("report-id");

   @Attribute(field = "report_type", nullable = false)
   public static final DataField REPORT_TYPE = new DataField("report-type");

   @Attribute(field = "report_size", nullable = false)
   public static final DataField REPORT_SIZE = new DataField("report-size");

   @Attribute(field = "period", nullable = false)
   public static final DataField PERIOD = new DataField("period");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "", nullable = false, selectExpr = "max(report_id)")
   public static final DataField MAX_ID = new DataField("max-id");

   @Attribute(field = "", nullable = false, selectExpr = "count(*)")
   public static final DataField COUNT = new DataField("count");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_TIME = new DataField("start-time");

   @Variable
   public static final DataField END_TIME = new DataField("end-time");

   @Variable
   public static final DataField TYPE = new DataField("type");

   public static final Readset<Overload> READSET_FULL = new Readset<Overload>(ID, REPORT_ID, REPORT_TYPE, REPORT_SIZE, PERIOD, CREATION_DATE);

   public static final Readset<Overload> READSET_MAXID = new Readset<Overload>(MAX_ID);

   public static final Readset<Overload> READSET_COUNT_NUM = new Readset<Overload>(COUNT);

   public static final Readset<Overload> READSET_ID_SIZE_TYPE = new Readset<Overload>(REPORT_ID, REPORT_SIZE, REPORT_TYPE);

   public static final Updateset<Overload> UPDATESET_FULL = new Updateset<Overload>(ID, REPORT_ID, REPORT_TYPE, REPORT_SIZE, PERIOD);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", OverloadEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", OverloadEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", OverloadEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", OverloadEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_MAX_ID_BY_TYPE = new QueryDef("findMaxIdByType", OverloadEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='report-type'/> = ${type}");

   public static final QueryDef FIND_COUNT = new QueryDef("findCount", OverloadEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/>");

   public static final QueryDef FIND_ID_AND_SIZE_BY_DURATION = new QueryDef("findIdAndSizeByDuration", OverloadEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> >= ${start-time} AND <FIELD name='period'/> <= ${end-time} ORDER BY <FIELD name='period'/> DESC, <FIELD name='report-type'/> ASC");

}
