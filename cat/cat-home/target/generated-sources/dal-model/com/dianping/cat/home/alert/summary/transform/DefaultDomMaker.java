package com.dianping.cat.home.alert.summary.transform;

import static com.dianping.cat.home.alert.summary.Constants.ATTR_ALERT_DATE;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_ALERTTIME;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_CONTEXT;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_COUNT;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_METRIC;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_NAME;
import static com.dianping.cat.home.alert.summary.Constants.ATTR_TYPE;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public class DefaultDomMaker implements IMaker<Node> {

   @Override
   public Alert buildAlert(Node node) {
      String alertTime = getAttribute(node, ATTR_ALERTTIME);
      String type = getAttribute(node, ATTR_TYPE);
      String metric = getAttribute(node, ATTR_METRIC);
      String context = getAttribute(node, ATTR_CONTEXT);
      String domain = getAttribute(node, ATTR_DOMAIN);
      String count = getAttribute(node, ATTR_COUNT);

      Alert alert = new Alert();

      if (alertTime != null) {
         alert.setAlertTime(toDate(alertTime, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (type != null) {
         alert.setType(type);
      }

      if (metric != null) {
         alert.setMetric(metric);
      }

      if (context != null) {
         alert.setContext(context);
      }

      if (domain != null) {
         alert.setDomain(domain);
      }

      if (count != null) {
         alert.setCount(convert(Integer.class, count, null));
      }

      return alert;
   }

   @Override
   public AlertSummary buildAlertSummary(Node node) {
      String alertDate = getAttribute(node, ATTR_ALERT_DATE);
      String domain = getAttribute(node, ATTR_DOMAIN);

      AlertSummary alertSummary = new AlertSummary();

      if (alertDate != null) {
         alertSummary.setAlertDate(toDate(alertDate, "yyyy-MM-dd HH:mm:ss", null));
      }

      if (domain != null) {
         alertSummary.setDomain(domain);
      }

      return alertSummary;
   }

   @Override
   public Category buildCategory(Node node) {
      String name = getAttribute(node, ATTR_NAME);

      Category category = new Category(name);

      return category;
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null || value.length() == 0) {
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

   protected String getAttribute(Node node, String name) {
      Node attribute = node.getAttributes().getNamedItem(name);

      return attribute == null ? null : attribute.getNodeValue();
   }

   protected Node getChildTagNode(Node parent, String name) {
      NodeList children = parent.getChildNodes();
      int len = children.getLength();

      for (int i = 0; i < len; i++) {
         Node child = children.item(i);

         if (child.getNodeType() == Node.ELEMENT_NODE) {
            if (child.getNodeName().equals(name)) {
               return child;
            }
         }
      }

      return null;
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
