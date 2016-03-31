package com.dianping.cat.consumer.dal;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "business-report", physicalName = "businessReport", alias = "b")
public class BusinessReportEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "type", nullable = false)
   public static final DataField TYPE = new DataField("type");

   @Attribute(field = "name", nullable = false)
   public static final DataField NAME = new DataField("name");

   @Attribute(field = "ip", nullable = false)
   public static final DataField IP = new DataField("ip");

   @Attribute(field = "productLine", nullable = false)
   public static final DataField PRODUCT_LINE = new DataField("product-line");

   @Attribute(field = "period", nullable = false)
   public static final DataField PERIOD = new DataField("period");

   @Attribute(field = "content")
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_DATE = new DataField("start-date");

   @Variable
   public static final DataField END_DATE = new DataField("end-date");

   public static final Readset<BusinessReport> READSET_FULL = new Readset<BusinessReport>(ID, TYPE, NAME, IP, PRODUCT_LINE, PERIOD, CONTENT, CREATION_DATE);

   public static final Updateset<BusinessReport> UPDATESET_FULL = new Updateset<BusinessReport>(ID, TYPE, NAME, IP, PRODUCT_LINE, PERIOD, CONTENT);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", BusinessReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", BusinessReportEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", BusinessReportEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", BusinessReportEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_ALL_BY_PERIOD_PRODUCTLINE_NAME = new QueryDef("findAllByPeriodProductLineName", BusinessReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} AND <FIELD name='product-line'/> = ${product-line}");

}
