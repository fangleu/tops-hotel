package com.dianping.cat.home.bug.transform;

import static com.dianping.cat.home.bug.Constants.ELEMENT_EXCPETION;
import static com.dianping.cat.home.bug.Constants.ELEMENT_MESSAGE;
import static com.dianping.cat.home.bug.Constants.ELEMENT_MESSAGES;
import static com.dianping.cat.home.bug.Constants.ELEMENT_PROBLEM_URL;

import static com.dianping.cat.home.bug.Constants.ENTITY_BUG_REPORT;
import static com.dianping.cat.home.bug.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.home.bug.Constants.ENTITY_EXCEPTION_ITEM;

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

import com.dianping.cat.home.bug.IEntity;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static BugReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(BugReport.class, is);
   }

   public static BugReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static BugReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static BugReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof Domain) {
            Domain domain = (Domain) currentObj;

            if (ELEMENT_PROBLEM_URL.equals(currentTag)) {
               domain.setProblemUrl(getText());
            } else if (ELEMENT_EXCPETION.equals(currentTag)) {
               domain.setExcpetion(getText());
            }
         } else if (currentObj instanceof ExceptionItem) {
            ExceptionItem exceptionItem = (ExceptionItem) currentObj;

            if (ELEMENT_MESSAGE.equals(currentTag)) {
               exceptionItem.addMessage(getText());
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

   private void parseForBugReport(BugReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_linker.onDomain(parentObj, domain);
         m_objs.push(domain);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under bug-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForDomain(Domain parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_PROBLEM_URL.equals(qName) || ELEMENT_EXCPETION.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_EXCEPTION_ITEM.equals(qName)) {
         ExceptionItem exceptionItem = m_maker.buildExceptionItem(attributes);

         m_linker.onExceptionItem(parentObj, exceptionItem);
         m_objs.push(exceptionItem);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under domain!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForExceptionItem(ExceptionItem parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_MESSAGES.equals(qName) || ELEMENT_MESSAGE.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under exception-item!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_BUG_REPORT.equals(qName)) {
         BugReport bugReport = m_maker.buildBugReport(attributes);

         m_entity = bugReport;
         m_objs.push(bugReport);
         m_tags.push(qName);
      } else if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_entity = domain;
         m_objs.push(domain);
         m_tags.push(qName);
      } else if (ENTITY_EXCEPTION_ITEM.equals(qName)) {
         ExceptionItem exceptionItem = m_maker.buildExceptionItem(attributes);

         m_entity = exceptionItem;
         m_objs.push(exceptionItem);
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

            if (parent instanceof BugReport) {
               parseForBugReport((BugReport) parent, tag, qName, attributes);
            } else if (parent instanceof Domain) {
               parseForDomain((Domain) parent, tag, qName, attributes);
            } else if (parent instanceof ExceptionItem) {
               parseForExceptionItem((ExceptionItem) parent, tag, qName, attributes);
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
