package com.dianping.cat.home.alert.policy.transform;

import static com.dianping.cat.home.alert.policy.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.policy.Constants.ATTR_RECOVERMINUTE;
import static com.dianping.cat.home.alert.policy.Constants.ATTR_SEND;
import static com.dianping.cat.home.alert.policy.Constants.ATTR_SUSPENDMINUTE;

import org.xml.sax.Attributes;

import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AlertPolicy buildAlertPolicy(Attributes attributes) {
      AlertPolicy alertPolicy = new AlertPolicy();

      return alertPolicy;
   }

   @Override
   public Group buildGroup(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Group group = new Group(id);

      return group;
   }

   @Override
   public Level buildLevel(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String send = attributes.getValue(ATTR_SEND);
      String suspendMinute = attributes.getValue(ATTR_SUSPENDMINUTE);
      String recoverMinute = attributes.getValue(ATTR_RECOVERMINUTE);
      Level level = new Level(id);

      if (send != null) {
         level.setSend(send);
      }

      if (suspendMinute != null) {
         level.setSuspendMinute(convert(Integer.class, suspendMinute, null));
      }

      if (recoverMinute != null) {
         level.setRecoverMinute(convert(Integer.class, recoverMinute, null));
      }

      return level;
   }

   @Override
   public Type buildType(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Type type = new Type(id);

      return type;
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
