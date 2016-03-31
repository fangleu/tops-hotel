package com.dianping.cat.consumer.metric.config.transform;

import static com.dianping.cat.consumer.metric.config.Constants.ATTR_ALARM;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_DOMAIN;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_METRIC_KEY;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_AVG;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_COUNT;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_DASHBOARD_ORDER;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_SHOW_SUM;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_TITLE;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_TYPE;
import static com.dianping.cat.consumer.metric.config.Constants.ATTR_VIEW_ORDER;

import org.xml.sax.Attributes;

import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public MetricConfig buildMetricConfig(Attributes attributes) {
      MetricConfig metricConfig = new MetricConfig();

      return metricConfig;
   }

   @Override
   public MetricItemConfig buildMetricItemConfig(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String type = attributes.getValue(ATTR_TYPE);
      String viewOrder = attributes.getValue(ATTR_VIEW_ORDER);
      String metricKey = attributes.getValue(ATTR_METRIC_KEY);
      String title = attributes.getValue(ATTR_TITLE);
      String showCount = attributes.getValue(ATTR_SHOW_COUNT);
      String showAvg = attributes.getValue(ATTR_SHOW_AVG);
      String showSum = attributes.getValue(ATTR_SHOW_SUM);
      String showDashboardOrder = attributes.getValue(ATTR_SHOW_DASHBOARD_ORDER);
      String alarm = attributes.getValue(ATTR_ALARM);
      MetricItemConfig metricItemConfig = new MetricItemConfig(id);

      if (domain != null) {
         metricItemConfig.setDomain(domain);
      }

      if (type != null) {
         metricItemConfig.setType(type);
      }

      if (viewOrder != null) {
         metricItemConfig.setViewOrder(convert(Double.class, viewOrder, 0.0));
      }

      if (metricKey != null) {
         metricItemConfig.setMetricKey(metricKey);
      }

      if (title != null) {
         metricItemConfig.setTitle(title);
      }

      if (showCount != null) {
         metricItemConfig.setShowCount(convert(Boolean.class, showCount, false));
      }

      if (showAvg != null) {
         metricItemConfig.setShowAvg(convert(Boolean.class, showAvg, false));
      }

      if (showSum != null) {
         metricItemConfig.setShowSum(convert(Boolean.class, showSum, false));
      }

      if (showDashboardOrder != null) {
         metricItemConfig.setShowDashboardOrder(convert(Double.class, showDashboardOrder, 0.0));
      }

      if (alarm != null) {
         metricItemConfig.setAlarm(convert(Boolean.class, alarm, false));
      }

      return metricItemConfig;
   }

   @Override
   public Tag buildTag(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String type = attributes.getValue(ATTR_TYPE);
      Tag tag = new Tag();

      if (name != null) {
         tag.setName(name);
      }

      if (type != null) {
         tag.setType(type);
      }

      return tag;
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
}
