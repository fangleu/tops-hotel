package com.dianping.cat.consumer.all.config.transform;

import static com.dianping.cat.consumer.all.config.Constants.ENTITY_ALL_CONFIG;
import static com.dianping.cat.consumer.all.config.Constants.ENTITY_NAME;
import static com.dianping.cat.consumer.all.config.Constants.ENTITY_REPORT;
import static com.dianping.cat.consumer.all.config.Constants.ENTITY_TYPE;

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

import com.dianping.cat.consumer.all.config.IEntity;
import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static AllConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(AllConfig.class, is);
   }

   public static AllConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static AllConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static AllConfig parse(String xml) throws SAXException, IOException {
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

   private void parseForAllConfig(AllConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_REPORT.equals(qName)) {
         Report report = m_maker.buildReport(attributes);

         m_linker.onReport(parentObj, report);
         m_objs.push(report);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under all-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForName(Name parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForReport(Report parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_TYPE.equals(qName)) {
         Type type = m_maker.buildType(attributes);

         m_linker.onType(parentObj, type);
         m_objs.push(type);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForType(Type parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_NAME.equals(qName)) {
         Name name = m_maker.buildName(attributes);

         m_linker.onName(parentObj, name);
         m_objs.push(name);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under type!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_ALL_CONFIG.equals(qName)) {
         AllConfig allConfig = m_maker.buildAllConfig(attributes);

         m_entity = allConfig;
         m_objs.push(allConfig);
         m_tags.push(qName);
      } else if (ENTITY_REPORT.equals(qName)) {
         Report report = m_maker.buildReport(attributes);

         m_entity = report;
         m_objs.push(report);
         m_tags.push(qName);
      } else if (ENTITY_TYPE.equals(qName)) {
         Type type = m_maker.buildType(attributes);

         m_entity = type;
         m_objs.push(type);
         m_tags.push(qName);
      } else if (ENTITY_NAME.equals(qName)) {
         Name name = m_maker.buildName(attributes);

         m_entity = name;
         m_objs.push(name);
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

            if (parent instanceof AllConfig) {
               parseForAllConfig((AllConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Report) {
               parseForReport((Report) parent, tag, qName, attributes);
            } else if (parent instanceof Type) {
               parseForType((Type) parent, tag, qName, attributes);
            } else if (parent instanceof Name) {
               parseForName((Name) parent, tag, qName, attributes);
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
