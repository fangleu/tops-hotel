package com.dianping.cat.home.bug.config.transform;

import static com.dianping.cat.home.bug.config.Constants.ATTR_ADDITIVITY;
import static com.dianping.cat.home.bug.config.Constants.ATTR_ID;

import org.xml.sax.Attributes;

import com.dianping.cat.home.bug.config.entity.BugConfig;
import com.dianping.cat.home.bug.config.entity.Domain;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public BugConfig buildBugConfig(Attributes attributes) {
      BugConfig bugConfig = new BugConfig();

      return bugConfig;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String additivity = attributes.getValue(ATTR_ADDITIVITY);
      Domain domain = new Domain(id);

      if (additivity != null) {
         domain.setAdditivity(convert(Boolean.class, additivity, null));
      }

      return domain;
   }

   @Override
   public String buildException(Attributes attributes) {
      throw new UnsupportedOperationException();
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
