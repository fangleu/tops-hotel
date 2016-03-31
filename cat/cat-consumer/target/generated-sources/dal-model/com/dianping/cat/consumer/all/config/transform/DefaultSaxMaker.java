package com.dianping.cat.consumer.all.config.transform;

import static com.dianping.cat.consumer.all.config.Constants.ATTR_ID;

import org.xml.sax.Attributes;

import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AllConfig buildAllConfig(Attributes attributes) {
      AllConfig allConfig = new AllConfig();

      return allConfig;
   }

   @Override
   public Name buildName(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Name name = new Name();

      if (id != null) {
         name.setId(id);
      }

      return name;
   }

   @Override
   public Report buildReport(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Report report = new Report(id);

      return report;
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
