package com.dianping.cat.core.config;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "config", physicalName = "config", alias = "c")
public class ConfigEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "name", nullable = false)
   public static final DataField NAME = new DataField("name");

   @Attribute(field = "content")
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "modify_date", nullable = false, insertExpr = "NOW()", updateExpr = "NOW()")
   public static final DataField MODIFY_DATE = new DataField("modify-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   public static final Readset<Config> READSET_FULL = new Readset<Config>(ID, NAME, CONTENT, CREATION_DATE, MODIFY_DATE);

   public static final Updateset<Config> UPDATESET_FULL = new Updateset<Config>(ID, NAME, CONTENT, MODIFY_DATE);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", ConfigEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", ConfigEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", ConfigEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", ConfigEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_BY_NAME = new QueryDef("findByName", ConfigEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='name'/> = ${name}");

   public static final QueryDef FIND_ALL_CONFIG = new QueryDef("findAllConfig", ConfigEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/>");

}
