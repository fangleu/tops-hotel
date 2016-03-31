package com.dianping.cat.configuration.server.black.transform;

import static com.dianping.cat.configuration.server.black.Constants.ATTR_NAME;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.server.black.entity.BlackList;
import com.dianping.cat.configuration.server.black.entity.Property;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public BlackList buildBlackList(Attributes attributes) {
      BlackList blackList = new BlackList();

      return blackList;
   }

   @Override
   public String buildDomain(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String buildIp(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Property buildProperty(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      Property property = new Property(name);

      return property;
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
