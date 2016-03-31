package com.dianping.cat.consumer.matrix.model.transform;

import static com.dianping.cat.consumer.matrix.model.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.consumer.matrix.model.Constants.ELEMENT_DOMAIN_NAMES;

import static com.dianping.cat.consumer.matrix.model.Constants.ENTITY_MATRIX;
import static com.dianping.cat.consumer.matrix.model.Constants.ENTITY_MATRIX_REPORT;
import static com.dianping.cat.consumer.matrix.model.Constants.ENTITY_RATIO;

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

import com.dianping.cat.consumer.matrix.model.IEntity;
import com.dianping.cat.consumer.matrix.model.entity.Matrix;
import com.dianping.cat.consumer.matrix.model.entity.MatrixReport;
import com.dianping.cat.consumer.matrix.model.entity.Ratio;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static MatrixReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(MatrixReport.class, is);
   }

   public static MatrixReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static MatrixReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static MatrixReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof MatrixReport) {
            MatrixReport matrixReport = (MatrixReport) currentObj;

            if (ELEMENT_DOMAIN.equals(currentTag)) {
               matrixReport.addDomain(getText());
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

   private void parseForMatrix(Matrix parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_RATIO.equals(qName)) {
         Ratio ratio = m_maker.buildRatio(attributes);

         m_linker.onRatio(parentObj, ratio);
         m_objs.push(ratio);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under matrix!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMatrixReport(MatrixReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DOMAIN_NAMES.equals(qName) || ELEMENT_DOMAIN.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_MATRIX.equals(qName)) {
         Matrix matrix = m_maker.buildMatrix(attributes);

         m_linker.onMatrix(parentObj, matrix);
         m_objs.push(matrix);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under matrix-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForRatio(Ratio parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_MATRIX_REPORT.equals(qName)) {
         MatrixReport matrixReport = m_maker.buildMatrixReport(attributes);

         m_entity = matrixReport;
         m_objs.push(matrixReport);
         m_tags.push(qName);
      } else if (ENTITY_MATRIX.equals(qName)) {
         Matrix matrix = m_maker.buildMatrix(attributes);

         m_entity = matrix;
         m_objs.push(matrix);
         m_tags.push(qName);
      } else if (ENTITY_RATIO.equals(qName)) {
         Ratio ratio = m_maker.buildRatio(attributes);

         m_entity = ratio;
         m_objs.push(ratio);
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

            if (parent instanceof MatrixReport) {
               parseForMatrixReport((MatrixReport) parent, tag, qName, attributes);
            } else if (parent instanceof Matrix) {
               parseForMatrix((Matrix) parent, tag, qName, attributes);
            } else if (parent instanceof Ratio) {
               parseForRatio((Ratio) parent, tag, qName, attributes);
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
