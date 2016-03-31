package com.dianping.cat.configuration.server.transform;

import static com.dianping.cat.configuration.server.Constants.ATTR_ALERT_MACHINE;
import static com.dianping.cat.configuration.server.Constants.ATTR_BASE_DIR;
import static com.dianping.cat.configuration.server.Constants.ATTR_DEFAULT_DOMAIN;
import static com.dianping.cat.configuration.server.Constants.ATTR_DEFAULT_SERVICE_THRESHOLD;
import static com.dianping.cat.configuration.server.Constants.ATTR_DEFAULT_SQL_THRESHOLD;
import static com.dianping.cat.configuration.server.Constants.ATTR_DEFAULT_URL_THRESHOLD;
import static com.dianping.cat.configuration.server.Constants.ATTR_HDFS_DISABLED;
import static com.dianping.cat.configuration.server.Constants.ATTR_HDFS_MACHINE;
import static com.dianping.cat.configuration.server.Constants.ATTR_ID;
import static com.dianping.cat.configuration.server.Constants.ATTR_JOB_MACHINE;
import static com.dianping.cat.configuration.server.Constants.ATTR_LDAPURL;
import static com.dianping.cat.configuration.server.Constants.ATTR_LOCAL_BASE_DIR;
import static com.dianping.cat.configuration.server.Constants.ATTR_LOCAL_LOGIVEW_STORAGE_TIME;
import static com.dianping.cat.configuration.server.Constants.ATTR_LOCAL_MODE;
import static com.dianping.cat.configuration.server.Constants.ATTR_LOCAL_REPORT_STORAGE_TIME;
import static com.dianping.cat.configuration.server.Constants.ATTR_MAX_HDFS_STORAGE_TIME;
import static com.dianping.cat.configuration.server.Constants.ATTR_MAX_SIZE;
import static com.dianping.cat.configuration.server.Constants.ATTR_NAME;
import static com.dianping.cat.configuration.server.Constants.ATTR_SEND_MACHINE;
import static com.dianping.cat.configuration.server.Constants.ATTR_SERVER_URI;
import static com.dianping.cat.configuration.server.Constants.ATTR_SERVICE_THRESHOLD;
import static com.dianping.cat.configuration.server.Constants.ATTR_SHOW_CAT_DOMAIN;
import static com.dianping.cat.configuration.server.Constants.ATTR_SQL_THRESHOLD;
import static com.dianping.cat.configuration.server.Constants.ATTR_UPLOAD_THREAD;
import static com.dianping.cat.configuration.server.Constants.ATTR_URL_THRESHOLD;
import static com.dianping.cat.configuration.server.Constants.ATTR_VALUE;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.server.entity.ConsoleConfig;
import com.dianping.cat.configuration.server.entity.ConsumerConfig;
import com.dianping.cat.configuration.server.entity.Domain;
import com.dianping.cat.configuration.server.entity.HdfsConfig;
import com.dianping.cat.configuration.server.entity.Ldap;
import com.dianping.cat.configuration.server.entity.LongConfig;
import com.dianping.cat.configuration.server.entity.Property;
import com.dianping.cat.configuration.server.entity.ServerConfig;
import com.dianping.cat.configuration.server.entity.StorageConfig;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public ServerConfig buildConfig(Attributes attributes) {
      String localMode = attributes.getValue(ATTR_LOCAL_MODE);
      String jobMachine = attributes.getValue(ATTR_JOB_MACHINE);
      String alertMachine = attributes.getValue(ATTR_ALERT_MACHINE);
      String hdfsMachine = attributes.getValue(ATTR_HDFS_MACHINE);
      String sendMachine = attributes.getValue(ATTR_SEND_MACHINE);
      ServerConfig config = new ServerConfig();

      if (localMode != null) {
         config.setLocalMode(convert(Boolean.class, localMode, null));
      }

      if (jobMachine != null) {
         config.setJobMachine(convert(Boolean.class, jobMachine, null));
      }

      if (alertMachine != null) {
         config.setAlertMachine(convert(Boolean.class, alertMachine, null));
      }

      if (hdfsMachine != null) {
         config.setHdfsMachine(convert(Boolean.class, hdfsMachine, null));
      }

      if (sendMachine != null) {
         config.setSendMachine(convert(Boolean.class, sendMachine, null));
      }

      return config;
   }

   @Override
   public ConsoleConfig buildConsole(Attributes attributes) {
      String defaultDomain = attributes.getValue(ATTR_DEFAULT_DOMAIN);
      String showCatDomain = attributes.getValue(ATTR_SHOW_CAT_DOMAIN);
      ConsoleConfig console = new ConsoleConfig();

      if (defaultDomain != null) {
         console.setDefaultDomain(defaultDomain);
      }

      if (showCatDomain != null) {
         console.setShowCatDomain(convert(Boolean.class, showCatDomain, null));
      }

      return console;
   }

   @Override
   public ConsumerConfig buildConsumer(Attributes attributes) {
      ConsumerConfig consumer = new ConsumerConfig();

      return consumer;
   }

   @Override
   public Domain buildDomain(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String urlThreshold = attributes.getValue(ATTR_URL_THRESHOLD);
      String sqlThreshold = attributes.getValue(ATTR_SQL_THRESHOLD);
      String serviceThreshold = attributes.getValue(ATTR_SERVICE_THRESHOLD);
      Domain domain = new Domain(name);

      if (urlThreshold != null) {
         domain.setUrlThreshold(convert(Integer.class, urlThreshold, null));
      }

      if (sqlThreshold != null) {
         domain.setSqlThreshold(convert(Integer.class, sqlThreshold, null));
      }

      if (serviceThreshold != null) {
         domain.setServiceThreshold(convert(Integer.class, serviceThreshold, null));
      }

      return domain;
   }

   @Override
   public HdfsConfig buildHdfs(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String maxSize = attributes.getValue(ATTR_MAX_SIZE);
      String serverUri = attributes.getValue(ATTR_SERVER_URI);
      String baseDir = attributes.getValue(ATTR_BASE_DIR);
      HdfsConfig hdfs = new HdfsConfig(id);

      if (maxSize != null) {
         hdfs.setMaxSize(maxSize);
      }

      if (serverUri != null) {
         hdfs.setServerUri(serverUri);
      }

      if (baseDir != null) {
         hdfs.setBaseDir(baseDir);
      }

      return hdfs;
   }

   @Override
   public Ldap buildLdap(Attributes attributes) {
      String ldapUrl = attributes.getValue(ATTR_LDAPURL);
      Ldap ldap = new Ldap();

      if (ldapUrl != null) {
         ldap.setLdapUrl(ldapUrl);
      }

      return ldap;
   }

   @Override
   public LongConfig buildLongConfig(Attributes attributes) {
      String defaultUrlThreshold = attributes.getValue(ATTR_DEFAULT_URL_THRESHOLD);
      String defaultSqlThreshold = attributes.getValue(ATTR_DEFAULT_SQL_THRESHOLD);
      String defaultServiceThreshold = attributes.getValue(ATTR_DEFAULT_SERVICE_THRESHOLD);
      LongConfig longConfig = new LongConfig();

      if (defaultUrlThreshold != null) {
         longConfig.setDefaultUrlThreshold(convert(Integer.class, defaultUrlThreshold, null));
      }

      if (defaultSqlThreshold != null) {
         longConfig.setDefaultSqlThreshold(convert(Integer.class, defaultSqlThreshold, null));
      }

      if (defaultServiceThreshold != null) {
         longConfig.setDefaultServiceThreshold(convert(Integer.class, defaultServiceThreshold, null));
      }

      return longConfig;
   }

   @Override
   public Property buildProperty(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String value = attributes.getValue(ATTR_VALUE);
      Property property = new Property(name);

      if (value != null) {
         property.setValue(value);
      }

      return property;
   }

   @Override
   public StorageConfig buildStorage(Attributes attributes) {
      String localBaseDir = attributes.getValue(ATTR_LOCAL_BASE_DIR);
      String hdfsDisabled = attributes.getValue(ATTR_HDFS_DISABLED);
      String uploadThread = attributes.getValue(ATTR_UPLOAD_THREAD);
      String maxHdfsStorageTime = attributes.getValue(ATTR_MAX_HDFS_STORAGE_TIME);
      String localReportStorageTime = attributes.getValue(ATTR_LOCAL_REPORT_STORAGE_TIME);
      String localLogivewStorageTime = attributes.getValue(ATTR_LOCAL_LOGIVEW_STORAGE_TIME);
      StorageConfig storage = new StorageConfig();

      if (localBaseDir != null) {
         storage.setLocalBaseDir(localBaseDir);
      }

      if (hdfsDisabled != null) {
         storage.setHdfsDisabled(convert(Boolean.class, hdfsDisabled, null));
      }

      if (uploadThread != null) {
         storage.setUploadThread(convert(Integer.class, uploadThread, 0));
      }

      if (maxHdfsStorageTime != null) {
         storage.setMaxHdfsStorageTime(convert(Integer.class, maxHdfsStorageTime, 0));
      }

      if (localReportStorageTime != null) {
         storage.setLocalReportStorageTime(convert(Integer.class, localReportStorageTime, 0));
      }

      if (localLogivewStorageTime != null) {
         storage.setLocalLogivewStorageTime(convert(Integer.class, localLogivewStorageTime, 0));
      }

      return storage;
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
