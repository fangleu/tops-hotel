package com.dianping.cat.home.network;

import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;

import com.dianping.cat.home.network.transform.DefaultXmlBuilder;
import com.dianping.cat.home.network.transform.DefaultJsonBuilder;

public abstract class BaseEntity<T> implements IEntity<T>, Formattable {

   public static final String JSON = "%#.3s";

   public static final String JSON_COMPACT = "%#s";

   public static final String XML = "%.3s";
   
   public static final String XML_COMPACT = "%s";
   
   protected void assertAttributeEquals(Object instance, String entityName, String name, Object expectedValue, Object actualValue) {
      if (expectedValue == null && actualValue != null || expectedValue != null && !expectedValue.equals(actualValue)) {
         throw new IllegalArgumentException(String.format("Mismatched entity(%s) found! Same %s attribute is expected! %s: %s.", entityName, name, entityName, instance));
      }
   }

   @Override
   public void formatTo(Formatter formatter, int flags, int width, int precision) {
      boolean useJson = (flags & FormattableFlags.ALTERNATE) == FormattableFlags.ALTERNATE;
      boolean compact = (precision <= 0);
      
      if (useJson) {
         DefaultJsonBuilder builder = new DefaultJsonBuilder(compact);

         formatter.format(builder.build(this));
      } else {
         DefaultXmlBuilder builder = new DefaultXmlBuilder(compact);

         formatter.format(builder.buildXml(this));
      }
   }

   @Override
   public String toString() {
      return new DefaultXmlBuilder().buildXml(this);
   }
}
