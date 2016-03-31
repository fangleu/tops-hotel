package com.dianping.cat.consumer.heartbeat.model.transform;

import static com.dianping.cat.consumer.heartbeat.model.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ELEMENT_DOMAIN_NAMES;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ELEMENT_IP;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ELEMENT_IPS;

import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_DETAIL;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_DISK;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_EXTENSION;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_HEARTBEAT_REPORT;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_MACHINE;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_PERIOD;

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

import com.dianping.cat.consumer.heartbeat.model.IEntity;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static HeartbeatReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(HeartbeatReport.class, is);
   }

   public static HeartbeatReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static HeartbeatReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static HeartbeatReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof HeartbeatReport) {
            HeartbeatReport heartbeatReport = (HeartbeatReport) currentObj;

            if (ELEMENT_DOMAIN.equals(currentTag)) {
               heartbeatReport.addDomain(getText());
            } else if (ELEMENT_IP.equals(currentTag)) {
               heartbeatReport.addIp(getText());
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

   private void parseForDetail(Detail parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForDisk(Disk parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForExtension(Extension parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DETAIL.equals(qName)) {
         Detail detail = m_maker.buildDetail(attributes);

         m_linker.onDetail(parentObj, detail);
         m_objs.push(detail);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under extension!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForHeartbeatReport(HeartbeatReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DOMAIN_NAMES.equals(qName) || ELEMENT_DOMAIN.equals(qName) || ELEMENT_IPS.equals(qName) || ELEMENT_IP.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_linker.onMachine(parentObj, machine);
         m_objs.push(machine);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under heartbeat-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMachine(Machine parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_PERIOD.equals(qName)) {
         Period period = m_maker.buildPeriod(attributes);

         m_linker.onPeriod(parentObj, period);
         m_objs.push(period);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under machine!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForPeriod(Period parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DISK.equals(qName)) {
         Disk disk = m_maker.buildDisk(attributes);

         m_linker.onDisk(parentObj, disk);
         m_objs.push(disk);
      } else if (ENTITY_EXTENSION.equals(qName)) {
         Extension extension = m_maker.buildExtension(attributes);

         m_linker.onExtension(parentObj, extension);
         m_objs.push(extension);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under period!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_HEARTBEAT_REPORT.equals(qName)) {
         HeartbeatReport heartbeatReport = m_maker.buildHeartbeatReport(attributes);

         m_entity = heartbeatReport;
         m_objs.push(heartbeatReport);
         m_tags.push(qName);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_entity = machine;
         m_objs.push(machine);
         m_tags.push(qName);
      } else if (ENTITY_PERIOD.equals(qName)) {
         Period period = m_maker.buildPeriod(attributes);

         m_entity = period;
         m_objs.push(period);
         m_tags.push(qName);
      } else if (ENTITY_DISK.equals(qName)) {
         Disk disk = m_maker.buildDisk(attributes);

         m_entity = disk;
         m_objs.push(disk);
         m_tags.push(qName);
      } else if (ENTITY_EXTENSION.equals(qName)) {
         Extension extension = m_maker.buildExtension(attributes);

         m_entity = extension;
         m_objs.push(extension);
         m_tags.push(qName);
      } else if (ENTITY_DETAIL.equals(qName)) {
         Detail detail = m_maker.buildDetail(attributes);

         m_entity = detail;
         m_objs.push(detail);
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

            if (parent instanceof HeartbeatReport) {
               parseForHeartbeatReport((HeartbeatReport) parent, tag, qName, attributes);
            } else if (parent instanceof Machine) {
               parseForMachine((Machine) parent, tag, qName, attributes);
            } else if (parent instanceof Period) {
               parseForPeriod((Period) parent, tag, qName, attributes);
            } else if (parent instanceof Disk) {
               parseForDisk((Disk) parent, tag, qName, attributes);
            } else if (parent instanceof Extension) {
               parseForExtension((Extension) parent, tag, qName, attributes);
            } else if (parent instanceof Detail) {
               parseForDetail((Detail) parent, tag, qName, attributes);
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
