package com.dianping.cat.home.alert.policy.transform;

import static com.dianping.cat.home.alert.policy.Constants.ENTITY_ALERT_POLICY;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_GROUP;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_LEVEL;
import static com.dianping.cat.home.alert.policy.Constants.ENTITY_TYPE;

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
import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

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

   public AlertPolicy parse(Node node) {
      return parse(new DefaultDomMaker(), new DefaultLinker(false), node);
   }

   public AlertPolicy parse(String xml) throws SAXException, IOException {
      Node doc = getDocument(xml);
      Node rootNode = getChildTagNode(doc, ENTITY_ALERT_POLICY);

      if (rootNode == null) {
         throw new RuntimeException(String.format("alert-policy element(%s) is expected!", ENTITY_ALERT_POLICY));
      }

      return parse(new DefaultDomMaker(), new DefaultLinker(false), rootNode);
   }

   public AlertPolicy parse(IMaker<Node> maker, ILinker linker, Node node) {
      AlertPolicy alertPolicy = maker.buildAlertPolicy(node);

      if (node != null) {
         AlertPolicy parent = alertPolicy;

         for (Node child : getChildTagNodes(node, ENTITY_TYPE)) {
            Type type = maker.buildType(child);

            if (linker.onType(parent, type)) {
               parseForType(maker, linker, type, child);
            }
         }
      }

      return alertPolicy;
   }

   public void parseForGroup(IMaker<Node> maker, ILinker linker, Group parent, Node node) {
      for (Node child : getChildTagNodes(node, ENTITY_LEVEL)) {
         Level level = maker.buildLevel(child);

         if (linker.onLevel(parent, level)) {
            parseForLevel(maker, linker, level, child);
         }
      }
   }

   public void parseForLevel(IMaker<Node> maker, ILinker linker, Level parent, Node node) {
   }

   public void parseForType(IMaker<Node> maker, ILinker linker, Type parent, Node node) {
      for (Node child : getChildTagNodes(node, ENTITY_GROUP)) {
         Group group = maker.buildGroup(child);

         if (linker.onGroup(parent, group)) {
            parseForGroup(maker, linker, group, child);
         }
      }
   }
}
