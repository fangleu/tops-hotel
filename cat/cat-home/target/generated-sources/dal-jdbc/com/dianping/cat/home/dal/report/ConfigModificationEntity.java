package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "config-modification", physicalName = "config_modification", alias = "cm")
public class ConfigModificationEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "user_name", nullable = false)
   public static final DataField USER_NAME = new DataField("user-name");

   @Attribute(field = "account_name", nullable = false)
   public static final DataField ACCOUNT_NAME = new DataField("account-name");

   @Attribute(field = "action_name", nullable = false)
   public static final DataField ACTION_NAME = new DataField("action-name");

   @Attribute(field = "argument")
   public static final DataField ARGUMENT = new DataField("argument");

   @Attribute(field = "date", nullable = false)
   public static final DataField DATE = new DataField("date");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   public static final Readset<ConfigModification> READSET_FULL = new Readset<ConfigModification>(ID, USER_NAME, ACCOUNT_NAME, ACTION_NAME, ARGUMENT, DATE, CREATION_DATE);

   public static final Updateset<ConfigModification> UPDATESET_FULL = new Updateset<ConfigModification>(ID, USER_NAME, ACCOUNT_NAME, ACTION_NAME, ARGUMENT, DATE);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", ConfigModificationEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", ConfigModificationEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", ConfigModificationEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", ConfigModificationEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

}
