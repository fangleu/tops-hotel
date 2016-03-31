package com.dianping.cat.home.rule.transform;

import static com.dianping.cat.home.rule.Constants.ATTR_ALERTTYPE;
import static com.dianping.cat.home.rule.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.rule.Constants.ATTR_ID;
import static com.dianping.cat.home.rule.Constants.ATTR_METRICITEMTEXT;
import static com.dianping.cat.home.rule.Constants.ATTR_MINUTE;
import static com.dianping.cat.home.rule.Constants.ATTR_MONITORAVG;
import static com.dianping.cat.home.rule.Constants.ATTR_MONITORCOUNT;
import static com.dianping.cat.home.rule.Constants.ATTR_MONITORSUM;
import static com.dianping.cat.home.rule.Constants.ATTR_PRODUCTTEXT;
import static com.dianping.cat.home.rule.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.rule.Constants.ATTR_TEXT;
import static com.dianping.cat.home.rule.Constants.ATTR_TITLE;
import static com.dianping.cat.home.rule.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Condition buildCondition(Attributes attributes) {
      String minute = attributes.getValue(ATTR_MINUTE);
      String title = attributes.getValue(ATTR_TITLE);
      String alertType = attributes.getValue(ATTR_ALERTTYPE);
      Condition condition = new Condition();

      if (minute != null) {
         condition.setMinute(convert(Integer.class, minute, null));
      }

      if (title != null) {
         condition.setTitle(title);
      }

      if (alertType != null) {
         condition.setAlertType(alertType);
      }

      return condition;
   }

   @Override
   public Config buildConfig(Attributes attributes) {
      String starttime = attributes.getValue(ATTR_STARTTIME);
      String endtime = attributes.getValue(ATTR_ENDTIME);
      Config config = new Config();

      if (starttime != null) {
         config.setStarttime(starttime);
      }

      if (endtime != null) {
         config.setEndtime(endtime);
      }

      return config;
   }

   @Override
   public MetricItem buildMetricItem(Attributes attributes) {
      String monitorCount = attributes.getValue(ATTR_MONITORCOUNT);
      String monitorSum = attributes.getValue(ATTR_MONITORSUM);
      String monitorAvg = attributes.getValue(ATTR_MONITORAVG);
      String productText = attributes.getValue(ATTR_PRODUCTTEXT);
      String metricItemText = attributes.getValue(ATTR_METRICITEMTEXT);
      MetricItem metricItem = new MetricItem();

      if (monitorCount != null) {
         metricItem.setMonitorCount(convert(Boolean.class, monitorCount, null));
      }

      if (monitorSum != null) {
         metricItem.setMonitorSum(convert(Boolean.class, monitorSum, null));
      }

      if (monitorAvg != null) {
         metricItem.setMonitorAvg(convert(Boolean.class, monitorAvg, null));
      }

      if (productText != null) {
         metricItem.setProductText(productText);
      }

      if (metricItemText != null) {
         metricItem.setMetricItemText(metricItemText);
      }

      return metricItem;
   }

   @Override
   public MonitorRules buildMonitorRules(Attributes attributes) {
      MonitorRules monitorRules = new MonitorRules();

      return monitorRules;
   }

   @Override
   public Rule buildRule(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Rule rule = new Rule(id);

      return rule;
   }

   @Override
   public SubCondition buildSubCondition(Attributes attributes) {
      String type = attributes.getValue(ATTR_TYPE);
      String text = attributes.getValue(ATTR_TEXT);
      SubCondition subCondition = new SubCondition();

      if (type != null) {
         subCondition.setType(type);
      }

      if (text != null) {
         subCondition.setText(text);
      }

      return subCondition;
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
