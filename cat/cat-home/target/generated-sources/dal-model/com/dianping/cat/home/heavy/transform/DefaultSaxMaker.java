package com.dianping.cat.home.heavy.transform;

import static com.dianping.cat.home.heavy.Constants.ATTR_COUNT;
import static com.dianping.cat.home.heavy.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.heavy.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.heavy.Constants.ATTR_KEY;
import static com.dianping.cat.home.heavy.Constants.ATTR_LOGVIEW;
import static com.dianping.cat.home.heavy.Constants.ATTR_NAME;
import static com.dianping.cat.home.heavy.Constants.ATTR_STARTTIME;

import org.xml.sax.Attributes;

import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public HeavyCache buildHeavyCache(Attributes attributes) {
      HeavyCache heavyCache = new HeavyCache();

      return heavyCache;
   }

   @Override
   public HeavyCall buildHeavyCall(Attributes attributes) {
      HeavyCall heavyCall = new HeavyCall();

      return heavyCall;
   }

   @Override
   public HeavyReport buildHeavyReport(Attributes attributes) {
      String domain = attributes.getValue(ATTR_DOMAIN);
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      HeavyReport heavyReport = new HeavyReport(domain);

      if (startTime != null) {
         heavyReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         heavyReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return heavyReport;
   }

   @Override
   public HeavySql buildHeavySql(Attributes attributes) {
      HeavySql heavySql = new HeavySql();

      return heavySql;
   }

   @Override
   public Service buildService(Attributes attributes) {
      String domain = attributes.getValue(ATTR_DOMAIN);
      String name = attributes.getValue(ATTR_NAME);
      String logview = attributes.getValue(ATTR_LOGVIEW);
      String count = attributes.getValue(ATTR_COUNT);
      String key = attributes.getValue(ATTR_KEY);
      Service service = new Service(key);

      if (domain != null) {
         service.setDomain(domain);
      }

      if (name != null) {
         service.setName(name);
      }

      if (logview != null) {
         service.setLogview(logview);
      }

      if (count != null) {
         service.setCount(convert(Long.class, count, 0L));
      }

      return service;
   }

   @Override
   public Url buildUrl(Attributes attributes) {
      String domain = attributes.getValue(ATTR_DOMAIN);
      String name = attributes.getValue(ATTR_NAME);
      String logview = attributes.getValue(ATTR_LOGVIEW);
      String count = attributes.getValue(ATTR_COUNT);
      String key = attributes.getValue(ATTR_KEY);
      Url url = new Url(key);

      if (domain != null) {
         url.setDomain(domain);
      }

      if (name != null) {
         url.setName(name);
      }

      if (logview != null) {
         url.setLogview(logview);
      }

      if (count != null) {
         url.setCount(convert(Long.class, count, 0L));
      }

      return url;
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
