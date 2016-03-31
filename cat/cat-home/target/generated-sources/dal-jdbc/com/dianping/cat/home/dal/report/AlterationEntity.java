package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "alteration", physicalName = "alteration", alias = "a")
public class AlterationEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "type", nullable = false)
   public static final DataField TYPE = new DataField("type");

   @Attribute(field = "title", nullable = false)
   public static final DataField TITLE = new DataField("title");

   @Attribute(field = "domain", nullable = false)
   public static final DataField DOMAIN = new DataField("domain");

   @Attribute(field = "hostname", nullable = false)
   public static final DataField HOSTNAME = new DataField("hostname");

   @Attribute(field = "ip")
   public static final DataField IP = new DataField("ip");

   @Attribute(field = "date", nullable = false)
   public static final DataField DATE = new DataField("date");

   @Attribute(field = "user", nullable = false)
   public static final DataField USER = new DataField("user");

   @Attribute(field = "alt_group")
   public static final DataField ALT_GROUP = new DataField("alt-group");

   @Attribute(field = "content", nullable = false)
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "url")
   public static final DataField URL = new DataField("url");

   @Attribute(field = "status")
   public static final DataField STATUS = new DataField("status");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_TIME = new DataField("start-time");

   @Variable
   public static final DataField END_TIME = new DataField("end-time");

   @Variable
   public static final DataField TYPES = new DataField("types");

   public static final Readset<Alteration> READSET_FULL = new Readset<Alteration>(ID, TYPE, TITLE, DOMAIN, HOSTNAME, IP, DATE, USER, ALT_GROUP, CONTENT, URL, STATUS, CREATION_DATE);

   public static final Updateset<Alteration> UPDATESET_FULL = new Updateset<Alteration>(ID, TYPE, TITLE, DOMAIN, HOSTNAME, IP, DATE, USER, ALT_GROUP, CONTENT, URL, STATUS);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", AlterationEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", AlterationEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", AlterationEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", AlterationEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_BY_TYPE_DRUATION = new QueryDef("findByTypeDruation", AlterationEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='date'/> >= ${start-time} AND <FIELD name='date'/> <= ${end-time} <IF type='NOT_NULL' field='type'> AND <FIELD name='type'/> = ${type} </IF> ORDER BY <FIELD name='date'/> desc");

   public static final QueryDef FIND_BY_DTDH = new QueryDef("findByDtdh", AlterationEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='date'/> >= ${start-time} AND <FIELD name='date'/> <= ${end-time} <IF type='NOT_NULL' field='type'> AND <FIELD name='type'/> = ${type} </IF> <IF type='NOT_NULL' field='domain'> AND <FIELD name='domain'/> = ${domain} </IF> <IF type='NOT_NULL' field='hostname'> AND <FIELD name='hostname'/> = ${hostname} </IF> ORDER BY <FIELD name='date'/> desc");

   public static final QueryDef FIND_BY_DTDH_TYPES = new QueryDef("findByDtdhTypes", AlterationEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='date'/> >= ${start-time} AND <FIELD name='date'/> <= ${end-time} <IF type='NOT_NULL' field='type'> AND <FIELD name='type'/> = ${type} </IF> <IF type='NOT_NULL' field='domain'> AND <FIELD name='domain'/> = ${domain} </IF> <IF type='NOT_NULL' field='hostname'> AND <FIELD name='hostname'/> = ${hostname} </IF> AND <FIELD name='type'/> in <IN>${types}</IN> ORDER BY <FIELD name='date'/> desc");

   public static final QueryDef FIND_BY_DOMAIN_AND_TIME = new QueryDef("findByDomainAndTime", AlterationEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='date'/> >= ${start-time} AND <FIELD name='date'/> <= ${end-time} <IF type='NOT_NULL' field='domain'> AND <FIELD name='domain'/> = ${domain} </IF> ORDER BY <FIELD name='date'/> desc");

}
