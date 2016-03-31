package com.dianping.cat.home.router.transform;

import static com.dianping.cat.home.router.Constants.ATTR_BACKUP_SERVER;
import static com.dianping.cat.home.router.Constants.ATTR_BACKUP_SERVER_PORT;
import static com.dianping.cat.home.router.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.router.Constants.ATTR_ENABLE;
import static com.dianping.cat.home.router.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.router.Constants.ATTR_ID;
import static com.dianping.cat.home.router.Constants.ATTR_PORT;
import static com.dianping.cat.home.router.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.router.Constants.ATTR_WEIGHT;

import org.xml.sax.Attributes;

import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public DefaultServer buildDefaultServer(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String port = attributes.getValue(ATTR_PORT);
      String enable = attributes.getValue(ATTR_ENABLE);
      String weight = attributes.getValue(ATTR_WEIGHT);
      DefaultServer defaultServer = new DefaultServer();

      if (id != null) {
         defaultServer.setId(id);
      }

      if (port != null) {
         defaultServer.setPort(convert(Integer.class, port, 0));
      }

      if (enable != null) {
         defaultServer.setEnable(convert(Boolean.class, enable, false));
      }

      if (weight != null) {
         defaultServer.setWeight(convert(Double.class, weight, 0.0));
      }

      return defaultServer;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Domain domain = new Domain(id);

      return domain;
   }

   @Override
   public RouterConfig buildRouterConfig(Attributes attributes) {
      String startTime = attributes.getValue(ATTR_STARTTIME);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String backupServer = attributes.getValue(ATTR_BACKUP_SERVER);
      String backupServerPort = attributes.getValue(ATTR_BACKUP_SERVER_PORT);
      String endTime = attributes.getValue(ATTR_ENDTIME);
      RouterConfig routerConfig = new RouterConfig(domain);

      if (startTime != null) {
         routerConfig.setStartTime(toDate(startTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (backupServer != null) {
         routerConfig.setBackupServer(backupServer);
      }

      if (backupServerPort != null) {
         routerConfig.setBackupServerPort(convert(Integer.class, backupServerPort, 0));
      }

      if (endTime != null) {
         routerConfig.setEndTime(toDate(endTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      return routerConfig;
   }

   @Override
   public Server buildServer(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String port = attributes.getValue(ATTR_PORT);
      String weight = attributes.getValue(ATTR_WEIGHT);
      Server server = new Server();

      if (id != null) {
         server.setId(id);
      }

      if (port != null) {
         server.setPort(convert(Integer.class, port, 0));
      }

      if (weight != null) {
         server.setWeight(convert(Double.class, weight, 0.0));
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

   protected java.util.Date toDate(String str, String format, java.util.Date defaultValue) {
      if (str == null || str.length() == 0) {
         return defaultValue;
      }

      try {
         return new java.text.SimpleDateFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse date(%s) in format(%s)!", str, format), e);
      }
   }
}
