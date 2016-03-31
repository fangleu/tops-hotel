package com.dianping.cat.configuration.web.url.transform;

import static com.dianping.cat.configuration.web.url.Constants.ATTR_DOMAIN;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_GROUP;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_ID;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_PATTERN;
import static com.dianping.cat.configuration.web.url.Constants.ATTR_STATUS;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Code buildCode(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String name = attributes.getValue(ATTR_NAME);
      String status = attributes.getValue(ATTR_STATUS);
      Code code = new Code(id == null ? null : convert(Integer.class, id, null));

      if (name != null) {
         code.setName(name);
      }

      if (status != null) {
         code.setStatus(convert(Integer.class, status, null));
      }

      return code;
   }

   @Override
   public PatternItem buildPatternItem(Attributes attributes) {
      String group = attributes.getValue(ATTR_GROUP);
      String name = attributes.getValue(ATTR_NAME);
      String pattern = attributes.getValue(ATTR_PATTERN);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String id = attributes.getValue(ATTR_ID);
      PatternItem patternItem = new PatternItem(name);

      if (group != null) {
         patternItem.setGroup(group);
      }

      if (pattern != null) {
         patternItem.setPattern(pattern);
      }

      if (domain != null) {
         patternItem.setDomain(domain);
      }

      if (id != null) {
         patternItem.setId(convert(Integer.class, id, 0));
      }

      return patternItem;
   }

   @Override
   public UrlPattern buildUrlPattern(Attributes attributes) {
      UrlPattern urlPattern = new UrlPattern();

      return urlPattern;
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
