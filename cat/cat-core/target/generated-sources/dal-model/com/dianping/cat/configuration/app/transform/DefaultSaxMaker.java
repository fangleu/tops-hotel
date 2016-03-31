package com.dianping.cat.configuration.app.transform;

import static com.dianping.cat.configuration.app.Constants.ATTR_ALL;
import static com.dianping.cat.configuration.app.Constants.ATTR_DES;
import static com.dianping.cat.configuration.app.Constants.ATTR_DOMAIN;
import static com.dianping.cat.configuration.app.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.app.Constants.ATTR_STATUS;
import static com.dianping.cat.configuration.app.Constants.ATTR_THRESHOLD;
import static com.dianping.cat.configuration.app.Constants.ATTR_TITLE;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AppConfig buildAppConfig(Attributes attributes) {
      AppConfig appConfig = new AppConfig();

      return appConfig;
   }

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
   public Command buildCommand(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String name = attributes.getValue(ATTR_NAME);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String title = attributes.getValue(ATTR_TITLE);
      String all = attributes.getValue(ATTR_ALL);
      String threshold = attributes.getValue(ATTR_THRESHOLD);
      Command command = new Command(id == null ? null : convert(Integer.class, id, null));

      if (name != null) {
         command.setName(name);
      }

      if (domain != null) {
         command.setDomain(domain);
      }

      if (title != null) {
         command.setTitle(title);
      }

      if (all != null) {
         command.setAll(convert(Boolean.class, all, false));
      }

      if (threshold != null) {
         command.setThreshold(convert(Integer.class, threshold, 0));
      }

      return command;
   }

   @Override
   public ConfigItem buildConfigItem(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      ConfigItem configItem = new ConfigItem(id);

      return configItem;
   }

   @Override
   public Item buildItem(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String name = attributes.getValue(ATTR_NAME);
      String des = attributes.getValue(ATTR_DES);
      Item item = new Item(id == null ? null : convert(Integer.class, id, null));

      if (name != null) {
         item.setName(name);
      }

      if (des != null) {
         item.setDes(des);
      }

      return item;
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
