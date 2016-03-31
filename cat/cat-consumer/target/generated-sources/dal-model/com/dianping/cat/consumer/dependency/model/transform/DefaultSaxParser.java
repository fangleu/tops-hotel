package com.dianping.cat.consumer.dependency.model.transform;

import static com.dianping.cat.consumer.dependency.model.Constants.ELEMENT_DOMAINNAME;
import static com.dianping.cat.consumer.dependency.model.Constants.ELEMENT_DOMAIN_NAMES;

import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_DEPENDENCY;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_DEPENDENCY_REPORT;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_INDEX;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_SEGMENT;

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

import com.dianping.cat.consumer.dependency.model.IEntity;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static DependencyReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(DependencyReport.class, is);
   }

   public static DependencyReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static DependencyReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static DependencyReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof DependencyReport) {
            DependencyReport dependencyReport = (DependencyReport) currentObj;

            if (ELEMENT_DOMAINNAME.equals(currentTag)) {
               dependencyReport.addDomainName(getText());
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

   private void parseForDependency(Dependency parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForDependencyReport(DependencyReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DOMAIN_NAMES.equals(qName) || ELEMENT_DOMAINNAME.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_linker.onSegment(parentObj, segment);
         m_objs.push(segment);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under dependency-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForIndex(Index parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForSegment(Segment parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_INDEX.equals(qName)) {
         Index index = m_maker.buildIndex(attributes);

         m_linker.onIndex(parentObj, index);
         m_objs.push(index);
      } else if (ENTITY_DEPENDENCY.equals(qName)) {
         Dependency dependency = m_maker.buildDependency(attributes);

         m_linker.onDependency(parentObj, dependency);
         m_objs.push(dependency);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under segment!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DEPENDENCY_REPORT.equals(qName)) {
         DependencyReport dependencyReport = m_maker.buildDependencyReport(attributes);

         m_entity = dependencyReport;
         m_objs.push(dependencyReport);
         m_tags.push(qName);
      } else if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_entity = segment;
         m_objs.push(segment);
         m_tags.push(qName);
      } else if (ENTITY_INDEX.equals(qName)) {
         Index index = m_maker.buildIndex(attributes);

         m_entity = index;
         m_objs.push(index);
         m_tags.push(qName);
      } else if (ENTITY_DEPENDENCY.equals(qName)) {
         Dependency dependency = m_maker.buildDependency(attributes);

         m_entity = dependency;
         m_objs.push(dependency);
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

            if (parent instanceof DependencyReport) {
               parseForDependencyReport((DependencyReport) parent, tag, qName, attributes);
            } else if (parent instanceof Segment) {
               parseForSegment((Segment) parent, tag, qName, attributes);
            } else if (parent instanceof Index) {
               parseForIndex((Index) parent, tag, qName, attributes);
            } else if (parent instanceof Dependency) {
               parseForDependency((Dependency) parent, tag, qName, attributes);
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
