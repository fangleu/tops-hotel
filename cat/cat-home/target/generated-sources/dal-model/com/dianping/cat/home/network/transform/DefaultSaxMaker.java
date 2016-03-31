package com.dianping.cat.home.network.transform;

import static com.dianping.cat.home.network.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.network.Constants.ATTR_FROM;
import static com.dianping.cat.home.network.Constants.ATTR_GROUP;
import static com.dianping.cat.home.network.Constants.ATTR_IN;
import static com.dianping.cat.home.network.Constants.ATTR_INDISCARDS;
import static com.dianping.cat.home.network.Constants.ATTR_INDISCARDSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_INERRORS;
import static com.dianping.cat.home.network.Constants.ATTR_INERRORSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_INSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_INSUM;
import static com.dianping.cat.home.network.Constants.ATTR_KEY;
import static com.dianping.cat.home.network.Constants.ATTR_MINUTE;
import static com.dianping.cat.home.network.Constants.ATTR_NAME;
import static com.dianping.cat.home.network.Constants.ATTR_OUT;
import static com.dianping.cat.home.network.Constants.ATTR_OUTDISCARDS;
import static com.dianping.cat.home.network.Constants.ATTR_OUTDISCARDSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_OUTERRORS;
import static com.dianping.cat.home.network.Constants.ATTR_OUTERRORSSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_OUTSTATE;
import static com.dianping.cat.home.network.Constants.ATTR_OUTSUM;
import static com.dianping.cat.home.network.Constants.ATTR_STATE;
import static com.dianping.cat.home.network.Constants.ATTR_TO;
import static com.dianping.cat.home.network.Constants.ATTR_X;
import static com.dianping.cat.home.network.Constants.ATTR_Y;

import org.xml.sax.Attributes;

