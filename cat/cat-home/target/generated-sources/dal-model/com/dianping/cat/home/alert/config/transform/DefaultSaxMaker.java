package com.dianping.cat.home.alert.config.transform;

import static com.dianping.cat.home.alert.config.Constants.ATTR_ENABLE;
import static com.dianping.cat.home.alert.config.Constants.ATTR_ID;

import org.xml.sax.Attributes;

import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AlertConfig buildAlertConfig(Attributes attributes) {
      String enable = attributes.getValue(ATTR_ENABLE);
      AlertConfig alertConfig = new AlertConfig();

      if (enable != null) {
         alertConfig.setEnable(convert(Boolean.class, enable, null));
      }

      return alertConfig;
   }

   @Override
   public String buildEmail(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String buildPhone(Attributes attributes) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Receiver buildReceiver(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String enable = attributes.getValue(ATTR_ENABLE);
      Receiver receiver = new Receiver(id);

      if (enable != null) {
         receiver.setEnable(convert(Boolean.class, enable, null));
      }

      return receiver;
   }

   @Override
   public String buildWeixin(Attributes attributes) {
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
