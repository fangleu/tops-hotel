package com.dianping.cat.status.model.transform;

import static com.dianping.cat.status.model.Constants.ELEMENT_DESCRIPTION;
import static com.dianping.cat.status.model.Constants.ELEMENT_DUMP;
import static com.dianping.cat.status.model.Constants.ELEMENT_JAVA_CLASSPATH;
import static com.dianping.cat.status.model.Constants.ELEMENT_USER_DIR;

import static com.dianping.cat.status.model.Constants.ENTITY_DISK;
import static com.dianping.cat.status.model.Constants.ENTITY_DISK_VOLUME;
import static com.dianping.cat.status.model.Constants.ENTITY_EXTENSION;
import static com.dianping.cat.status.model.Constants.ENTITY_EXTENSIONDETAIL;
import static com.dianping.cat.status.model.Constants.ENTITY_GC;
import static com.dianping.cat.status.model.Constants.ENTITY_MEMORY;
import static com.dianping.cat.status.model.Constants.ENTITY_MESSAGE;
import static com.dianping.cat.status.model.Constants.ENTITY_OS;
import static com.dianping.cat.status.model.Constants.ENTITY_RUNTIME;
import static com.dianping.cat.status.model.Constants.ENTITY_STATUS;
import static com.dianping.cat.status.model.Constants.ENTITY_THREAD;

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

