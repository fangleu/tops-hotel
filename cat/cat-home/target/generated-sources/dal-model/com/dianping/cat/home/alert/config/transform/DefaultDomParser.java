package com.dianping.cat.home.alert.config.transform;

import static com.dianping.cat.home.alert.config.Constants.ELEMENT_EMAIL;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_PHONE;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_WEIXIN;

import static com.dianping.cat.home.alert.config.Constants.ENTITY_ALERT_CONFIG;
import static com.dianping.cat.home.alert.config.Constants.ENTITY_RECEIVER;

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
import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

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

   public AlertConfig parse(Node node) {
      return parse(new DefaultDomMaker(), new DefaultLinker(false), node);
   }

   public AlertConfig parse(String xml) throws SAXException, IOException {
      Node doc = getDocument(xml);
      Node rootNode = getChildTagNode(doc, ENTITY_ALERT_CONFIG);

      if (rootNode == null) {
         throw new RuntimeException(String.format("alert-config element(%s) is expected!", ENTITY_ALERT_CONFIG));
      }

      return parse(new DefaultDomMaker(), new DefaultLinker(false), rootNode);
   }

   public AlertConfig parse(IMaker<Node> maker, ILinker linker, Node node) {
      AlertConfig alertConfig = maker.buildAlertConfig(node);

      if (node != null) {
         AlertConfig parent = alertConfig;

         for (Node child : getChildTagNodes(node, ENTITY_RECEIVER)) {
            Receiver receiver = maker.buildReceiver(child);

            if (linker.onReceiver(parent, receiver)) {
               parseForReceiver(maker, linker, receiver, child);
            }
         }
      }

      return alertConfig;
   }

   public void parseForReceiver(IMaker<Node> maker, ILinker linker, Receiver parent, Node node) {
      for (Node child : getChildTagNodes(node, ELEMENT_EMAIL)) {
         String email = maker.buildEmail(child);

         parent.addEmail(email);
      }

      for (Node child : getChildTagNodes(node, ELEMENT_PHONE)) {
         String phone = maker.buildPhone(child);

         parent.addPhone(phone);
      }

      for (Node child : getChildTagNodes(node, ELEMENT_WEIXIN)) {
         String weixin = maker.buildWeixin(child);

         parent.addWeixin(weixin);
      }
   }
}
