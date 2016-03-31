package com.dianping.cat.consumer.storage.model.transform;

import static com.dianping.cat.consumer.storage.model.Constants.ELEMENT_ID;
import static com.dianping.cat.consumer.storage.model.Constants.ELEMENT_IDS;
import static com.dianping.cat.consumer.storage.model.Constants.ELEMENT_IP;
import static com.dianping.cat.consumer.storage.model.Constants.ELEMENT_IPS;
import static com.dianping.cat.consumer.storage.model.Constants.ELEMENT_OP;
import static com.dianping.cat.consumer.storage.model.Constants.ELEMENT_OPS;

import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_MACHINE;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_OPERATION;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_SEGMENT;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_SQL;
import static com.dianping.cat.consumer.storage.model.Constants.ENTITY_STORAGE_REPORT;

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

import com.dianping.cat.consumer.storage.model.IEntity;
import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static StorageReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(StorageReport.class, is);
   }

   public static StorageReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static StorageReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static StorageReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof StorageReport) {
            StorageReport storageReport = (StorageReport) currentObj;

            if (ELEMENT_ID.equals(currentTag)) {
               storageReport.addId(getText());
            } else if (ELEMENT_IP.equals(currentTag)) {
               storageReport.addIp(getText());
            } else if (ELEMENT_OP.equals(currentTag)) {
               storageReport.addOp(getText());
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

   private void parseForDomain(Domain parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_OPERATION.equals(qName)) {
         Operation operation = m_maker.buildOperation(attributes);

         m_linker.onOperation(parentObj, operation);
         m_objs.push(operation);
      } else if (ENTITY_SQL.equals(qName)) {
         Sql sql = m_maker.buildSql(attributes);

         m_linker.onSql(parentObj, sql);
         m_objs.push(sql);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under domain!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMachine(Machine parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_linker.onDomain(parentObj, domain);
         m_objs.push(domain);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under machine!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForOperation(Operation parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_linker.onSegment(parentObj, segment);
         m_objs.push(segment);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under operation!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForSegment(Segment parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForSql(Sql parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForStorageReport(StorageReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_IDS.equals(qName) || ELEMENT_ID.equals(qName) || ELEMENT_IPS.equals(qName) || ELEMENT_IP.equals(qName) || ELEMENT_OPS.equals(qName) || ELEMENT_OP.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_linker.onMachine(parentObj, machine);
         m_objs.push(machine);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under storage-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_STORAGE_REPORT.equals(qName)) {
         StorageReport storageReport = m_maker.buildStorageReport(attributes);

         m_entity = storageReport;
         m_objs.push(storageReport);
         m_tags.push(qName);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_entity = machine;
         m_objs.push(machine);
         m_tags.push(qName);
      } else if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_entity = domain;
         m_objs.push(domain);
         m_tags.push(qName);
      } else if (ENTITY_OPERATION.equals(qName)) {
         Operation operation = m_maker.buildOperation(attributes);

         m_entity = operation;
         m_objs.push(operation);
         m_tags.push(qName);
      } else if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_entity = segment;
         m_objs.push(segment);
         m_tags.push(qName);
      } else if (ENTITY_SQL.equals(qName)) {
         Sql sql = m_maker.buildSql(attributes);

         m_entity = sql;
         m_objs.push(sql);
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

            if (parent instanceof StorageReport) {
               parseForStorageReport((StorageReport) parent, tag, qName, attributes);
            } else if (parent instanceof Machine) {
               parseForMachine((Machine) parent, tag, qName, attributes);
            } else if (parent instanceof Domain) {
               parseForDomain((Domain) parent, tag, qName, attributes);
            } else if (parent instanceof Operation) {
               parseForOperation((Operation) parent, tag, qName, attributes);
            } else if (parent instanceof Segment) {
               parseForSegment((Segment) parent, tag, qName, attributes);
            } else if (parent instanceof Sql) {
               parseForSql((Sql) parent, tag, qName, attributes);
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
