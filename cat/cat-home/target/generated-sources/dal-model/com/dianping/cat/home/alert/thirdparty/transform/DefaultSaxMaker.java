package com.dianping.cat.home.alert.thirdparty.transform;

import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_ID;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_IP;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_PORT;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_TYPE;
import static com.dianping.cat.home.alert.thirdparty.Constants.ATTR_URL;

import org.xml.sax.Attributes;

import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Http buildHttp(Attributes attributes) {
      String url = attributes.getValue(ATTR_URL);
      String type = attributes.getValue(ATTR_TYPE);
      String domain = attributes.getValue(ATTR_DOMAIN);
      Http http = new Http();

      if (url != null) {
         http.setUrl(url);
      }

      if (type != null) {
         http.setType(type);
      }

      if (domain != null) {
         http.setDomain(domain);
      }

      return http;
   }

   @Override
   public Par buildPar(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Par par = new Par();

      if (id != null) {
         par.setId(id);
      }

      return par;
   }

   @Override
   public Socket buildSocket(Attributes attributes) {
      String ip = attributes.getValue(ATTR_IP);
      String port = attributes.getValue(ATTR_PORT);
      String domain = attributes.getValue(ATTR_DOMAIN);
      Socket socket = new Socket();

      if (ip != null) {
         socket.setIp(ip);
      }

      if (port != null) {
         socket.setPort(convert(Integer.class, port, 0));
      }

      if (domain != null) {
         socket.setDomain(domain);
      }

      return socket;
   }

   @Override
   public ThirdPartyConfig buildThirdPartyConfig(Attributes attributes) {
      ThirdPartyConfig thirdPartyConfig = new ThirdPartyConfig();

      return thirdPartyConfig;
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
