package com.dianping.cat.core.dal;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.QueryType;
import org.unidal.dal.jdbc.Readset;
import org.unidal.dal.jdbc.Updateset;
import org.unidal.dal.jdbc.annotation.Attribute;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.annotation.Variable;

@Entity(logicalName = "task", physicalName = "task", alias = "t")
public class TaskEntity {

   @Attribute(field = "id", nullable = false, primaryKey = true, autoIncrement = true)
   public static final DataField ID = new DataField("id");

   @Attribute(field = "producer", nullable = false)
   public static final DataField PRODUCER = new DataField("producer");

   @Attribute(field = "consumer")
   public static final DataField CONSUMER = new DataField("consumer");

   @Attribute(field = "failure_count", nullable = false)
   public static final DataField FAILURE_COUNT = new DataField("failure-count");

   @Attribute(field = "report_name", nullable = false)
   public static final DataField REPORT_NAME = new DataField("report-name");

   @Attribute(field = "report_domain", nullable = false)
   public static final DataField REPORT_DOMAIN = new DataField("report-domain");

   @Attribute(field = "report_period", nullable = false)
   public static final DataField REPORT_PERIOD = new DataField("report-period");

   @Attribute(field = "status", nullable = false)
   public static final DataField STATUS = new DataField("status");

   @Attribute(field = "task_type", nullable = false)
   public static final DataField TASK_TYPE = new DataField("task-type");

   @Attribute(field = "creation_date", nullable = false)
   public static final DataField CREATION_DATE = new DataField("creation-date");

   @Attribute(field = "start_date")
   public static final DataField START_DATE = new DataField("start-date");

   @Attribute(field = "end_date")
   public static final DataField END_DATE = new DataField("end-date");

   @Attribute(field = "", selectExpr = "COUNT(*)")
   public static final DataField COUNT = new DataField("count");

   @Variable
   public static final DataField KEY_ID = new DataField("key-id");

   @Variable
   public static final DataField START_LIMIT = new DataField("start-limit");

   @Variable
   public static final DataField END_LIMIT = new DataField("end-limit");

   public static final Readset<Task> READSET_FULL = new Readset<Task>(ID, PRODUCER, CONSUMER, FAILURE_COUNT, REPORT_NAME, REPORT_DOMAIN, REPORT_PERIOD, STATUS, TASK_TYPE, CREATION_DATE, START_DATE, END_DATE);

   public static final Readset<Task> READSET_REPORT_DOMAIN = new Readset<Task>(REPORT_DOMAIN);

   public static final Readset<Task> READSET_REPORT_NAME = new Readset<Task>(REPORT_NAME);

   public static final Readset<Task> READSET_REPORT_NAME_DOMAIN = new Readset<Task>(REPORT_DOMAIN, REPORT_NAME);

   public static final Readset<Task> READSET_COUNT = new Readset<Task>(COUNT);

   public static final Updateset<Task> UPDATESET_FULL = new Updateset<Task>(ID, PRODUCER, CONSUMER, FAILURE_COUNT, REPORT_NAME, REPORT_DOMAIN, REPORT_PERIOD, STATUS, TASK_TYPE, CREATION_DATE, START_DATE, END_DATE);

   public static final QueryDef FIND_BY_PK = new QueryDef("findByPK", TaskEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef INSERT = new QueryDef("insert", TaskEntity.class, QueryType.INSERT, 
      "INSERT IGNORE INTO <TABLE/> (<FIELDS/>) VALUES (<VALUES/>)");

   public static final QueryDef UPDATE_BY_PK = new QueryDef("updateByPK", TaskEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELDS/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef DELETE_BY_PK = new QueryDef("deleteByPK", TaskEntity.class, QueryType.DELETE, 
      "DELETE FROM <TABLE/> WHERE <FIELD name='id'/> = ${key-id}");

   public static final QueryDef FIND_BY_STATUS_CONSUMER = new QueryDef("findByStatusConsumer", TaskEntity.class, QueryType.SELECT, 
      "SELECT <FIELDS/> FROM <TABLE/> WHERE <FIELD name='status'/> = ${status} <IF type='NOT_NULL' field='consumer'> AND <FIELD name='consumer'/> = ${consumer} </IF> limit 1");

   public static final QueryDef UPDATE_TODO_TO_DOING = new QueryDef("updateTodoToDoing", TaskEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELD name='consumer'/>=${consumer}, <FIELD name='status'/>=2, <FIELD name='start-date'/>=${start-date} WHERE <FIELD name='status'/> = 1 AND <FIELD name='id'/> = ${id};");

   public static final QueryDef UPDATE_DOING_TO_DONE = new QueryDef("updateDoingToDone", TaskEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELD name='status'/>=3, <FIELD name='end-date'/>=${end-date} WHERE <FIELD name='status'/> = 2 AND <FIELD name='id'/> = ${id};");

   public static final QueryDef UPDATE_FAILURE_TO_DONE = new QueryDef("updateFailureToDone", TaskEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELD name='status'/>=3, <FIELD name='end-date'/>=${end-date} WHERE <FIELD name='status'/> =4 AND <FIELD name='id'/> = ${id};");

   public static final QueryDef UPDATE_STATUS_TO_TODO = new QueryDef("updateStatusToTodo", TaskEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELD name='status'/>=1, WHERE <FIELD name='id'/> = ${id};");

   public static final QueryDef UPDATE_DOING_TO_FAIL = new QueryDef("updateDoingToFail", TaskEntity.class, QueryType.UPDATE, 
      "UPDATE <TABLE/> SET <FIELD name='status'/>=4, <FIELD name='end-date'/>=${end-date}, <FIELD name='failure-count'/> = <FIELD name='failure-count'/> + 1 WHERE <FIELD name='status'/> = 2 AND <FIELD name='id'/> = ${id};");

}
