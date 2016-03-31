package com.dianping.cat.consumer.problem.model.transform;

import static com.dianping.cat.consumer.problem.model.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.consumer.problem.model.Constants.ELEMENT_DOMAIN_NAMES;
import static com.dianping.cat.consumer.problem.model.Constants.ELEMENT_IP;
import static com.dianping.cat.consumer.problem.model.Constants.ELEMENT_IPS;
import static com.dianping.cat.consumer.problem.model.Constants.ELEMENT_MESSAGE;
import static com.dianping.cat.consumer.problem.model.Constants.ELEMENT_MESSAGES;

import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_DURATION;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_ENTITY;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_ENTRY;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_MACHINE;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_PROBLEM_REPORT;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_SEGMENT;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_THREAD;

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

import com.dianping.cat.consumer.problem.model.IEntity;
import com.dianping.cat.consumer.problem.model.entity.Duration;
import com.dianping.cat.consumer.problem.model.entity.Entity;
import com.dianping.cat.consumer.problem.model.entity.Entry;
import com.dianping.cat.consumer.problem.model.entity.JavaThread;
import com.dianping.cat.consumer.problem.model.entity.Machine;
import com.dianping.cat.consumer.problem.model.entity.ProblemReport;
import com.dianping.cat.consumer.problem.model.entity.Segment;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static ProblemReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(ProblemReport.class, is);
   }

   public static ProblemReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static ProblemReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static ProblemReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof ProblemReport) {
            ProblemReport problemReport = (ProblemReport) currentObj;

            if (ELEMENT_DOMAIN.equals(currentTag)) {
               problemReport.addDomain(getText());
            } else if (ELEMENT_IP.equals(currentTag)) {
               problemReport.addIp(getText());
            }
         } else if (currentObj instanceof Segment) {
            Segment segment = (Segment) currentObj;

            if (ELEMENT_MESSAGE.equals(currentTag)) {
               segment.addMessage(getText());
            }
         } else if (currentObj instanceof Duration) {
            Duration duration = (Duration) currentObj;

            if (ELEMENT_MESSAGE.equals(currentTag)) {
               duration.addMessage(getText());
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

   private void parseForDuration(Duration parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_MESSAGES.equals(qName) || ELEMENT_MESSAGE.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under duration!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForEntity(Entity parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_THREAD.equals(qName)) {
         JavaThread thread = m_maker.buildThread(attributes);

         m_linker.onThread(parentObj, thread);
         m_objs.push(thread);
      } else if (ENTITY_DURATION.equals(qName)) {
         Duration duration = m_maker.buildDuration(attributes);

         m_linker.onDuration(parentObj, duration);
         m_objs.push(duration);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under entity!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForEntry(Entry parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DURATION.equals(qName)) {
         Duration duration = m_maker.buildDuration(attributes);

         m_linker.onDuration(parentObj, duration);
         m_objs.push(duration);
      } else if (ENTITY_THREAD.equals(qName)) {
         JavaThread thread = m_maker.buildThread(attributes);

         m_linker.onThread(parentObj, thread);
         m_objs.push(thread);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under entry!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMachine(Machine parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_ENTRY.equals(qName)) {
         Entry entry = m_maker.buildEntry(attributes);

         m_linker.onEntry(parentObj, entry);
         m_objs.push(entry);
      } else if (ENTITY_ENTITY.equals(qName)) {
         Entity entity = m_maker.buildEntity(attributes);

         m_linker.onEntity(parentObj, entity);
         m_objs.push(entity);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under machine!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForProblemReport(ProblemReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DOMAIN_NAMES.equals(qName) || ELEMENT_DOMAIN.equals(qName) || ELEMENT_IPS.equals(qName) || ELEMENT_IP.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_linker.onMachine(parentObj, machine);
         m_objs.push(machine);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under problem-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForSegment(Segment parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_MESSAGES.equals(qName) || ELEMENT_MESSAGE.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under segment!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForThread(JavaThread parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_linker.onSegment(parentObj, segment);
         m_objs.push(segment);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under thread!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_PROBLEM_REPORT.equals(qName)) {
         ProblemReport problemReport = m_maker.buildProblemReport(attributes);

         m_entity = problemReport;
         m_objs.push(problemReport);
         m_tags.push(qName);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_entity = machine;
         m_objs.push(machine);
         m_tags.push(qName);
      } else if (ENTITY_ENTRY.equals(qName)) {
         Entry entry = m_maker.buildEntry(attributes);

         m_entity = entry;
         m_objs.push(entry);
         m_tags.push(qName);
      } else if (ENTITY_THREAD.equals(qName)) {
         JavaThread thread = m_maker.buildThread(attributes);

         m_entity = thread;
         m_objs.push(thread);
         m_tags.push(qName);
      } else if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_entity = segment;
         m_objs.push(segment);
         m_tags.push(qName);
      } else if (ENTITY_DURATION.equals(qName)) {
         Duration duration = m_maker.buildDuration(attributes);

         m_entity = duration;
         m_objs.push(duration);
         m_tags.push(qName);
      } else if (ENTITY_ENTITY.equals(qName)) {
         Entity entity = m_maker.buildEntity(attributes);

         m_entity = entity;
         m_objs.push(entity);
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

            if (parent instanceof ProblemReport) {
               parseForProblemReport((ProblemReport) parent, tag, qName, attributes);
            } else if (parent instanceof Machine) {
               parseForMachine((Machine) parent, tag, qName, attributes);
            } else if (parent instanceof Entry) {
               parseForEntry((Entry) parent, tag, qName, attributes);
            } else if (parent instanceof JavaThread) {
               parseForThread((JavaThread) parent, tag, qName, attributes);
            } else if (parent instanceof Segment) {
               parseForSegment((Segment) parent, tag, qName, attributes);
            } else if (parent instanceof Duration) {
               parseForDuration((Duration) parent, tag, qName, attributes);
            } else if (parent instanceof Entity) {
               parseForEntity((Entity) parent, tag, qName, attributes);
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
