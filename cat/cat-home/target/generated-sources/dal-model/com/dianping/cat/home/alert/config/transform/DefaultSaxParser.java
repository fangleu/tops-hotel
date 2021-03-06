package com.dianping.cat.home.alert.config.transform;

import static com.dianping.cat.home.alert.config.Constants.ELEMENT_EMAIL;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_EMAILS;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_PHONE;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_PHONES;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_WEIXIN;
import static com.dianping.cat.home.alert.config.Constants.ELEMENT_WEIXINS;

import static com.dianping.cat.home.alert.config.Constants.ENTITY_ALERT_CONFIG;
import static com.dianping.cat.home.alert.config.Constants.ENTITY_RECEIVER;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dianping.cat.home.alert.config.IEntity;
import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static AlertConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(AlertConfig.class, is);
   }

   public static AlertConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static AlertConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static AlertConfig parse(String xml) throws SAXException, IOException {
      return parse(new InputSource(new StringReader(xml)));
   }

   public static <T extends IEntity<?>> T parseEntity(Class<T> type, String xml) throws SAXException, IOException {
      return parseEntity(type, new InputSource(new StringReader(xml)));
   }

   @SuppressWarnings("unchecked")
   public static <T extends IEntity<?>> T parseEntity(Class<T> type, InputSource is) throws SAXException, IOException {
      try {
         DefaultSaxParser handler = new DefaultSaxParser();
         SAXParserFactory factory = SAXParserFactory.newInstance();

         factory.setValidating(false);
         factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
         factory.setFeature("http://xml.org/sax/features/validation", false);

         factory.newSAXParser().parse(is, handler);
         return (T) handler.getEntity();
      } catch (ParserConfigurationException e) {
         throw new IllegalStateException("Unable to get SAX parser instance!", e);
      }
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

   @Override
   public void characters(char[] ch, int start, int length) throws SAXException {
      m_text.append(ch, start, length);
   }

   @Override
   public void endDocument() throws SAXException {
      m_linker.finish();
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (uri == null || uri.length() == 0) {
         Object currentObj = m_objs.pop();
         String currentTag = m_tags.pop();

         if (currentObj instanceof Receiver) {
            Receiver receiver = (Receiver) currentObj;

            if (ELEMENT_EMAIL.equals(currentTag)) {
               receiver.addEmail(getText());
            } else if (ELEMENT_PHONE.equals(currentTag)) {
               receiver.addPhone(getText());
            } else if (ELEMENT_WEIXIN.equals(currentTag)) {
               receiver.addWeixin(getText());
            }
         }
      }

      m_text.setLength(0);
   }

   private IEntity<?> getEntity() {
      return m_entity;
   }

   protected String getText() {
      return m_text.toString();
   }

   private void parseForAlertConfig(AlertConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_RECEIVER.equals(qName)) {
         Receiver receiver = m_maker.buildReceiver(attributes);

         m_linker.onReceiver(parentObj, receiver);
         m_objs.push(receiver);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under alert-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForReceiver(Receiver parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_EMAILS.equals(qName) || ELEMENT_EMAIL.equals(qName) || ELEMENT_PHONES.equals(qName) || ELEMENT_PHONE.equals(qName) || ELEMENT_WEIXINS.equals(qName) || ELEMENT_WEIXIN.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under receiver!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_ALERT_CONFIG.equals(qName)) {
         AlertConfig alertConfig = m_maker.buildAlertConfig(attributes);

         m_entity = alertConfig;
         m_objs.push(alertConfig);
         m_tags.push(qName);
      } else if (ENTITY_RECEIVER.equals(qName)) {
         Receiver receiver = m_maker.buildReceiver(attributes);

         m_entity = receiver;
         m_objs.push(receiver);
         m_tags.push(qName);
      } else {
         throw new SAXException("Unknown root element(" + qName + ") found!");
      }
   }

   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if (uri == null || uri.length() == 0) {
         if (m_objs.isEmpty()) { // root
            parseRoot(qName, attributes);
         } else {
            Object parent = m_objs.peek();
            String tag = m_tags.peek();

            if (parent instanceof AlertConfig) {
               parseForAlertConfig((AlertConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Receiver) {
               parseForReceiver((Receiver) parent, tag, qName, attributes);
            } else {
               throw new RuntimeException(String.format("Unknown entity(%s) under %s!", qName, parent.getClass().getName()));
            }
         }

         m_text.setLength(0);
        } else {
         throw new SAXException(String.format("Namespace(%s) is not supported by %s.", uri, this.getClass().getName()));
      }
   }
}
