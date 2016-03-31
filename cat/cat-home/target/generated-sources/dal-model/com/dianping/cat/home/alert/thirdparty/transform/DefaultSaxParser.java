package com.dianping.cat.home.alert.thirdparty.transform;

import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_HTTP;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_PAR;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_SOCKET;
import static com.dianping.cat.home.alert.thirdparty.Constants.ENTITY_THIRD_PARTY_CONFIG;

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

import com.dianping.cat.home.alert.thirdparty.IEntity;
import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static ThirdPartyConfig parse(InputSource is) throws SAXException, IOException {
      return parseEntity(ThirdPartyConfig.class, is);
   }

   public static ThirdPartyConfig parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static ThirdPartyConfig parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static ThirdPartyConfig parse(String xml) throws SAXException, IOException {
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

   private void parseForHttp(Http parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_PAR.equals(qName)) {
         Par par = m_maker.buildPar(attributes);

         m_linker.onPar(parentObj, par);
         m_objs.push(par);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under http!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForPar(Par parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForSocket(Socket parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForThirdPartyConfig(ThirdPartyConfig parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_HTTP.equals(qName)) {
         Http http = m_maker.buildHttp(attributes);

         m_linker.onHttp(parentObj, http);
         m_objs.push(http);
      } else if (ENTITY_SOCKET.equals(qName)) {
         Socket socket = m_maker.buildSocket(attributes);

         m_linker.onSocket(parentObj, socket);
         m_objs.push(socket);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under third-party-config!", qName));
      }

      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_THIRD_PARTY_CONFIG.equals(qName)) {
         ThirdPartyConfig thirdPartyConfig = m_maker.buildThirdPartyConfig(attributes);

         m_entity = thirdPartyConfig;
         m_objs.push(thirdPartyConfig);
         m_tags.push(qName);
      } else if (ENTITY_HTTP.equals(qName)) {
         Http http = m_maker.buildHttp(attributes);

         m_entity = http;
         m_objs.push(http);
         m_tags.push(qName);
      } else if (ENTITY_PAR.equals(qName)) {
         Par par = m_maker.buildPar(attributes);

         m_entity = par;
         m_objs.push(par);
         m_tags.push(qName);
      } else if (ENTITY_SOCKET.equals(qName)) {
         Socket socket = m_maker.buildSocket(attributes);

         m_entity = socket;
         m_objs.push(socket);
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

            if (parent instanceof ThirdPartyConfig) {
               parseForThirdPartyConfig((ThirdPartyConfig) parent, tag, qName, attributes);
            } else if (parent instanceof Http) {
               parseForHttp((Http) parent, tag, qName, attributes);
            } else if (parent instanceof Par) {
               parseForPar((Par) parent, tag, qName, attributes);
            } else if (parent instanceof Socket) {
               parseForSocket((Socket) parent, tag, qName, attributes);
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
