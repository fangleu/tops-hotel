package com.dianping.cat.consumer.metric.model.transform;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_AVG;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_COUNT;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ENDTIME;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_PRODUCT;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_RUN_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_STARTTIME;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_SUM;
import static com.dianping.cat.consumer.metric.model.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Abtest buildAbtest(Attributes attributes) {
      String runId = attributes.getValue(ATTR_RUN_ID);
      String name = attributes.getValue(ATTR_NAME);
      Abtest abtest = new Abtest(runId);

      if (name != null) {
         abtest.setName(name);
      }

      return abtest;
   }

   @Override
   public String buildDomain(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Group buildGroup(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      Group group = new Group(name);

      return group;
   }

   @Override
   public MetricItem buildMetricItem(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String type = attributes.getValue(ATTR_TYPE);
      MetricItem metricItem = new MetricItem(id);

      if (type != null) {
         metricItem.setType(type);
      }

      return metricItem;
   }

   @Override
   public MetricReport buildMetricReport(Attributes attributes) {
      String product = attributes.getValue(ATTR_PRODUCT);
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      MetricReport metricReport = new MetricReport(product);

      if (startTime != null) {
         metricReport.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (endTime != null) {
         metricReport.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return metricReport;
   }

   @Override
   public Point buildPoint(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      Point point = new Point(id == null ? null : convert(Integer.class, id, null));

      if (count != null) {
         point.setCount(convert(Integer.class, count, 0));
      }

      if (sum != null) {
         point.setSum(convert(Double.class, sum, 0.0));
      }

      if (avg != null) {
         point.setAvg(convert(Double.class, avg, 0.0));
      }

      return point;
   }

   @Override
   public Segment buildSegment(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      String sum = attributes.getValue(ATTR_SUM);
      String avg = attributes.getValue(ATTR_AVG);
      Segment segment = new Segment(id == null ? null : convert(Integer.class, id, null));

      if (count != null) {
         segment.setCount(convert(Integer.class, count, 0));
      }

      if (sum != null) {
         segment.setSum(convert(Double.class, sum, 0.0));
      }

      if (avg != null) {
         segment.setAvg(convert(Double.class, avg, 0.0));
      }

      return segment;
   }

   @Override
   public Statistic buildStatistic(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Statistic statistic = new Statistic(id);

      return statistic;
   }

   @Override
   public StatisticsItem buildStatisticsItem(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String count = attributes.getValue(ATTR_COUNT);
      StatisticsItem statisticsItem = new StatisticsItem(id);

      if (count != null) {
         statisticsItem.setCount(convert(Integer.class, count, 0));
      }

      return statisticsItem;
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
