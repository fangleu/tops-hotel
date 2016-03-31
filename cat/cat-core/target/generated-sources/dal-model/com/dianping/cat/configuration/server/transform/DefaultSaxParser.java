package com.dianping.cat.configuration.server.transform;

import static com.dianping.cat.configuration.server.Constants.ELEMENT_REMOTE_SERVERS;

import static com.dianping.cat.configuration.server.Constants.ENTITY_CONFIG;
import static com.dianping.cat.configuration.server.Constants.ENTITY_CONSOLE;
import static com.dianping.cat.configuration.server.Constants.ENTITY_CONSUMER;
import static com.dianping.cat.configuration.server.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.configuration.server.Constants.ENTITY_HDFS;
import static com.dianping.cat.configuration.server.Constants.ENTITY_LDAP;
import static com.dianping.cat.configuration.server.Constants.ENTITY_LONG_CONFIG;
import static com.dianping.cat.configuration.server.Constants.ENTITY_PROPERTY;
import static com.dianping.cat.configuration.server.Constants.ENTITY_STORAGE;
import static com.dianping.cat.configuration.server.Constants.ENTITY_PROPERTIES;

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

import com.dianping.cat.configuration.server.IEntity;
import com.dianping.cat.configuration.server.entity.ConsoleConfig;
import com.dianping.cat.configuration.server.entity.ConsumerConfig;
import com.dianping.cat.configuration.server.entity.Domain;
import com.dianping.cat.configuration.server.entity.HdfsConfig;
import com.dianping.cat.configuration.server.entity.Ldap;
import com.dianping.cat.configuration.server.entity.LongConfig;
import com.dianping.cat.configuration.server.entity.Property;
import com.dianping.cat.configuration.server.entity.ServerConfig;
import com.dianping.cat.configuration.server.entity.StorageConfig;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static ServerConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(ServerConfig.class, is);
   }

   public static ServerConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static ServerConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static ServerConfig parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof ConsoleConfig) {
            ConsoleConfig console = (ConsoleConfig) currentObj;

            if (ELEMENT_REMOTE_SERVERS.equals(currentTag)) {
               console.setRemoteServers(getText());
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

   private void parseForConfig(ServerConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_STORAGE.equals(qName)) {
         StorageConfig storage = m_maker.buildStorage(attributes);

         m_linker.onStorage(parentObj, storage);
         m_objs.push(storage);
      } else if (ENTITY_CONSUMER.equals(qName)) {
         ConsumerConfig consumer = m_maker.buildConsumer(attributes);

         m_linker.onConsumer(parentObj, consumer);
         m_objs.push(consumer);
      } else if (ENTITY_CONSOLE.equals(qName)) {
         ConsoleConfig console = m_maker.buildConsole(attributes);

         m_linker.onConsole(parentObj, console);
         m_objs.push(console);
      } else if (ENTITY_LDAP.equals(qName)) {
         Ldap ldap = m_maker.buildLdap(attributes);

         m_linker.onLdap(parentObj, ldap);
         m_objs.push(ldap);
      } else if (ENTITY_PROPERTY.equals(qName)) {
         Property property = m_maker.buildProperty(attributes);

         m_linker.onProperty(parentObj, property);
         m_objs.push(property);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForConsole(ConsoleConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_REMOTE_SERVERS.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under console!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForConsumer(ConsumerConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_LONG_CONFIG.equals(qName)) {
         LongConfig longConfig = m_maker.buildLongConfig(attributes);

         m_linker.onLongConfig(parentObj, longConfig);
         m_objs.push(longConfig);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under consumer!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForDomain(Domain parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForHdfs(HdfsConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForLdap(Ldap parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForLongConfig(LongConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_linker.onDomain(parentObj, domain);
         m_objs.push(domain);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under long-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForProperty(Property parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForStorage(StorageConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_PROPERTIES.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_HDFS.equals(qName)) {
         HdfsConfig hdfs = m_maker.buildHdfs(attributes);

         m_linker.onHdfs(parentObj, hdfs);
         m_objs.push(hdfs);
      } else if (ENTITY_PROPERTY.equals(qName)) {
         Property property = m_maker.buildProperty(attributes);

         m_linker.onProperty(parentObj, property);
         m_objs.push(property);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under storage!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_CONFIG.equals(qName)) {
         ServerConfig config = m_maker.buildConfig(attributes);

         m_entity = config;
         m_objs.push(config);
         m_tags.push(qName);
      } else if (ENTITY_STORAGE.equals(qName)) {
         StorageConfig storage = m_maker.buildStorage(attributes);

         m_entity = storage;
         m_objs.push(storage);
         m_tags.push(qName);
      } else if (ENTITY_HDFS.equals(qName)) {
         HdfsConfig hdfs = m_maker.buildHdfs(attributes);

         m_entity = hdfs;
         m_objs.push(hdfs);
         m_tags.push(qName);
      } else if (ENTITY_PROPERTY.equals(qName)) {
         Property property = m_maker.buildProperty(attributes);

         m_entity = property;
         m_objs.push(property);
         m_tags.push(qName);
      } else if (ENTITY_CONSUMER.equals(qName)) {
         ConsumerConfig consumer = m_maker.buildConsumer(attributes);

         m_entity = consumer;
         m_objs.push(consumer);
         m_tags.push(qName);
      } else if (ENTITY_LONG_CONFIG.equals(qName)) {
         LongConfig longConfig = m_maker.buildLongConfig(attributes);

         m_entity = longConfig;
         m_objs.push(longConfig);
         m_tags.push(qName);
      } else if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_entity = domain;
         m_objs.push(domain);
         m_tags.push(qName);
      } else if (ENTITY_CONSOLE.equals(qName)) {
         ConsoleConfig console = m_maker.buildConsole(attributes);

         m_entity = console;
         m_objs.push(console);
         m_tags.push(qName);
      } else if (ENTITY_LDAP.equals(qName)) {
         Ldap ldap = m_maker.buildLdap(attributes);

         m_entity = ldap;
         m_objs.push(ldap);
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

            if (parent instanceof ServerConfig) {
               parseForConfig((ServerConfig) parent, tag, qName, attributes);
            } else if (parent instanceof StorageConfig) {
               parseForStorage((StorageConfig) parent, tag, qName, attributes);
            } else if (parent instanceof HdfsConfig) {
               parseForHdfs((HdfsConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Property) {
               parseForProperty((Property) parent, tag, qName, attributes);
            } else if (parent instanceof ConsumerConfig) {
               parseForConsumer((ConsumerConfig) parent, tag, qName, attributes);
            } else if (parent instanceof LongConfig) {
               parseForLongConfig((LongConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Domain) {
               parseForDomain((Domain) parent, tag, qName, attributes);
            } else if (parent instanceof ConsoleConfig) {
               parseForConsole((ConsoleConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Ldap) {
               parseForLdap((Ldap) parent, tag, qName, attributes);
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
