package com.dianping.cat.home.activity.transform;

import static com.dianping.cat.home.activity.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.activity.Constants.ATTR_NAME;
import static com.dianping.cat.home.activity.Constants.ATTR_TITLE;
import static com.dianping.cat.home.activity.Constants.ATTR_TYPE;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public class DefaultDomMaker implements IMaker<Node> {

   @Override
   public Activity buildActivity(Node node) {
      String domain = getAttribute(node, ATTR_DOMAIN);
      String type = getAttribute(node, ATTR_TYPE);
      String name = getAttribute(node, ATTR_NAME);
      String title = getAttribute(node, ATTR_TITLE);

      Activity activity = new Activity();

      if (domain != null) {
         activity.setDomain(domain);
      }

      if (type != null) {
         activity.setType(type);
      }

      if (name != null) {
         activity.setName(name);
      }

      if (title != null) {
         activity.setTitle(title);
      }

      return activity;
   }

   @Override
   public ActivityConfig buildActivityConfig(Node node) {
      ActivityConfig activityConfig = new ActivityConfig();

      return activityConfig;
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
}
