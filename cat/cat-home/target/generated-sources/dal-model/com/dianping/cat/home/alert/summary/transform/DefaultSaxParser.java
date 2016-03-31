package com.dianping.cat.home.alert.summary.transform;

import static com.dianping.cat.home.alert.summary.Constants.ENTITY_ALERT;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_ALERT_SUMMARY;
import static com.dianping.cat.home.alert.summary.Constants.ENTITY_CATEGORY;

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

import com.dianping.cat.home.alert.summary.IEntity;
import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static AlertSummary parse(InputSource is) throws SAXException, IOException {
      return parseEntity(AlertSummary.class, is);
   }

   public static AlertSummary parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static AlertSummary parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static AlertSummary parse(String xml) throws SAXException, IOException {
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
         m_objs.pop();
         m_tags.pop();

      }

      m_text.setLength(0);
   }

   private IEntity<?> getEntity() {
      return m_entity;
   }

   protected String getText() {
      return m_text.toString();
   }

   private void parseForAlert(Alert parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForAlertSummary(AlertSummary parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_CATEGORY.equals(qName)) {
         Category category = m_maker.buildCategory(attributes);

         m_linker.onCategory(parentObj, category);
         m_objs.push(category);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under alert-summary!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForCategory(Category parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_ALERT.equals(qName)) {
         Alert alert = m_maker.buildAlert(attributes);

         m_linker.onAlert(parentObj, alert);
         m_objs.push(alert);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under category!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_ALERT_SUMMARY.equals(qName)) {
         AlertSummary alertSummary = m_maker.buildAlertSummary(attributes);

         m_entity = alertSummary;
         m_objs.push(alertSummary);
         m_tags.push(qName);
      } else if (ENTITY_CATEGORY.equals(qName)) {
         Category category = m_maker.buildCategory(attributes);

         m_entity = category;
         m_objs.push(category);
         m_tags.push(qName);
      } else if (ENTITY_ALERT.equals(qName)) {
         Alert alert = m_maker.buildAlert(attributes);

         m_entity = alert;
         m_objs.push(alert);
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

            if (parent instanceof AlertSummary) {
               parseForAlertSummary((AlertSummary) parent, tag, qName, attributes);
            } else if (parent instanceof Category) {
               parseForCategory((Category) parent, tag, qName, attributes);
            } else if (parent instanceof Alert) {
               parseForAlert((Alert) parent, tag, qName, attributes);
            } else {
               throw new RuntimeException(String.format("Unknown entity(%s) under %s!", qName, parent.getClass().getName()));
            }
         }

         m_text.setLength(0);
        } else {
         throw new SAXException(String.format("Namespace(%s) is not supported by %s.", uri, this.getClass().getName()));
      }
   }

   protected java.util.Date toDate(String str, String format) {
      try {
         return new java.text.SimpleDateFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse date(%s) in format(%s)!", str, format), e);
      }
   }
}
