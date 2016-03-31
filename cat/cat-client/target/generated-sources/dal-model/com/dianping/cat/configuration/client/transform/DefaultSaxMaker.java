package com.dianping.cat.configuration.client.transform;

import static com.dianping.cat.configuration.client.Constants.ATTR_DUMP_LOCKED;
import static com.dianping.cat.configuration.client.Constants.ATTR_ENABLED;
import static com.dianping.cat.configuration.client.Constants.ATTR_HTTP_PORT;
import static com.dianping.cat.configuration.client.Constants.ATTR_ID;
import static com.dianping.cat.configuration.client.Constants.ATTR_IP;
import static com.dianping.cat.configuration.client.Constants.ATTR_MAX_MESSAGE_SIZE;
import static com.dianping.cat.configuration.client.Constants.ATTR_MODE;
import static com.dianping.cat.configuration.client.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.client.Constants.ATTR_PORT;

import java.util.Map;
import org.xml.sax.Attributes;

import com.dianping.cat.configuration.client.entity.Bind;
import com.dianping.cat.configuration.client.entity.ClientConfig;
import com.dianping.cat.configuration.client.entity.Domain;
import com.dianping.cat.configuration.client.entity.Property;
import com.dianping.cat.configuration.client.entity.Server;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Bind buildBind(Attributes attributes) {
      String ip = attributes.getValue(ATTR_IP);
      String port = attributes.getValue(ATTR_PORT);
      Bind bind = new Bind();

      if (ip != null) {
         bind.setIp(ip);
      }

      if (port != null) {
         bind.setPort(convert(Integer.class, port, 0));
      }

      return bind;
   }

   @Override
   public ClientConfig buildConfig(Attributes attributes) {
      String mode = attributes.getValue(ATTR_MODE);
      String enabled = attributes.getValue(ATTR_ENABLED);
      String dumpLocked = attributes.getValue(ATTR_DUMP_LOCKED);
      ClientConfig config = new ClientConfig();

      if (mode != null) {
         config.setMode(mode);
      }

      if (enabled != null) {
         config.setEnabled(convert(Boolean.class, enabled, null));
      }

      if (dumpLocked != null) {
         config.setDumpLocked(convert(Boolean.class, dumpLocked, null));
      }

      Map<String, String> dynamicAttributes = config.getDynamicAttributes();
      int _length = attributes == null ? 0 : attributes.getLength();

      for (int i = 0; i < _length; i++) {
         String _name = attributes.getQName(i);
         String _value = attributes.getValue(i);

         dynamicAttributes.put(_name, _value);
      }

      dynamicAttributes.remove(ATTR_MODE);
      dynamicAttributes.remove(ATTR_ENABLED);
      dynamicAttributes.remove(ATTR_DUMP_LOCKED);

      return config;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String ip = attributes.getValue(ATTR_IP);
      String enabled = attributes.getValue(ATTR_ENABLED);
      String maxMessageSize = attributes.getValue(ATTR_MAX_MESSAGE_SIZE);
      Domain domain = new Domain(id);

      if (ip != null) {
         domain.setIp(ip);
      }

      if (enabled != null) {
         domain.setEnabled(convert(Boolean.class, enabled, null));
      }

      if (maxMessageSize != null) {
         domain.setMaxMessageSize(convert(Integer.class, maxMessageSize, 0));
      }

      return domain;
   }

   @Override
   public Property buildProperty(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      Property property = new Property(name);

      return property;
   }

   @Override
   public Server buildServer(Attributes attributes) {
      String ip = attributes.getValue(ATTR_IP);
      String port = attributes.getValue(ATTR_PORT);
      String httpPort = attributes.getValue(ATTR_HTTP_PORT);
      String enabled = attributes.getValue(ATTR_ENABLED);
      Server server = new Server(ip);

      if (port != null) {
         server.setPort(convert(Integer.class, port, null));
      }

      if (httpPort != null) {
         server.setHttpPort(convert(Integer.class, httpPort, null));
      }

      if (enabled != null) {
         server.setEnabled(convert(Boolean.class, enabled, null));
      }

      return server;
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
