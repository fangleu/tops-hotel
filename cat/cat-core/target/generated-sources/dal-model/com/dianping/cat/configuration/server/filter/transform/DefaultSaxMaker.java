package com.dianping.cat.configuration.server.filter.transform;

import static com.dianping.cat.configuration.server.filter.Constants.ATTR_ID;
import static com.dianping.cat.configuration.server.filter.Constants.ATTR_TITLE;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.server.filter.entity.CrashLogDomain;
import com.dianping.cat.configuration.server.filter.entity.ServerFilterConfig;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public CrashLogDomain buildCrashLogDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String title = attributes.getValue(ATTR_TITLE);
      CrashLogDomain crashLogDomain = new CrashLogDomain(id);

      if (title != null) {
         crashLogDomain.setTitle(title);
      }

      return crashLogDomain;
   }

   @Override
   public String buildDomain(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ServerFilterConfig buildServerFilterConfig(Attributes attributes) {
      ServerFilterConfig serverFilterConfig = new ServerFilterConfig();

      return serverFilterConfig;
   }

   @Override
   public String buildTransactionName(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String buildTransactionType(Attributes attributes) {
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
