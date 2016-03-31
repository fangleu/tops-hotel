package com.dianping.cat.home.heavy.transform;

import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_CACHE;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_CALL;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_REPORT;
import static com.dianping.cat.home.heavy.Constants.ENTITY_HEAVY_SQL;
import static com.dianping.cat.home.heavy.Constants.ENTITY_SERVICE;
import static com.dianping.cat.home.heavy.Constants.ENTITY_URL;

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

import com.dianping.cat.home.heavy.IEntity;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static HeavyReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(HeavyReport.class, is);
   }

   public static HeavyReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static HeavyReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static HeavyReport parse(String xml) throws SAXException, IOException {
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

   private void parseForHeavyCache(HeavyCache parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_URL.equals(qName)) {
         Url url = m_maker.buildUrl(attributes);

         m_linker.onUrl(parentObj, url);
         m_objs.push(url);
      } else if (ENTITY_SERVICE.equals(qName)) {
         Service service = m_maker.buildService(attributes);

         m_linker.onService(parentObj, service);
         m_objs.push(service);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under heavy-cache!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForHeavyCall(HeavyCall parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_URL.equals(qName)) {
         Url url = m_maker.buildUrl(attributes);

         m_linker.onUrl(parentObj, url);
         m_objs.push(url);
      } else if (ENTITY_SERVICE.equals(qName)) {
         Service service = m_maker.buildService(attributes);

         m_linker.onService(parentObj, service);
         m_objs.push(service);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under heavy-call!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForHeavyReport(HeavyReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_HEAVY_SQL.equals(qName)) {
         HeavySql heavySql = m_maker.buildHeavySql(attributes);

         m_linker.onHeavySql(parentObj, heavySql);
         m_objs.push(heavySql);
      } else if (ENTITY_HEAVY_CALL.equals(qName)) {
         HeavyCall heavyCall = m_maker.buildHeavyCall(attributes);

         m_linker.onHeavyCall(parentObj, heavyCall);
         m_objs.push(heavyCall);
      } else if (ENTITY_HEAVY_CACHE.equals(qName)) {
         HeavyCache heavyCache = m_maker.buildHeavyCache(attributes);

         m_linker.onHeavyCache(parentObj, heavyCache);
         m_objs.push(heavyCache);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under heavy-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForHeavySql(HeavySql parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_URL.equals(qName)) {
         Url url = m_maker.buildUrl(attributes);

         m_linker.onUrl(parentObj, url);
         m_objs.push(url);
      } else if (ENTITY_SERVICE.equals(qName)) {
         Service service = m_maker.buildService(attributes);

         m_linker.onService(parentObj, service);
         m_objs.push(service);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under heavy-sql!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForService(Service parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForUrl(Url parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_HEAVY_REPORT.equals(qName)) {
         HeavyReport heavyReport = m_maker.buildHeavyReport(attributes);

         m_entity = heavyReport;
         m_objs.push(heavyReport);
         m_tags.push(qName);
      } else if (ENTITY_HEAVY_SQL.equals(qName)) {
         HeavySql heavySql = m_maker.buildHeavySql(attributes);

         m_entity = heavySql;
         m_objs.push(heavySql);
         m_tags.push(qName);
      } else if (ENTITY_URL.equals(qName)) {
         Url url = m_maker.buildUrl(attributes);

         m_entity = url;
         m_objs.push(url);
         m_tags.push(qName);
      } else if (ENTITY_SERVICE.equals(qName)) {
         Service service = m_maker.buildService(attributes);

         m_entity = service;
         m_objs.push(service);
         m_tags.push(qName);
      } else if (ENTITY_HEAVY_CALL.equals(qName)) {
         HeavyCall heavyCall = m_maker.buildHeavyCall(attributes);

         m_entity = heavyCall;
         m_objs.push(heavyCall);
         m_tags.push(qName);
      } else if (ENTITY_HEAVY_CACHE.equals(qName)) {
         HeavyCache heavyCache = m_maker.buildHeavyCache(attributes);

         m_entity = heavyCache;
         m_objs.push(heavyCache);
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

            if (parent instanceof HeavyReport) {
               parseForHeavyReport((HeavyReport) parent, tag, qName, attributes);
            } else if (parent instanceof HeavySql) {
               parseForHeavySql((HeavySql) parent, tag, qName, attributes);
            } else if (parent instanceof Url) {
               parseForUrl((Url) parent, tag, qName, attributes);
            } else if (parent instanceof Service) {
               parseForService((Service) parent, tag, qName, attributes);
            } else if (parent instanceof HeavyCall) {
               parseForHeavyCall((HeavyCall) parent, tag, qName, attributes);
            } else if (parent instanceof HeavyCache) {
               parseForHeavyCache((HeavyCache) parent, tag, qName, attributes);
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
