package com.dianping.cat.configuration.app.command.transform;

import static com.dianping.cat.configuration.app.command.Constants.ENTITY_COMMAND;
import static com.dianping.cat.configuration.app.command.Constants.ENTITY_COMMAND_FORMAT;
import static com.dianping.cat.configuration.app.command.Constants.ENTITY_RULE;

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

import com.dianping.cat.configuration.app.command.IEntity;
import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static CommandFormat parse(InputSource is) throws SAXException, IOException {
      return parseEntity(CommandFormat.class, is);
   }

   public static CommandFormat parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static CommandFormat parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static CommandFormat parse(String xml) throws SAXException, IOException {
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

   private void parseForCommand(Command parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForCommandFormat(CommandFormat parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_RULE.equals(qName)) {
         Rule rule = m_maker.buildRule(attributes);

         m_linker.onRule(parentObj, rule);
         m_objs.push(rule);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under command-format!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForRule(Rule parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_COMMAND.equals(qName)) {
         Command command = m_maker.buildCommand(attributes);

         m_linker.onCommand(parentObj, command);
         m_objs.push(command);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under rule!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_COMMAND_FORMAT.equals(qName)) {
         CommandFormat commandFormat = m_maker.buildCommandFormat(attributes);

         m_entity = commandFormat;
         m_objs.push(commandFormat);
         m_tags.push(qName);
      } else if (ENTITY_RULE.equals(qName)) {
         Rule rule = m_maker.buildRule(attributes);

         m_entity = rule;
         m_objs.push(rule);
         m_tags.push(qName);
      } else if (ENTITY_COMMAND.equals(qName)) {
         Command command = m_maker.buildCommand(attributes);

         m_entity = command;
         m_objs.push(command);
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

            if (parent instanceof CommandFormat) {
               parseForCommandFormat((CommandFormat) parent, tag, qName, attributes);
            } else if (parent instanceof Rule) {
               parseForRule((Rule) parent, tag, qName, attributes);
            } else if (parent instanceof Command) {
               parseForCommand((Command) parent, tag, qName, attributes);
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
