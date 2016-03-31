package com.dianping.cat.home.utilization.transform;

import static com.dianping.cat.home.utilization.Constants.ATTR_AVG;
import static com.dianping.cat.home.utilization.Constants.ATTR_AVG_MAX;
import static com.dianping.cat.home.utilization.Constants.ATTR_AVG95;
import static com.dianping.cat.home.utilization.Constants.ATTR_CMDB_ID;
import static com.dianping.cat.home.utilization.Constants.ATTR_COUNT;
import static com.dianping.cat.home.utilization.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.utilization.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.utilization.Constants.ATTR_FAILURE_COUNT;
import static com.dianping.cat.home.utilization.Constants.ATTR_FAILURE_PERCENT;
import static com.dianping.cat.home.utilization.Constants.ATTR_ID;
import static com.dianping.cat.home.utilization.Constants.ATTR_MACHINE_NUMBER;
import static com.dianping.cat.home.utilization.Constants.ATTR_MAXQPS;
import static com.dianping.cat.home.utilization.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.utilization.Constants.ATTR_SUM;

import org.xml.sax.Attributes;

import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public ApplicationState buildApplicationState(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String failureCount = attributes.getValue(ATTR_FAILURE_COUNT);
      String failurePercent = attributes.getValue(ATTR_FAILURE_PERCENT);
      String count = attributes.getValue(ATTR_COUNT);
      String maxQps = attributes.getValue(ATTR_MAXQPS);
      String avg = attributes.getValue(ATTR_AVG);
      String sum = attributes.getValue(ATTR_SUM);
      String avg95 = attributes.getValue(ATTR_AVG95);
      ApplicationState applicationState = new ApplicationState(id);

      if (failureCount != null) {
         applicationState.setFailureCount(convert(Long.class, failureCount, 0L));
      }

      if (failurePercent != null) {
         applicationState.setFailurePercent(toNumber(failurePercent, "0.000000", 0).doubleValue());
      }

      if (count != null) {
         applicationState.setCount(convert(Long.class, count, 0L));
      }

      if (maxQps != null) {
         applicationState.setMaxQps(toNumber(maxQps, "0.0000", 0).doubleValue());
      }

      if (avg != null) {
         applicationState.setAvg(toNumber(avg, "0.0000", 0).doubleValue());
      }

      if (sum != null) {
         applicationState.setSum(toNumber(sum, "0.0000", 0).doubleValue());
      }

      if (avg95 != null) {
         applicationState.setAvg95(toNumber(avg95, "0.0000", 0).doubleValue());
      }

      return applicationState;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String machineNumber = attributes.getValue(ATTR_MACHINE_NUMBER);
      String cmdbId = attributes.getValue(ATTR_CMDB_ID);
      Domain domain = new Domain(id);

      if (machineNumber != null) {
         domain.setMachineNumber(convert(Integer.class, machineNumber, 0));
      }

      if (cmdbId != null) {
         domain.setCmdbId(cmdbId);
      }

      return domain;
   }

   @Override
   public MachineState buildMachineState(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      String avgMax = attributes.getValue(ATTR_AVG_MAX);
      MachineState machineState = new MachineState(id);

      if (count != null) {
         machineState.setCount(convert(Long.class, count, 0L));
      }

      if (sum != null) {
         machineState.setSum(toNumber(sum, "0.0000", 0).doubleValue());
      }

      if (avg != null) {
         machineState.setAvg(toNumber(avg, "0.0000", 0).doubleValue());
      }

      if (avgMax != null) {
         machineState.setAvgMax(toNumber(avgMax, "0.0000", 0).doubleValue());
      }

      return machineState;
   }

   @Override
   public UtilizationReport buildUtilizationReport(Attributes attributes) {
      String domain = attributes.getValue(ATTR_DOMAIN);
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      UtilizationReport utilizationReport = new UtilizationReport(domain);

      if (startTime != null) {
         utilizationReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         utilizationReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return utilizationReport;
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
