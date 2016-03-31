package com.dianping.cat.configuration.app.speed.transform;

import static com.dianping.cat.configuration.app.speed.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.speed.Constants.ATTR_PAGE;
import static com.dianping.cat.configuration.app.speed.Constants.ATTR_STEP;
import static com.dianping.cat.configuration.app.speed.Constants.ATTR_THRESHOLD;
import static com.dianping.cat.configuration.app.speed.Constants.ATTR_TITLE;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.app.speed.entity.AppSpeedConfig;
import com.dianping.cat.configuration.app.speed.entity.Speed;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AppSpeedConfig buildAppSpeedConfig(Attributes attributes) {
      AppSpeedConfig appSpeedConfig = new AppSpeedConfig();

      return appSpeedConfig;
   }

   @Override
   public Speed buildSpeed(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String page = attributes.getValue(ATTR_PAGE);
      String step = attributes.getValue(ATTR_STEP);
      String title = attributes.getValue(ATTR_TITLE);
      String threshold = attributes.getValue(ATTR_THRESHOLD);
      Speed speed = new Speed(id == null ? 0 : convert(Integer.class, id, 0));

      if (page != null) {
         speed.setPage(page);
      }

      if (step != null) {
         speed.setStep(convert(Integer.class, step, 0));
      }

      if (title != null) {
         speed.setTitle(title);
      }

      if (threshold != null) {
         speed.setThreshold(convert(Integer.class, threshold, 0));
      }

      return speed;
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
