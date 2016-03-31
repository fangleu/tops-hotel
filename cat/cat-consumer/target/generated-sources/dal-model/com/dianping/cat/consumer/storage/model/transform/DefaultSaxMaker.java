package com.dianping.cat.consumer.storage.model.transform;

import static com.dianping.cat.consumer.storage.model.Constants.ATTR_AVG;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_COUNT;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ENDTIME;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ERROR;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_LONG_COUNT;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_STARTTIME;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_STATEMENT;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_SUM;
import static com.dianping.cat.consumer.storage.model.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Domain domain = new Domain(id);

      return domain;
   }

   @Override
   public String buildId(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String buildIp(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Machine buildMachine(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Machine machine = new Machine(id);

      return machine;
   }

   @Override
   public String buildOp(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Operation buildOperation(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      String avg = attributes.getValue(ATTR_AVG);
      String sum = attributes.getValue(ATTR_SUM);
      String error = attributes.getValue(ATTR_ERROR);
      String longCount = attributes.getValue(ATTR_LONG_COUNT);
      Operation operation = new Operation(id);

      if (count != null) {
         operation.setCount(convert(Long.class, count, 0L));
      }

      if (avg != null) {
         operation.setAvg(toNumber(avg, "0.0", 0).doubleValue());
      }

      if (sum != null) {
         operation.setSum(toNumber(sum, "0.0", 0).doubleValue());
      }

      if (error != null) {
         operation.setError(convert(Long.class, error, 0L));
      }

      if (longCount != null) {
         operation.setLongCount(convert(Long.class, longCount, 0L));
      }

      return operation;
   }

   @Override
   public Segment buildSegment(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      String avg = attributes.getValue(ATTR_AVG);
      String sum = attributes.getValue(ATTR_SUM);
      String error = attributes.getValue(ATTR_ERROR);
      String longCount = attributes.getValue(ATTR_LONG_COUNT);
      Segment segment = new Segment(id == null ? 0 : convert(Integer.class, id, 0));

      if (count != null) {
         segment.setCount(convert(Long.class, count, 0L));
      }

      if (avg != null) {
         segment.setAvg(toNumber(avg, "0.0", 0).doubleValue());
      }

      if (sum != null) {
         segment.setSum(toNumber(sum, "0.0", 0).doubleValue());
      }

      if (error != null) {
         segment.setError(convert(Long.class, error, 0L));
      }

      if (longCount != null) {
         segment.setLongCount(convert(Long.class, longCount, 0L));
      }

      return segment;
   }

   @Override
   public Sql buildSql(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String statement = attributes.getValue(ATTR_STATEMENT);
      String count = attributes.getValue(ATTR_COUNT);
      Sql sql = new Sql(id);

      if (statement != null) {
         sql.setStatement(statement);
      }

      if (count != null) {
         sql.setCount(convert(Integer.class, count, 0));
      }

      return sql;
   }

   @Override
   public StorageReport buildStorageReport(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String name = attributes.getValue(ATTR_NAME);
      String type = attributes.getValue(ATTR_TYPE);
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      StorageReport storageReport = new StorageReport(id);

      if (name != null) {
         storageReport.setName(name);
      }

      if (type != null) {
         storageReport.setType(type);
      }

      if (startTime != null) {
         storageReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         storageReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return storageReport;
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null) {
         return defaultValue;
      }

      if (type == Boolean.class) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }

   protected java.util.Date toDate(String str, String format, java.util.Date defaultValue) {
      if (str == null || str.length() == 0) {
         return defaultValue;
      }

      try {
         return new java.text.SimpleDateFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse date(%s) in format(%s)!", str, format), e);
      }
   }

   protected Number toNumber(String str, String format, Number defaultValue) {
      if (str == null || str.length() == 0) {
         return defaultValue;
      }

      try {
         return new java.text.DecimalFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse number(%s) in format(%s)!", str, format), e);
      }
   }
}
