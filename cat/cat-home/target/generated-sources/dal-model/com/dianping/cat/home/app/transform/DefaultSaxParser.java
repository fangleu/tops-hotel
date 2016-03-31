package com.dianping.cat.home.app.transform;

import static com.dianping.cat.home.app.Constants.ENTITY_APP_REPORT;
import static com.dianping.cat.home.app.Constants.ENTITY_CODE;
import static com.dianping.cat.home.app.Constants.ENTITY_COMMAND;
import static com.dianping.cat.home.app.Constants.ENTITY_TRANSACTION;

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

import com.dianping.cat.home.app.IEntity;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static AppReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(AppReport.class, is);
   }

   public static AppReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static AppReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static AppReport parse(String xml) throws SAXException, IOException {
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

   private void parseForAppReport(AppReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_COMMAND.equals(qName)) {
         Command command = m_maker.buildCommand(attributes);

         m_linker.onCommand(parentObj, command);
         m_objs.push(command);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under app-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForCode(Code parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForCommand(Command parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_TRANSACTION.equals(qName)) {
         Transaction transaction = m_maker.buildTransaction(attributes);

         m_linker.onTransaction(parentObj, transaction);
         m_objs.push(transaction);
      } else if (ENTITY_CODE.equals(qName)) {
         Code code = m_maker.buildCode(attributes);

         m_linker.onCode(parentObj, code);
         m_objs.push(code);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under command!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForTransaction(Transaction parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_APP_REPORT.equals(qName)) {
         AppReport appReport = m_maker.buildAppReport(attributes);

         m_entity = appReport;
         m_objs.push(appReport);
         m_tags.push(qName);
      } else if (ENTITY_COMMAND.equals(qName)) {
         Command command = m_maker.buildCommand(attributes);

         m_entity = command;
         m_objs.push(command);
         m_tags.push(qName);
      } else if (ENTITY_TRANSACTION.equals(qName)) {
         Transaction transaction = m_maker.buildTransaction(attributes);

         m_entity = transaction;
         m_objs.push(transaction);
         m_tags.push(qName);
      } else if (ENTITY_CODE.equals(qName)) {
         Code code = m_maker.buildCode(attributes);

         m_entity = code;
         m_objs.push(code);
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

            if (parent instanceof AppReport) {
               parseForAppReport((AppReport) parent, tag, qName, attributes);
            } else if (parent instanceof Command) {
               parseForCommand((Command) parent, tag, qName, attributes);
            } else if (parent instanceof Transaction) {
               parseForTransaction((Transaction) parent, tag, qName, attributes);
            } else if (parent instanceof Code) {
               parseForCode((Code) parent, tag, qName, attributes);
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

   protected Number toNumber(String str, String format) {
      try {
         return new java.text.DecimalFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse number(%s) in format(%s)!", str, format), e);
      }
   }
}
