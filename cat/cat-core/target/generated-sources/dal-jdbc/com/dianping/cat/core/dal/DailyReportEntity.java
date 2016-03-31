package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "dailyreport", physicalName = "dailyreport", alias = "dr")
public class DailyReportEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "name", nullable = false)
   public static final DataField NAME = new DataField("name");

   @Attribute(field = "ip", nullable = false)
   public static final DataField IP = new DataField("ip");

   @Attribute(field = "domain", nullable = false)
   public static final DataField DOMAIN = new DataField("domain");

   @Attribute(field = "period", nullable = false)
   public static final DataField PERIOD = new DataField("period");

   @Attribute(field = "type", nullable = false)
   public static final DataField TYPE = new DataField("type");

   @Attribute(field = "creation_date", nullable = false)
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "", selectExpr = "COUNT(*)")
   public static final DataField COUNT = new DataField("count");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_DATE = new DataField("start-date");

   @Variable
   public static final DataField END_DATE = new DataField("end-date");

   public static final Readset<DailyReport> READSET_FULL = new Readset<DailyReport>(ID, NAME, IP, DOMAIN, PERIOD, TYPE, CREATION_DATE);

   public static final Readset<DailyReport> READSET_DOMAIN_NAME = new Readset<DailyReport>(DOMAIN, NAME);

   public static final Readset<DailyReport> READSET_COUNT = new Readset<DailyReport>(COUNT);

   public static final Updateset<DailyReport> UPDATESET_FULL = new Updateset<DailyReport>(ID, NAME, IP, DOMAIN, PERIOD, TYPE, CREATION_DATE);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", DailyReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", DailyReportEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/> (<FIELDS/>) VALUES (<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", DailyReportEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", DailyReportEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_BY_DOMAIN_NAME_PERIOD = new QueryDef("findByDomainNamePeriod", DailyReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} AND <FIELD name='domain'/> = ${domain} AND <FIELD name='name'/> = ${name} AND binary(<FIELD name='domain'/>) = binary(${domain})");

}
