package com.dianping.cat.home.dependency.graph.transform;

import static com.dianping.cat.home.dependency.graph.Constants.ATTR_DASHED;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_DES;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_ID;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_KEY;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_LINK;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_OPPOSITE;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_SELF;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_STATUS;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_TARGET;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_TYPE;
import static com.dianping.cat.home.dependency.graph.Constants.ATTR_WEIGHT;

import org.xml.sax.Attributes;

import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public TopologyEdge buildTopologyEdge(Attributes attributes) {
      String key = attributes.getValue(ATTR_KEY);
      String type = attributes.getValue(ATTR_TYPE);
      String target = attributes.getValue(ATTR_TARGET);
      String opposite = attributes.getValue(ATTR_OPPOSITE);
      String weight = attributes.getValue(ATTR_WEIGHT);
      String status = attributes.getValue(ATTR_STATUS);
      String des = attributes.getValue(ATTR_DES);
      String link = attributes.getValue(ATTR_LINK);
      String self = attributes.getValue(ATTR_SELF);
      String dashed = attributes.getValue(ATTR_DASHED);
      TopologyEdge topologyEdge = new TopologyEdge(key);

      if (type != null) {
         topologyEdge.setType(type);
      }

      if (target != null) {
         topologyEdge.setTarget(target);
      }

      if (opposite != null) {
         topologyEdge.setOpposite(convert(Boolean.class, opposite, false));
      }

      if (weight != null) {
         topologyEdge.setWeight(convert(Integer.class, weight, 0));
      }

      if (status != null) {
         topologyEdge.setStatus(convert(Integer.class, status, 0));
      }

      if (des != null) {
         topologyEdge.setDes(des);
      }

      if (link != null) {
         topologyEdge.setLink(link);
      }

      if (self != null) {
         topologyEdge.setSelf(self);
      }

      if (dashed != null) {
         topologyEdge.setDashed(convert(Boolean.class, dashed, false));
      }

      return topologyEdge;
   }

   @Override
   public TopologyGraph buildTopologyGraph(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String type = attributes.getValue(ATTR_TYPE);
      String status = attributes.getValue(ATTR_STATUS);
      String des = attributes.getValue(ATTR_DES);
      TopologyGraph topologyGraph = new TopologyGraph();

      if (id != null) {
         topologyGraph.setId(id);
      }

      if (type != null) {
         topologyGraph.setType(type);
      }

      if (status != null) {
         topologyGraph.setStatus(convert(Integer.class, status, 0));
      }

      if (des != null) {
         topologyGraph.setDes(des);
      }

      return topologyGraph;
   }

   @Override
   public TopologyNode buildTopologyNode(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String type = attributes.getValue(ATTR_TYPE);
      String status = attributes.getValue(ATTR_STATUS);
      String des = attributes.getValue(ATTR_DES);
      String link = attributes.getValue(ATTR_LINK);
      String weight = attributes.getValue(ATTR_WEIGHT);
      TopologyNode topologyNode = new TopologyNode(id);

      if (type != null) {
         topologyNode.setType(type);
      }

      if (status != null) {
         topologyNode.setStatus(convert(Integer.class, status, 0));
      }

      if (des != null) {
         topologyNode.setDes(des);
      }

      if (link != null) {
         topologyNode.setLink(link);
      }

      if (weight != null) {
         topologyNode.setWeight(convert(Integer.class, weight, 0));
      }

      return topologyNode;
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
