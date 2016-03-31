package com.dianping.cat.home.service.transform;

import static com.dianping.cat.home.service.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.service.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.service.Constants.ATTR_ID;
import static com.dianping.cat.home.service.Constants.ATTR_STARTTIME;

import org.xml.sax.Attributes;

import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Domain domain = new Domain(id);

      return domain;
   }

   @Override
   public ServiceReport buildServiceReport(Attributes attributes) {
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      ServiceReport serviceReport = new ServiceReport(domain);

      if (startTime != null) {
         serviceReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         serviceReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return serviceReport;
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
