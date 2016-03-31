package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "baseline", physicalName = "baseline", alias = "b")
public class BaselineEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "report_name")
   public static final DataField REPORT_NAME = new DataField("report-name");

   @Attribute(field = "index_key")
   public static final DataField INDEX_KEY = new DataField("index-key");

   @Attribute(field = "report_period")
   public static final DataField REPORT_PERIOD = new DataField("report-period");

   @Attribute(field = "data")
   public static final DataField DATA = new DataField("data");

   @Attribute(field = "creation_date", insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField DATAINDOUBLEARRAY = new DataField("dataInDoubleArray");

   public static final Readset<Baseline> READSET_FULL = new Readset<Baseline>(ID, REPORT_NAME, INDEX_KEY, REPORT_PERIOD, DATA, CREATION_DATE);

   public static final Updateset<Baseline> UPDATESET_FULL = new Updateset<Baseline>(ID, REPORT_NAME, INDEX_KEY, REPORT_PERIOD, DATA);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", BaselineEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", BaselineEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", BaselineEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", BaselineEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_BY_REPORT_NAME_KEY_TIME = new QueryDef("findByReportNameKeyTime", BaselineEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='report-period'/> = ${report-period} AND <FIELD name='report-name'/> = ${report-name} AND <FIELD name='index-key'/> = ${index-key}");

}
