package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "report", physicalName = "report", alias = "r")
public class HourlyReportEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "type", nullable = false)
   public static final DataField TYPE = new DataField("type");

   @Attribute(field = "name", nullable = false)
   public static final DataField NAME = new DataField("name");

   @Attribute(field = "ip")
   public static final DataField IP = new DataField("ip");

   @Attribute(field = "domain", nullable = false)
   public static final DataField DOMAIN = new DataField("domain");

   @Attribute(field = "period", nullable = false)
   public static final DataField PERIOD = new DataField("period");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_DATE = new DataField("start-date");

   @Variable
   public static final DataField END_DATE = new DataField("end-date");

   public static final Readset<HourlyReport> READSET_FULL = new Readset<HourlyReport>(ID, TYPE, NAME, IP, DOMAIN, PERIOD, CREATION_DATE);

   public static final Readset<HourlyReport> READSET_DOMAIN_NAME = new Readset<HourlyReport>(DOMAIN, NAME);

   public static final Updateset<HourlyReport> UPDATESET_FULL = new Updateset<HourlyReport>(ID, TYPE, NAME, IP, DOMAIN, PERIOD);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", HourlyReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", HourlyReportEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", HourlyReportEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", HourlyReportEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_ALL_BY_DOMAIN_NAME_PERIOD = new QueryDef("findAllByDomainNamePeriod", HourlyReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} AND <FIELD name='domain'/> = ${domain} AND <FIELD name='name'/> = ${name} AND binary(<FIELD name='domain'/>) = binary(${domain})");

   public static final QueryDef FIND_ALL_BY_PERIOD_NAME = new QueryDef("findAllByPeriodName", HourlyReportEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='period'/> = ${period} AND <FIELD name='name'/> = ${name}");

}
