package com.dianping.cat.home.bug.transform;

import static com.dianping.cat.home.bug.Constants.ATTR_COUNT;
import static com.dianping.cat.home.bug.Constants.ATTR_DEPARTMENT;
import static com.dianping.cat.home.bug.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.bug.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.bug.Constants.ATTR_ID;
import static com.dianping.cat.home.bug.Constants.ATTR_PRODUCTLINE;
import static com.dianping.cat.home.bug.Constants.ATTR_STARTTIME;

import org.xml.sax.Attributes;

import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public BugReport buildBugReport(Attributes attributes) {
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      BugReport bugReport = new BugReport(domain);

      if (startTime != null) {
         bugReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         bugReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return bugReport;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String department = attributes.getValue(ATTR_DEPARTMENT);
      String productLine = attributes.getValue(ATTR_PRODUCTLINE);
      Domain domain = new Domain(id);

      if (department != null) {
         domain.setDepartment(department);
      }

      if (productLine != null) {
         domain.setProductLine(productLine);
      }

      return domain;
   }

   @Override
   public ExceptionItem buildExceptionItem(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      ExceptionItem exceptionItem = new ExceptionItem(id);

      if (count != null) {
         exceptionItem.setCount(convert(Integer.class, count, 0));
      }

      return exceptionItem;
   }

   @Override
   public String buildMessage(Attributes attributes) {
      throw new UnsupportedOperationException();
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
