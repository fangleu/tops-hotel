package com.dianping.cat.home.rule.transform;

import static com.dianping.cat.home.rule.Constants.ENTITY_CONDITION;
import static com.dianping.cat.home.rule.Constants.ENTITY_CONFIG;
import static com.dianping.cat.home.rule.Constants.ENTITY_METRIC_ITEM;
import static com.dianping.cat.home.rule.Constants.ENTITY_MONITOR_RULES;
import static com.dianping.cat.home.rule.Constants.ENTITY_RULE;
import static com.dianping.cat.home.rule.Constants.ENTITY_SUB_CONDITION;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public class DefaultDomParser implements IParser<Node> {

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

   protected List<Node> getChildTagNodes(Node parent, String name) {
      NodeList children = parent.getChildNodes();
      int len = children.getLength();
      List<Node> nodes = new ArrayList<Node>(len);

      for (int i = 0; i < len; i++) {
         Node child = children.item(i);

         if (child.getNodeType() == Node.ELEMENT_NODE) {
            if (name == null || child.getNodeName().equals(name)) {
               nodes.add(child);
            }
         }
      }

      return nodes;
   }

   protected Node getDocument(String xml) {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      dbf.setIgnoringElementContentWhitespace(true);
      dbf.setIgnoringComments(true);

      try {
         DocumentBuilder db = dbf.newDocumentBuilder();

         return db.parse(new InputSource(new StringReader(xml)));
      } catch (Exception x) {
         throw new RuntimeException(x);
      }
   }

   protected List<Node> getGrandChildTagNodes(Node parent, String name) {
      Node child = getChildTagNode(parent, name);
      NodeList children = child == null ? null : child.getChildNodes();
      int len = children == null ? 0 : children.getLength();
      List<Node> nodes = new ArrayList<Node>(len);

      for (int i = 0; i < len; i++) {
         Node grandChild = children.item(i);

         if (grandChild.getNodeType() == Node.ELEMENT_NODE) {
            nodes.add(grandChild);
         }
      }

      return nodes;
   }

   public MonitorRules parse(Node node) {
      return parse(new DefaultDomMaker(), new DefaultLinker(false), node);
   }

   public MonitorRules parse(String xml) throws SAXException, IOException {
      Node doc = getDocument(xml);
      Node rootNode = getChildTagNode(doc, ENTITY_MONITOR_RULES);

      if (rootNode == null) {
         throw new RuntimeException(String.format("monitor-rules element(%s) is expected!", ENTITY_MONITOR_RULES));
      }

      return parse(new DefaultDomMaker(), new DefaultLinker(false), rootNode);
   }

   public MonitorRules parse(IMaker<Node> maker, ILinker linker, Node node) {
      MonitorRules monitorRules = maker.buildMonitorRules(node);

      if (node != null) {
         MonitorRules parent = monitorRules;

         for (Node child : getChildTagNodes(node, ENTITY_RULE)) {
            Rule rule = maker.buildRule(child);

            if (linker.onRule(parent, rule)) {
               parseForRule(maker, linker, rule, child);
            }
         }
      }

      return monitorRules;
   }

   public void parseForCondition(IMaker<Node> maker, ILinker linker, Condition parent, Node node) {
      for (Node child : getChildTagNodes(node, ENTITY_SUB_CONDITION)) {
         SubCondition subCondition = maker.buildSubCondition(child);

         if (linker.onSubCondition(parent, subCondition)) {
            parseForSubCondition(maker, linker, subCondition, child);
         }
      }
   }

   public void parseForConfig(IMaker<Node> maker, ILinker linker, Config parent, Node node) {
      for (Node child : getChildTagNodes(node, ENTITY_CONDITION)) {
         Condition condition = maker.buildCondition(child);

         if (linker.onCondition(parent, condition)) {
            parseForCondition(maker, linker, condition, child);
         }
      }
   }

   public void parseForMetricItem(IMaker<Node> maker, ILinker linker, MetricItem parent, Node node) {
   }

   public void parseForRule(IMaker<Node> maker, ILinker linker, Rule parent, Node node) {
      for (Node child : getChildTagNodes(node, ENTITY_METRIC_ITEM)) {
         MetricItem metricItem = maker.buildMetricItem(child);

         if (linker.onMetricItem(parent, metricItem)) {
            parseForMetricItem(maker, linker, metricItem, child);
         }
      }

      for (Node child : getChildTagNodes(node, ENTITY_CONFIG)) {
         Config config = maker.buildConfig(child);

         if (linker.onConfig(parent, config)) {
            parseForConfig(maker, linker, config, child);
         }
      }
   }

   public void parseForSubCondition(IMaker<Node> maker, ILinker linker, SubCondition parent, Node node) {
   }
}
