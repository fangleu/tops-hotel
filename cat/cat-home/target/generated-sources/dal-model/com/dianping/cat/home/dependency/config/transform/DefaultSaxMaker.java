package com.dianping.cat.home.dependency.config.transform;

import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_ERROR_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_ERROR_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_MIN_COUNT_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_WARNING_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_DEFAULT_WARNING_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_ERROR_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_ERROR_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_FROM;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_ID;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_KEY;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_MIN_COUNT_THRESHOLD;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_TO;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_TYPE;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_WARNING_RESPONSE_TIME;
import static com.dianping.cat.home.dependency.config.Constants.ATTR_WARNING_THRESHOLD;

import org.xml.sax.Attributes;

import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public DomainConfig buildDomainConfig(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String warningThreshold = attributes.getValue(ATTR_WARNING_THRESHOLD);
      String errorThreshold = attributes.getValue(ATTR_ERROR_THRESHOLD);
      String warningResponseTime = attributes.getValue(ATTR_WARNING_RESPONSE_TIME);
      String errorResponseTime = attributes.getValue(ATTR_ERROR_RESPONSE_TIME);
      String minCountThreshold = attributes.getValue(ATTR_MIN_COUNT_THRESHOLD);
      DomainConfig domainConfig = new DomainConfig(id);

      if (warningThreshold != null) {
         domainConfig.setWarningThreshold(convert(Integer.class, warningThreshold, 0));
      }

      if (errorThreshold != null) {
         domainConfig.setErrorThreshold(convert(Integer.class, errorThreshold, 0));
      }

      if (warningResponseTime != null) {
         domainConfig.setWarningResponseTime(convert(Double.class, warningResponseTime, 0.0));
      }

      if (errorResponseTime != null) {
         domainConfig.setErrorResponseTime(convert(Double.class, errorResponseTime, 0.0));
      }

      if (minCountThreshold != null) {
         domainConfig.setMinCountThreshold(convert(Integer.class, minCountThreshold, 0));
      }

      return domainConfig;
   }

   @Override
   public EdgeConfig buildEdgeConfig(Attributes attributes) {
      String key = attributes.getValue(ATTR_KEY);
      String type = attributes.getValue(ATTR_TYPE);
      String from = attributes.getValue(ATTR_FROM);
      String to = attributes.getValue(ATTR_TO);
      String warningThreshold = attributes.getValue(ATTR_WARNING_THRESHOLD);
      String errorThreshold = attributes.getValue(ATTR_ERROR_THRESHOLD);
      String warningResponseTime = attributes.getValue(ATTR_WARNING_RESPONSE_TIME);
      String errorResponseTime = attributes.getValue(ATTR_ERROR_RESPONSE_TIME);
      String minCountThreshold = attributes.getValue(ATTR_MIN_COUNT_THRESHOLD);
      EdgeConfig edgeConfig = new EdgeConfig(key);

      if (type != null) {
         edgeConfig.setType(type);
      }

      if (from != null) {
         edgeConfig.setFrom(from);
      }

      if (to != null) {
         edgeConfig.setTo(to);
      }

      if (warningThreshold != null) {
         edgeConfig.setWarningThreshold(convert(Integer.class, warningThreshold, 0));
      }

      if (errorThreshold != null) {
         edgeConfig.setErrorThreshold(convert(Integer.class, errorThreshold, 0));
      }

      if (warningResponseTime != null) {
         edgeConfig.setWarningResponseTime(convert(Double.class, warningResponseTime, 0.0));
      }

      if (errorResponseTime != null) {
         edgeConfig.setErrorResponseTime(convert(Double.class, errorResponseTime, 0.0));
      }

      if (minCountThreshold != null) {
         edgeConfig.setMinCountThreshold(convert(Integer.class, minCountThreshold, 0));
      }

      return edgeConfig;
   }

   @Override
   public NodeConfig buildNodeConfig(Attributes attributes) {
      String type = attributes.getValue(ATTR_TYPE);
      String defaultWarningThreshold = attributes.getValue(ATTR_DEFAULT_WARNING_THRESHOLD);
      String defaultErrorThreshold = attributes.getValue(ATTR_DEFAULT_ERROR_THRESHOLD);
      String defaultWarningResponseTime = attributes.getValue(ATTR_DEFAULT_WARNING_RESPONSE_TIME);
      String defaultErrorResponseTime = attributes.getValue(ATTR_DEFAULT_ERROR_RESPONSE_TIME);
      String defaultMinCountThreshold = attributes.getValue(ATTR_DEFAULT_MIN_COUNT_THRESHOLD);
      NodeConfig nodeConfig = new NodeConfig(type);

      if (defaultWarningThreshold != null) {
         nodeConfig.setDefaultWarningThreshold(convert(Integer.class, defaultWarningThreshold, null));
      }

      if (defaultErrorThreshold != null) {
         nodeConfig.setDefaultErrorThreshold(convert(Integer.class, defaultErrorThreshold, null));
      }

      if (defaultWarningResponseTime != null) {
         nodeConfig.setDefaultWarningResponseTime(convert(Double.class, defaultWarningResponseTime, null));
      }

      if (defaultErrorResponseTime != null) {
         nodeConfig.setDefaultErrorResponseTime(convert(Double.class, defaultErrorResponseTime, null));
      }

      if (defaultMinCountThreshold != null) {
         nodeConfig.setDefaultMinCountThreshold(convert(Integer.class, defaultMinCountThreshold, 0));
      }

      return nodeConfig;
   }

   @Override
   public TopologyGraphConfig buildTopologyGraphConfig(Attributes attributes) {
      TopologyGraphConfig topologyGraphConfig = new TopologyGraphConfig();

      return topologyGraphConfig;
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
