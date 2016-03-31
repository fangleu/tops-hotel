package com.dianping.cat.configuration.client.transform;

import static com.dianping.cat.configuration.client.Constants.ELEMENT_BASE_LOG_DIR;

import static com.dianping.cat.configuration.client.Constants.ENTITY_BIND;
import static com.dianping.cat.configuration.client.Constants.ENTITY_CONFIG;
import static com.dianping.cat.configuration.client.Constants.ENTITY_DOMAIN;
import static com.dianping.cat.configuration.client.Constants.ENTITY_PROPERTY;
import static com.dianping.cat.configuration.client.Constants.ENTITY_SERVER;
import static com.dianping.cat.configuration.client.Constants.ENTITY_PROPERTIES;
import static com.dianping.cat.configuration.client.Constants.ENTITY_SERVERS;

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

import com.dianping.cat.configuration.client.IEntity;
import com.dianping.cat.configuration.client.entity.Bind;
import com.dianping.cat.configuration.client.entity.ClientConfig;
import com.dianping.cat.configuration.client.entity.Domain;
import com.dianping.cat.configuration.client.entity.Property;
import com.dianping.cat.configuration.client.entity.Server;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static ClientConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(ClientConfig.class, is);
   }

   public static ClientConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static ClientConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static ClientConfig parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof ClientConfig) {
            ClientConfig config = (ClientConfig) currentObj;

            if (ELEMENT_BASE_LOG_DIR.equals(currentTag)) {
               config.setBaseLogDir(getText());
            }
         } else if (currentObj instanceof Property) {
            Property property = (Property) currentObj;

            property.setText(getText());
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

   private void parseForBind(Bind parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForConfig(ClientConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_SERVERS.equals(qName) || ENTITY_PROPERTIES.equals(qName) || ELEMENT_BASE_LOG_DIR.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_SERVER.equals(qName)) {
         Server server = m_maker.buildServer(attributes);

         m_linker.onServer(parentObj, server);
         m_objs.push(server);
      } else if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_linker.onDomain(parentObj, domain);
         m_objs.push(domain);
      } else if (ENTITY_BIND.equals(qName)) {
         Bind bind = m_maker.buildBind(attributes);

         m_linker.onBind(parentObj, bind);
         m_objs.push(bind);
      } else if (ENTITY_PROPERTY.equals(qName)) {
         Property property = m_maker.buildProperty(attributes);

         m_linker.onProperty(parentObj, property);
         m_objs.push(property);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForDomain(Domain parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForProperty(Property parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForServer(Server parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_CONFIG.equals(qName)) {
         ClientConfig config = m_maker.buildConfig(attributes);

         m_entity = config;
         m_objs.push(config);
         m_tags.push(qName);
      } else if (ENTITY_SERVER.equals(qName)) {
         Server server = m_maker.buildServer(attributes);

         m_entity = server;
         m_objs.push(server);
         m_tags.push(qName);
      } else if (ENTITY_DOMAIN.equals(qName)) {
         Domain domain = m_maker.buildDomain(attributes);

         m_entity = domain;
         m_objs.push(domain);
         m_tags.push(qName);
      } else if (ENTITY_BIND.equals(qName)) {
         Bind bind = m_maker.buildBind(attributes);

         m_entity = bind;
         m_objs.push(bind);
         m_tags.push(qName);
      } else if (ENTITY_PROPERTY.equals(qName)) {
         Property property = m_maker.buildProperty(attributes);

         m_entity = property;
         m_objs.push(property);
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

            if (parent instanceof ClientConfig) {
               parseForConfig((ClientConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Server) {
               parseForServer((Server) parent, tag, qName, attributes);
            } else if (parent instanceof Domain) {
               parseForDomain((Domain) parent, tag, qName, attributes);
            } else if (parent instanceof Bind) {
               parseForBind((Bind) parent, tag, qName, attributes);
            } else if (parent instanceof Property) {
               parseForProperty((Property) parent, tag, qName, attributes);
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
