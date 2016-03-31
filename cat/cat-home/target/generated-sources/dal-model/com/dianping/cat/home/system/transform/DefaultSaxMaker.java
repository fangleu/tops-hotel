package com.dianping.cat.home.system.transform;

import static com.dianping.cat.home.system.Constants.ATTR_AVG;
import static com.dianping.cat.home.system.Constants.ATTR_COUNT;
import static com.dianping.cat.home.system.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.system.Constants.ATTR_ID;
import static com.dianping.cat.home.system.Constants.ATTR_IP;
import static com.dianping.cat.home.system.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.system.Constants.ATTR_SUM;

import org.xml.sax.Attributes;

import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Day buildDay(Attributes attributes) {
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      Day day = new Day();

      if (count != null) {
         day.setCount(convert(Long.class, count, 0L));
      }

      if (sum != null) {
         day.setSum(convert(Double.class, sum, 0.0));
      }

      if (avg != null) {
         day.setAvg(convert(Double.class, avg, 0.0));
      }

      return day;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Domain domain = new Domain(id);

      return domain;
   }

   @Override
   public Entity buildEntity(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Entity entity = new Entity(id);

      return entity;
   }

   @Override
   public Machine buildMachine(Attributes attributes) {
      String ip = attributes.getValue(ATTR_IP);
      Machine machine = new Machine(ip);

      return machine;
   }

   @Override
   public Rush buildRush(Attributes attributes) {
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      Rush rush = new Rush();

      if (count != null) {
         rush.setCount(convert(Long.class, count, 0L));
      }

      if (sum != null) {
         rush.setSum(convert(Double.class, sum, 0.0));
      }

      if (avg != null) {
         rush.setAvg(convert(Double.class, avg, 0.0));
      }

      return rush;
   }

   @Override
   public SystemReport buildSystemReport(Attributes attributes) {
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      SystemReport systemReport = new SystemReport();

      if (startTime != null) {
         systemReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         systemReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return systemReport;
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
}