import com.dianping.cat.status.model.IEntity;
import com.dianping.cat.status.model.entity.DiskInfo;
import com.dianping.cat.status.model.entity.DiskVolumeInfo;
import com.dianping.cat.status.model.entity.Extension;
import com.dianping.cat.status.model.entity.ExtensionDetail;
import com.dianping.cat.status.model.entity.GcInfo;
import com.dianping.cat.status.model.entity.MemoryInfo;
import com.dianping.cat.status.model.entity.MessageInfo;
import com.dianping.cat.status.model.entity.OsInfo;
import com.dianping.cat.status.model.entity.RuntimeInfo;
import com.dianping.cat.status.model.entity.StatusInfo;
import com.dianping.cat.status.model.entity.ThreadsInfo;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static StatusInfo parse(InputSource is) throws SAXException, IOException {
      return parseEntity(StatusInfo.class, is);
   }

   public static StatusInfo parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static StatusInfo parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static StatusInfo parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof RuntimeInfo) {
            RuntimeInfo runtime = (RuntimeInfo) currentObj;

            if (ELEMENT_USER_DIR.equals(currentTag)) {
               runtime.setUserDir(getText());
            } else if (ELEMENT_JAVA_CLASSPATH.equals(currentTag)) {
               runtime.setJavaClasspath(getText());
            }
         } else if (currentObj instanceof ThreadsInfo) {
            ThreadsInfo thread = (ThreadsInfo) currentObj;

            if (ELEMENT_DUMP.equals(currentTag)) {
               thread.setDump(getText());
            }
         } else if (currentObj instanceof Extension) {
            Extension extension = (Extension) currentObj;

            if (ELEMENT_DESCRIPTION.equals(currentTag)) {
               extension.setDescription(getText());
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

   private void parseForDisk(DiskInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_DISK_VOLUME.equals(qName)) {
         DiskVolumeInfo diskVolume = m_maker.buildDiskVolume(attributes);

         m_linker.onDiskVolume(parentObj, diskVolume);
         m_objs.push(diskVolume);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under disk!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForDiskVolume(DiskVolumeInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForExtension(Extension parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DESCRIPTION.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_EXTENSIONDETAIL.equals(qName)) {
         ExtensionDetail extensionDetail = m_maker.buildExtensionDetail(attributes);

         m_linker.onExtensionDetail(parentObj, extensionDetail);
         m_objs.push(extensionDetail);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under extension!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForExtensionDetail(ExtensionDetail parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForGc(GcInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForMemory(MemoryInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_GC.equals(qName)) {
         GcInfo gc = m_maker.buildGc(attributes);

         m_linker.onGc(parentObj, gc);
         m_objs.push(gc);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under memory!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMessage(MessageInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForOs(OsInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForRuntime(RuntimeInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_USER_DIR.equals(qName) || ELEMENT_JAVA_CLASSPATH.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under runtime!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForStatus(StatusInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_RUNTIME.equals(qName)) {
         RuntimeInfo runtime = m_maker.buildRuntime(attributes);

         m_linker.onRuntime(parentObj, runtime);
         m_objs.push(runtime);
      } else if (ENTITY_OS.equals(qName)) {
         OsInfo os = m_maker.buildOs(attributes);

         m_linker.onOs(parentObj, os);
         m_objs.push(os);
      } else if (ENTITY_DISK.equals(qName)) {
         DiskInfo disk = m_maker.buildDisk(attributes);

         m_linker.onDisk(parentObj, disk);
         m_objs.push(disk);
      } else if (ENTITY_MEMORY.equals(qName)) {
         MemoryInfo memory = m_maker.buildMemory(attributes);

         m_linker.onMemory(parentObj, memory);
         m_objs.push(memory);
      } else if (ENTITY_THREAD.equals(qName)) {
         ThreadsInfo thread = m_maker.buildThread(attributes);

         m_linker.onThread(parentObj, thread);
         m_objs.push(thread);
      } else if (ENTITY_MESSAGE.equals(qName)) {
         MessageInfo message = m_maker.buildMessage(attributes);

         m_linker.onMessage(parentObj, message);
         m_objs.push(message);
      } else if (ENTITY_EXTENSION.equals(qName)) {
         Extension extension = m_maker.buildExtension(attributes);

         m_linker.onExtension(parentObj, extension);
         m_objs.push(extension);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under status!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForThread(ThreadsInfo parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DUMP.equals(qName)) {
         m_objs.push(parentObj);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under thread!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_STATUS.equals(qName)) {
         StatusInfo status = m_maker.buildStatus(attributes);

         m_entity = status;
         m_objs.push(status);
         m_tags.push(qName);
      } else if (ENTITY_RUNTIME.equals(qName)) {
         RuntimeInfo runtime = m_maker.buildRuntime(attributes);

         m_entity = runtime;
         m_objs.push(runtime);
         m_tags.push(qName);
      } else if (ENTITY_OS.equals(qName)) {
         OsInfo os = m_maker.buildOs(attributes);

         m_entity = os;
         m_objs.push(os);
         m_tags.push(qName);
      } else if (ENTITY_MEMORY.equals(qName)) {
         MemoryInfo memory = m_maker.buildMemory(attributes);

         m_entity = memory;
         m_objs.push(memory);
         m_tags.push(qName);
      } else if (ENTITY_THREAD.equals(qName)) {
         ThreadsInfo thread = m_maker.buildThread(attributes);

         m_entity = thread;
         m_objs.push(thread);
         m_tags.push(qName);
      } else if (ENTITY_DISK.equals(qName)) {
         DiskInfo disk = m_maker.buildDisk(attributes);

         m_entity = disk;
         m_objs.push(disk);
         m_tags.push(qName);
      } else if (ENTITY_DISK_VOLUME.equals(qName)) {
         DiskVolumeInfo diskVolume = m_maker.buildDiskVolume(attributes);

         m_entity = diskVolume;
         m_objs.push(diskVolume);
         m_tags.push(qName);
      } else if (ENTITY_MESSAGE.equals(qName)) {
         MessageInfo message = m_maker.buildMessage(attributes);

         m_entity = message;
         m_objs.push(message);
         m_tags.push(qName);
      } else if (ENTITY_GC.equals(qName)) {
         GcInfo gc = m_maker.buildGc(attributes);

         m_entity = gc;
         m_objs.push(gc);
         m_tags.push(qName);
      } else if (ENTITY_EXTENSION.equals(qName)) {
         Extension extension = m_maker.buildExtension(attributes);

         m_entity = extension;
         m_objs.push(extension);
         m_tags.push(qName);
      } else if (ENTITY_EXTENSIONDETAIL.equals(qName)) {
         ExtensionDetail extensionDetail = m_maker.buildExtensionDetail(attributes);

         m_entity = extensionDetail;
         m_objs.push(extensionDetail);
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

            if (parent instanceof StatusInfo) {
               parseForStatus((StatusInfo) parent, tag, qName, attributes);
            } else if (parent instanceof RuntimeInfo) {
               parseForRuntime((RuntimeInfo) parent, tag, qName, attributes);
            } else if (parent instanceof OsInfo) {
               parseForOs((OsInfo) parent, tag, qName, attributes);
            } else if (parent instanceof MemoryInfo) {
               parseForMemory((MemoryInfo) parent, tag, qName, attributes);
            } else if (parent instanceof ThreadsInfo) {
               parseForThread((ThreadsInfo) parent, tag, qName, attributes);
            } else if (parent instanceof DiskInfo) {
               parseForDisk((DiskInfo) parent, tag, qName, attributes);
            } else if (parent instanceof DiskVolumeInfo) {
               parseForDiskVolume((DiskVolumeInfo) parent, tag, qName, attributes);
            } else if (parent instanceof MessageInfo) {
               parseForMessage((MessageInfo) parent, tag, qName, attributes);
            } else if (parent instanceof GcInfo) {
               parseForGc((GcInfo) parent, tag, qName, attributes);
            } else if (parent instanceof Extension) {
               parseForExtension((Extension) parent, tag, qName, attributes);
            } else if (parent instanceof ExtensionDetail) {
               parseForExtensionDetail((ExtensionDetail) parent, tag, qName, attributes);
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
