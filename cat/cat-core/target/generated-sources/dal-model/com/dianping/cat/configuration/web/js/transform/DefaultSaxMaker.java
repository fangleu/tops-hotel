package com.dianping.cat.configuration.web.js.transform;

import static com.dianping.cat.configuration.web.js.Constants.ATTR_CREATION_DATE;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_DISPLAY_NAME;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_DOMAIN;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_MAILS;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_PATTERN;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_SAMPLE;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_TYPE;
import static com.dianping.cat.configuration.web.js.Constants.ATTR_WARN;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.web.js.entity.Aggregation;
import com.dianping.cat.configuration.web.js.entity.AggregationRule;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Aggregation buildAggregation(Attributes attributes) {
      Aggregation aggregation = new Aggregation();

      return aggregation;
   }

   @Override
   public AggregationRule buildAggregationRule(Attributes attributes) {
      String type = attributes.getValue(ATTR_TYPE);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String pattern = attributes.getValue(ATTR_PATTERN);
      String sample = attributes.getValue(ATTR_SAMPLE);
      String displayName = attributes.getValue(ATTR_DISPLAY_NAME);
      String creationDate = attributes.getValue(ATTR_CREATION_DATE);
      String warn = attributes.getValue(ATTR_WARN);
      String mails = attributes.getValue(ATTR_MAILS);
      AggregationRule aggregationRule = new AggregationRule(pattern);

      if (type != null) {
         aggregationRule.setType(convert(Integer.class, type, 0));
      }

      if (domain != null) {
         aggregationRule.setDomain(domain);
      }

      if (sample != null) {
         aggregationRule.setSample(sample);
      }

      if (displayName != null) {
         aggregationRule.setDisplayName(displayName);
      }

      if (creationDate != null) {
         aggregationRule.setCreationDate(toDate(creationDate, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (warn != null) {
         aggregationRule.setWarn(convert(Integer.class, warn, 0));
      }

      if (mails != null) {
         aggregationRule.setMails(mails);
      }

      return aggregationRule;
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
