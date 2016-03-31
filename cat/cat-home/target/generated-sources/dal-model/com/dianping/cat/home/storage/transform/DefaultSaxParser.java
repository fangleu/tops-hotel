package com.dianping.cat.home.storage.transform;

import static com.dianping.cat.home.storage.Constants.ELEMENT_PAR;
import static com.dianping.cat.home.storage.Constants.ELEMENT_PARS;

import static com.dianping.cat.home.storage.Constants.ENTITY_LINK;
import static com.dianping.cat.home.storage.Constants.ENTITY_MACHINE;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE_GROUP;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE_GROUP_CONFIG;

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

import com.dianping.cat.home.storage.IEntity;
import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static StorageGroupConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(StorageGroupConfig.class, is);
   }

   public static StorageGroupConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static StorageGroupConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static StorageGroupConfig parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof Link) {
            Link link = (Link) currentObj;

            if (ELEMENT_PAR.equals(currentTag)) {
               link.addPar(getText());
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

   private void parseForLink(Link parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_PARS.equals(qName) || ELEMENT_PAR.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under link!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMachine(Machine parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForStorage(Storage parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_linker.onMachine(parentObj, machine);
         m_objs.push(machine);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under storage!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForStorageGroup(StorageGroup parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_LINK.equals(qName)) {
         Link link = m_maker.buildLink(attributes);

         m_linker.onLink(parentObj, link);
         m_objs.push(link);
      } else if (ENTITY_STORAGE.equals(qName)) {
         Storage storage = m_maker.buildStorage(attributes);

         m_linker.onStorage(parentObj, storage);
         m_objs.push(storage);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under storage-group!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForStorageGroupConfig(StorageGroupConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_STORAGE_GROUP.equals(qName)) {
         StorageGroup storageGroup = m_maker.buildStorageGroup(attributes);

         m_linker.onStorageGroup(parentObj, storageGroup);
         m_objs.push(storageGroup);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under storage-group-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_STORAGE_GROUP_CONFIG.equals(qName)) {
         StorageGroupConfig storageGroupConfig = m_maker.buildStorageGroupConfig(attributes);

         m_entity = storageGroupConfig;
         m_objs.push(storageGroupConfig);
         m_tags.push(qName);
      } else if (ENTITY_STORAGE_GROUP.equals(qName)) {
         StorageGroup storageGroup = m_maker.buildStorageGroup(attributes);

         m_entity = storageGroup;
         m_objs.push(storageGroup);
         m_tags.push(qName);
      } else if (ENTITY_LINK.equals(qName)) {
         Link link = m_maker.buildLink(attributes);

         m_entity = link;
         m_objs.push(link);
         m_tags.push(qName);
      } else if (ENTITY_STORAGE.equals(qName)) {
         Storage storage = m_maker.buildStorage(attributes);

         m_entity = storage;
         m_objs.push(storage);
         m_tags.push(qName);
      } else if (ENTITY_MACHINE.equals(qName)) {
         Machine machine = m_maker.buildMachine(attributes);

         m_entity = machine;
         m_objs.push(machine);
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

            if (parent instanceof StorageGroupConfig) {
               parseForStorageGroupConfig((StorageGroupConfig) parent, tag, qName, attributes);
            } else if (parent instanceof StorageGroup) {
               parseForStorageGroup((StorageGroup) parent, tag, qName, attributes);
            } else if (parent instanceof Link) {
               parseForLink((Link) parent, tag, qName, attributes);
            } else if (parent instanceof Storage) {
               parseForStorage((Storage) parent, tag, qName, attributes);
            } else if (parent instanceof Machine) {
               parseForMachine((Machine) parent, tag, qName, attributes);
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
