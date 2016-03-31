package com.dianping.cat.home.alert.summary.transform;

import static com.dianping.cat.home.alert.summary.Constants.ATTR_ALERT_DATE;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_ALERTTIME;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_CONTEXT;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_COUNT;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_METRIC;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_NAME;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Alert buildAlert(Attributes attributes) {
      String alertTime = attributes.getValue(ATTR_ALERTTIME);
      String type = attributes.getValue(ATTR_TYPE);
      String metric = attributes.getValue(ATTR_METRIC);
      String context = attributes.getValue(ATTR_CONTEXT);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String count = attributes.getValue(ATTR_COUNT);
      Alert alert = new Alert();

      if (alertTime != null) {
         alert.setAlertTime(toDate(alertTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (type != null) {
         alert.setType(type);
      }

      if (metric != null) {
         alert.setMetric(metric);
      }

      if (context != null) {
         alert.setContext(context);
      }

      if (domain != null) {
         alert.setDomain(domain);
      }

      if (count != null) {
         alert.setCount(convert(Integer.class, count, null));
      }

      return alert;
   }

   @Override
   public AlertSummary buildAlertSummary(Attributes attributes) {
      String alertDate = attributes.getValue(ATTR_ALERT_DATE);
      String domain = attributes.getValue(ATTR_DOMAIN);
      AlertSummary alertSummary = new AlertSummary();

      if (alertDate != null) {
         alertSummary.setAlertDate(toDate(alertDate, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (domain != null) {
         alertSummary.setDomain(domain);
      }

      return alertSummary;
   }

   @Override
   public Category buildCategory(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      Category category = new Category(name);

      return category;
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
