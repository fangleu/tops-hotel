package com.dianping.cat.home.activity.transform;

import static com.dianping.cat.home.activity.Constants.ENTITY_ACTIVITY;
import static com.dianping.cat.home.activity.Constants.ENTITY_ACTIVITY_CONFIG;

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
import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

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

   public ActivityConfig parse(Node node) {
      return parse(new DefaultDomMaker(), new DefaultLinker(false), node);
   }

   public ActivityConfig parse(String xml) throws SAXException, IOException {
      Node doc = getDocument(xml);
      Node rootNode = getChildTagNode(doc, ENTITY_ACTIVITY_CONFIG);

      if (rootNode == null) {
         throw new RuntimeException(String.format("activity-config element(%s) is expected!", ENTITY_ACTIVITY_CONFIG));
      }

      return parse(new DefaultDomMaker(), new DefaultLinker(false), rootNode);
   }

   public ActivityConfig parse(IMaker<Node> maker, ILinker linker, Node node) {
      ActivityConfig activityConfig = maker.buildActivityConfig(node);

      if (node != null) {
         ActivityConfig parent = activityConfig;

         for (Node child : getChildTagNodes(node, ENTITY_ACTIVITY)) {
            Activity activity = maker.buildActivity(child);

            if (linker.onActivity(parent, activity)) {
               parseForActivity(maker, linker, activity, child);
            }
         }
      }

      return activityConfig;
   }

   public void parseForActivity(IMaker<Node> maker, ILinker linker, Activity parent, Node node) {
   }
}
