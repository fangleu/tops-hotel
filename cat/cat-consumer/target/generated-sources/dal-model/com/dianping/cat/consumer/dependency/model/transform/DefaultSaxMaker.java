package com.dianping.cat.consumer.dependency.model.transform;

import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_AVG;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_DOMAIN;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ENDTIME;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ERROR_COUNT;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_EXCEPTION_COUNT;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_KEY;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_STARTTIME;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_SUM;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_TARGET;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_TOTAL_COUNT;
import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Dependency buildDependency(Attributes attributes) {
      String type = attributes.getValue(ATTR_TYPE);
      String target = attributes.getValue(ATTR_TARGET);
      String totalCount = attributes.getValue(ATTR_TOTAL_COUNT);
      String avg = attributes.getValue(ATTR_AVG);
      String errorCount = attributes.getValue(ATTR_ERROR_COUNT);
      String key = attributes.getValue(ATTR_KEY);
      String sum = attributes.getValue(ATTR_SUM);
      Dependency dependency = new Dependency(key);

      if (type != null) {
         dependency.setType(type);
      }

      if (target != null) {
         dependency.setTarget(target);
      }

      if (totalCount != null) {
         dependency.setTotalCount(convert(Long.class, totalCount, 0L));
      }

      if (avg != null) {
         dependency.setAvg(toNumber(avg, "0.00", 0).doubleValue());
      }

      if (errorCount != null) {
         dependency.setErrorCount(convert(Long.class, errorCount, 0L));
      }

      if (sum != null) {
         dependency.setSum(toNumber(sum, "0.00", 0).doubleValue());
      }

      return dependency;
   }

   @Override
   public DependencyReport buildDependencyReport(Attributes attributes) {
      String domain = attributes.getValue(ATTR_DOMAIN);
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      DependencyReport dependencyReport = new DependencyReport(domain);

      if (startTime != null) {
         dependencyReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         dependencyReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return dependencyReport;
   }

   @Override
   public String buildDomainName(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Index buildIndex(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String totalCount = attributes.getValue(ATTR_TOTAL_COUNT);
      String errorCount = attributes.getValue(ATTR_ERROR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      Index index = new Index(name);

      if (totalCount != null) {
         index.setTotalCount(convert(Long.class, totalCount, 0L));
      }

      if (errorCount != null) {
         index.setErrorCount(convert(Long.class, errorCount, 0L));
      }

      if (sum != null) {
         index.setSum(toNumber(sum, "0.00", 0).doubleValue());
      }

      if (avg != null) {
         index.setAvg(toNumber(avg, "0.00", 0).doubleValue());
      }

      return index;
   }

   @Override
   public Segment buildSegment(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String exceptionCount = attributes.getValue(ATTR_EXCEPTION_COUNT);
      Segment segment = new Segment(id == null ? null : convert(Integer.class, id, null));

      if (exceptionCount != null) {
         segment.setExceptionCount(convert(Integer.class, exceptionCount, null));
      }

      return segment;
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
