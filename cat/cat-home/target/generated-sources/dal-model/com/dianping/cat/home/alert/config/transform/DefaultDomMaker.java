package com.dianping.cat.home.alert.config.transform;

import static com.dianping.cat.home.alert.config.Constants.ATTR_ENABLE;
import static com.dianping.cat.home.alert.config.Constants.ATTR_ID;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public class DefaultDomMaker implements IMaker<Node> {

   @Override
   public AlertConfig buildAlertConfig(Node node) {
      String enable = getAttribute(node, ATTR_ENABLE);

      AlertConfig alertConfig = new AlertConfig();

      if (enable != null) {
         alertConfig.setEnable(convert(Boolean.class, enable, null));
      }

      return alertConfig;
   }

   @Override
   public String buildEmail(Node node) {
      return getText(node);
   }

   @Override
   public String buildPhone(Node node) {
      return getText(node);
   }

   @Override
   public Receiver buildReceiver(Node node) {
      String id = getAttribute(node, ATTR_ID);
      String enable = getAttribute(node, ATTR_ENABLE);

      Receiver receiver = new Receiver(id);

      if (enable != null) {
         receiver.setEnable(convert(Boolean.class, enable, null));
      }

      return receiver;
   }

   @Override
   public String buildWeixin(Node node) {
      return getText(node);
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

   protected String getText(Node node) {
      if (node != null) {
         StringBuilder sb = new StringBuilder();
         NodeList children = node.getChildNodes();
         int len = children.getLength();

         for (int i = 0; i < len; i++) {
            Node child = children.item(i);

            if (child.getNodeType() == Node.TEXT_NODE || child.getNodeType() == Node.CDATA_SECTION_NODE) {
               sb.append(child.getNodeValue());
            }
         }

         if (sb.length() != 0) {
            return sb.toString();
         }
      }

      return null;
   }
}
