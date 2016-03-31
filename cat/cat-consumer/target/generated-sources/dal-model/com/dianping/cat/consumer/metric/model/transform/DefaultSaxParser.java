package com.dianping.cat.consumer.metric.model.transform;

import static com.dianping.cat.consumer.metric.model.Constants.ELEMENT_DOMAIN;
import static com.dianping.cat.consumer.metric.model.Constants.ELEMENT_DOMAINS;

import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_ABTEST;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_GROUP;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_METRIC_ITEM;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_METRIC_REPORT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_POINT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_SEGMENT;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_STATISTIC;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_STATISTICS_ITEM;

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

import com.dianping.cat.consumer.metric.model.IEntity;
import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static MetricReport parse(InputSource is) throws SAXException, IOException {
      return parseEntity(MetricReport.class, is);
   }

   public static MetricReport parse(InputStream in) throws SAXException, IOException {
      return parse(new InputSource(in));
   }

   public static MetricReport parse(Reader reader) throws SAXException, IOException {
      return parse(new InputSource(reader));
   }

   public static MetricReport parse(String xml) throws SAXException, IOException {
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

         if (currentObj instanceof MetricItem) {
            MetricItem metricItem = (MetricItem) currentObj;

            if (ELEMENT_DOMAIN.equals(currentTag)) {
               metricItem.addDomain(getText());
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

   private void parseForAbtest(Abtest parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_GROUP.equals(qName)) {
         Group group = m_maker.buildGroup(attributes);

         m_linker.onGroup(parentObj, group);
         m_objs.push(group);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under abtest!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForGroup(Group parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_POINT.equals(qName)) {
         Point point = m_maker.buildPoint(attributes);

         m_linker.onPoint(parentObj, point);
         m_objs.push(point);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under group!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMetricItem(MetricItem parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ELEMENT_DOMAINS.equals(qName) || ELEMENT_DOMAIN.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_ABTEST.equals(qName)) {
         Abtest abtest = m_maker.buildAbtest(attributes);

         m_linker.onAbtest(parentObj, abtest);
         m_objs.push(abtest);
      } else if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_linker.onSegment(parentObj, segment);
         m_objs.push(segment);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under metric-item!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForMetricReport(MetricReport parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_METRIC_ITEM.equals(qName)) {
         MetricItem metricItem = m_maker.buildMetricItem(attributes);

         m_linker.onMetricItem(parentObj, metricItem);
         m_objs.push(metricItem);
      } else if (ENTITY_STATISTIC.equals(qName)) {
         Statistic statistic = m_maker.buildStatistic(attributes);

         m_linker.onStatistic(parentObj, statistic);
         m_objs.push(statistic);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under metric-report!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForPoint(Point parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForSegment(Segment parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForStatistic(Statistic parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_STATISTICS_ITEM.equals(qName)) {
         StatisticsItem statisticsItem = m_maker.buildStatisticsItem(attributes);

         m_linker.onStatisticsItem(parentObj, statisticsItem);
         m_objs.push(statisticsItem);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under statistic!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForStatisticsItem(StatisticsItem parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_METRIC_REPORT.equals(qName)) {
         MetricReport metricReport = m_maker.buildMetricReport(attributes);

         m_entity = metricReport;
         m_objs.push(metricReport);
         m_tags.push(qName);
      } else if (ENTITY_METRIC_ITEM.equals(qName)) {
         MetricItem metricItem = m_maker.buildMetricItem(attributes);

         m_entity = metricItem;
         m_objs.push(metricItem);
         m_tags.push(qName);
      } else if (ENTITY_SEGMENT.equals(qName)) {
         Segment segment = m_maker.buildSegment(attributes);

         m_entity = segment;
         m_objs.push(segment);
         m_tags.push(qName);
      } else if (ENTITY_ABTEST.equals(qName)) {
         Abtest abtest = m_maker.buildAbtest(attributes);

         m_entity = abtest;
         m_objs.push(abtest);
         m_tags.push(qName);
      } else if (ENTITY_GROUP.equals(qName)) {
         Group group = m_maker.buildGroup(attributes);

         m_entity = group;
         m_objs.push(group);
         m_tags.push(qName);
      } else if (ENTITY_POINT.equals(qName)) {
         Point point = m_maker.buildPoint(attributes);

         m_entity = point;
         m_objs.push(point);
         m_tags.push(qName);
      } else if (ENTITY_STATISTIC.equals(qName)) {
         Statistic statistic = m_maker.buildStatistic(attributes);

         m_entity = statistic;
         m_objs.push(statistic);
         m_tags.push(qName);
      } else if (ENTITY_STATISTICS_ITEM.equals(qName)) {
         StatisticsItem statisticsItem = m_maker.buildStatisticsItem(attributes);

         m_entity = statisticsItem;
         m_objs.push(statisticsItem);
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

            if (parent instanceof MetricReport) {
               parseForMetricReport((MetricReport) parent, tag, qName, attributes);
            } else if (parent instanceof MetricItem) {
               parseForMetricItem((MetricItem) parent, tag, qName, attributes);
            } else if (parent instanceof Segment) {
               parseForSegment((Segment) parent, tag, qName, attributes);
            } else if (parent instanceof Abtest) {
               parseForAbtest((Abtest) parent, tag, qName, attributes);
            } else if (parent instanceof Group) {
               parseForGroup((Group) parent, tag, qName, attributes);
            } else if (parent instanceof Point) {
               parseForPoint((Point) parent, tag, qName, attributes);
            } else if (parent instanceof Statistic) {
               parseForStatistic((Statistic) parent, tag, qName, attributes);
            } else if (parent instanceof StatisticsItem) {
               parseForStatisticsItem((StatisticsItem) parent, tag, qName, attributes);
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
