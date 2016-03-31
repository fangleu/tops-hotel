package com.dianping.cat.home.alert.summary.transform;

import static com.dianping.cat.home.alert.summary.Constants.ENTITY_ALERT;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_ALERT_SUMMARY;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_CATEGORY;

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
import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

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

   public AlertSummary parse(Node node) {
      return parse(new DefaultDomMaker(), new DefaultLinker(false), node);
   }

   public AlertSummary parse(String xml) throws SAXException, IOException {
      Node doc = getDocument(xml);
      Node rootNode = getChildTagNode(doc, ENTITY_ALERT_SUMMARY);

      if (rootNode == null) {
         throw new RuntimeException(String.format("alert-summary element(%s) is expected!", ENTITY_ALERT_SUMMARY));
      }

      return parse(new DefaultDomMaker(), new DefaultLinker(false), rootNode);
   }

   public AlertSummary parse(IMaker<Node> maker, ILinker linker, Node node) {
      AlertSummary alertSummary = maker.buildAlertSummary(node);

      if (node != null) {
         AlertSummary parent = alertSummary;

         for (Node child : getChildTagNodes(node, ENTITY_CATEGORY)) {
            Category category = maker.buildCategory(child);

            if (linker.onCategory(parent, category)) {
               parseForCategory(maker, linker, category, child);
            }
         }
      }

      return alertSummary;
   }

   public void parseForAlert(IMaker<Node> maker, ILinker linker, Alert parent, Node node) {
   }

   public void parseForCategory(IMaker<Node> maker, ILinker linker, Category parent, Node node) {
      for (Node child : getChildTagNodes(node, ENTITY_ALERT)) {
         Alert alert = maker.buildAlert(child);

         if (linker.onAlert(parent, alert)) {
            parseForAlert(maker, linker, alert, child);
         }
      }
   }
}