import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Anchor buildAnchor(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String x = attributes.getValue(ATTR_X);
      String y = attributes.getValue(ATTR_Y);
      Anchor anchor = new Anchor();

      if (name != null) {
         anchor.setName(name);
      }

      if (x != null) {
         anchor.setX(convert(Integer.class, x, null));
      }

      if (y != null) {
         anchor.setY(convert(Integer.class, y, null));
      }

      return anchor;
   }

   @Override
   public Connection buildConnection(Attributes attributes) {
      String from = attributes.getValue(ATTR_FROM);
      String to = attributes.getValue(ATTR_TO);
      String instate = attributes.getValue(ATTR_INSTATE);
      String outstate = attributes.getValue(ATTR_OUTSTATE);
      String insum = attributes.getValue(ATTR_INSUM);
      String outsum = attributes.getValue(ATTR_OUTSUM);
      String inDiscardsState = attributes.getValue(ATTR_INDISCARDSSTATE);
      String outDiscardsState = attributes.getValue(ATTR_OUTDISCARDSSTATE);
      String inErrorsState = attributes.getValue(ATTR_INERRORSSTATE);
      String outErrorsState = attributes.getValue(ATTR_OUTERRORSSTATE);
      String inDiscards = attributes.getValue(ATTR_INDISCARDS);
      String outDiscards = attributes.getValue(ATTR_OUTDISCARDS);
      String inErrors = attributes.getValue(ATTR_INERRORS);
      String outErrors = attributes.getValue(ATTR_OUTERRORS);
      Connection connection = new Connection();

      if (from != null) {
         connection.setFrom(from);
      }

      if (to != null) {
         connection.setTo(to);
      }

      if (instate != null) {
         connection.setInstate(convert(Integer.class, instate, null));
      }

      if (outstate != null) {
         connection.setOutstate(convert(Integer.class, outstate, null));
      }

      if (insum != null) {
         connection.setInsum(convert(Double.class, insum, null));
      }

      if (outsum != null) {
         connection.setOutsum(convert(Double.class, outsum, null));
      }

      if (inDiscardsState != null) {
         connection.setInDiscardsState(convert(Integer.class, inDiscardsState, null));
      }

      if (outDiscardsState != null) {
         connection.setOutDiscardsState(convert(Integer.class, outDiscardsState, null));
      }

      if (inErrorsState != null) {
         connection.setInErrorsState(convert(Integer.class, inErrorsState, null));
      }

      if (outErrorsState != null) {
         connection.setOutErrorsState(convert(Integer.class, outErrorsState, null));
      }

      if (inDiscards != null) {
         connection.setInDiscards(convert(Double.class, inDiscards, null));
      }

      if (outDiscards != null) {
         connection.setOutDiscards(convert(Double.class, outDiscards, null));
      }

      if (inErrors != null) {
         connection.setInErrors(convert(Double.class, inErrors, null));
      }

      if (outErrors != null) {
         connection.setOutErrors(convert(Double.class, outErrors, null));
      }

      return connection;
   }

   @Override
   public Interface buildInterface(Attributes attributes) {
      String group = attributes.getValue(ATTR_GROUP);
      String domain = attributes.getValue(ATTR_DOMAIN);
      String key = attributes.getValue(ATTR_KEY);
      String instate = attributes.getValue(ATTR_INSTATE);
      String outstate = attributes.getValue(ATTR_OUTSTATE);
      String in = attributes.getValue(ATTR_IN);
      String out = attributes.getValue(ATTR_OUT);
      String inDiscardsState = attributes.getValue(ATTR_INDISCARDSSTATE);
      String outDiscardsState = attributes.getValue(ATTR_OUTDISCARDSSTATE);
      String inErrorsState = attributes.getValue(ATTR_INERRORSSTATE);
      String outErrorsState = attributes.getValue(ATTR_OUTERRORSSTATE);
      String inDiscards = attributes.getValue(ATTR_INDISCARDS);
      String outDiscards = attributes.getValue(ATTR_OUTDISCARDS);
      String inErrors = attributes.getValue(ATTR_INERRORS);
      String outErrors = attributes.getValue(ATTR_OUTERRORS);
      Interface interface_ = new Interface();

      if (group != null) {
         interface_.setGroup(group);
      }

      if (domain != null) {
         interface_.setDomain(domain);
      }

      if (key != null) {
         interface_.setKey(key);
      }

      if (instate != null) {
         interface_.setInstate(convert(Integer.class, instate, null));
      }

      if (outstate != null) {
         interface_.setOutstate(convert(Integer.class, outstate, null));
      }

      if (in != null) {
         interface_.setIn(convert(Double.class, in, null));
      }

      if (out != null) {
         interface_.setOut(convert(Double.class, out, null));
      }

      if (inDiscardsState != null) {
         interface_.setInDiscardsState(convert(Integer.class, inDiscardsState, null));
      }

      if (outDiscardsState != null) {
         interface_.setOutDiscardsState(convert(Integer.class, outDiscardsState, null));
      }

      if (inErrorsState != null) {
         interface_.setInErrorsState(convert(Integer.class, inErrorsState, null));
      }

      if (outErrorsState != null) {
         interface_.setOutErrorsState(convert(Integer.class, outErrorsState, null));
      }

      if (inDiscards != null) {
         interface_.setInDiscards(convert(Double.class, inDiscards, null));
      }

      if (outDiscards != null) {
         interface_.setOutDiscards(convert(Double.class, outDiscards, null));
      }

      if (inErrors != null) {
         interface_.setInErrors(convert(Double.class, inErrors, null));
      }

      if (outErrors != null) {
         interface_.setOutErrors(convert(Double.class, outErrors, null));
      }

      return interface_;
   }

   @Override
   public NetGraph buildNetGraph(Attributes attributes) {
      String minute = attributes.getValue(ATTR_MINUTE);
      NetGraph netGraph = new NetGraph(minute == null ? null : convert(Integer.class, minute, null));

      return netGraph;
   }

   @Override
   public NetGraphSet buildNetGraphSet(Attributes attributes) {
      NetGraphSet netGraphSet = new NetGraphSet();

      return netGraphSet;
   }

   @Override
   public NetTopology buildNetTopology(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      NetTopology netTopology = new NetTopology();

      if (name != null) {
         netTopology.setName(name);
      }

      return netTopology;
   }

   @Override
   public Switch buildSwitch(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String x = attributes.getValue(ATTR_X);
      String y = attributes.getValue(ATTR_Y);
      String state = attributes.getValue(ATTR_STATE);
      Switch switch_ = new Switch();

      if (name != null) {
         switch_.setName(name);
      }

      if (x != null) {
         switch_.setX(convert(Integer.class, x, null));
      }

      if (y != null) {
         switch_.setY(convert(Integer.class, y, null));
      }

      if (state != null) {
         switch_.setState(convert(Integer.class, state, null));
      }

      return switch_;
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
