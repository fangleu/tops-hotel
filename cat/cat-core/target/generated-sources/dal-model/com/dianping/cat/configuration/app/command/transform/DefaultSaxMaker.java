package com.dianping.cat.configuration.app.command.transform;

import static com.dianping.cat.configuration.app.command.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PATTERN;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PICLARGE;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PICMIDDEL;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_PICSMALL;
import static com.dianping.cat.configuration.app.command.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Command buildCommand(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Command command = new Command();

      if (id != null) {
         command.setId(id);
      }

      return command;
   }

   @Override
   public CommandFormat buildCommandFormat(Attributes attributes) {
      String picSmall = attributes.getValue(ATTR_PICSMALL);
      String picMiddel = attributes.getValue(ATTR_PICMIDDEL);
      String picLarge = attributes.getValue(ATTR_PICLARGE);
      CommandFormat commandFormat = new CommandFormat();

      if (picSmall != null) {
         commandFormat.setPicSmall(convert(Integer.class, picSmall, 0));
      }

      if (picMiddel != null) {
         commandFormat.setPicMiddel(convert(Integer.class, picMiddel, 0));
      }

      if (picLarge != null) {
         commandFormat.setPicLarge(convert(Integer.class, picLarge, 0));
      }

      return commandFormat;
   }

   @Override
   public Rule buildRule(Attributes attributes) {
      String pattern = attributes.getValue(ATTR_PATTERN);
      String type = attributes.getValue(ATTR_TYPE);
      Rule rule = new Rule();

      if (pattern != null) {
         rule.setPattern(pattern);
      }

      if (type != null) {
         rule.setType(convert(Integer.class, type, 0));
      }

      return rule;
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
