package com.dianping.cat.home.dal.report;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "user-define-rule", physicalName = "user_define_rule", alias = "udr")
public class UserDefineRuleEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "content", nullable = false)
   public static final DataField CONTENT = new DataField("content");

   @Attribute(field = "creation_date", nullable = false, insertExpr = "NOW()")
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "", nullable = false, selectExpr = "max(id)")
   public static final DataField MAX_ID = new DataField("max-id");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   public static final Readset<UserDefineRule> READSET_FULL = new Readset<UserDefineRule>(ID, CONTENT, CREATION_DATE);

   public static final Readset<UserDefineRule> READSET_MAXID = new Readset<UserDefineRule>(MAX_ID);

   public static final Updateset<UserDefineRule> UPDATESET_FULL = new Updateset<UserDefineRule>(ID, CONTENT);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", UserDefineRuleEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", UserDefineRuleEntity.class, QueryType.INSERT, 
      "INSERT INTO <TABLE/>(<FIELDS/>) VALUES(<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", UserDefineRuleEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", UserDefineRuleEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_MAX_ID = new QueryDef("findMaxId", UserDefineRuleEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/>");

}
