package com.dianping.cat.consumer.metric.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.metric.model.IVisitor;
import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(MetricReport metricReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(metricReport, out);
      return out.toByteArray();
   }

   public static void build(MetricReport metricReport, OutputStream out) {
      metricReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitAbtest(Abtest abtest) {
      if (abtest.getRunId() != null) {
         writeTag(1, 1);
         writeString(abtest.getRunId());
      }

      if (abtest.getName() != null) {
         writeTag(2, 1);
         writeString(abtest.getName());
      }

      if (!abtest.getGroups().isEmpty()) {
         writeTag(33, 2);
         writeInt(abtest.getGroups().size());

         for (Group group : abtest.getGroups().values()) {
            group.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitGroup(Group group) {
      if (group.getName() != null) {
         writeTag(1, 1);
         writeString(group.getName());
      }

      if (!group.getPoints().isEmpty()) {
         writeTag(33, 2);
         writeInt(group.getPoints().size());

         for (Point point : group.getPoints().values()) {
            point.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitMetricItem(MetricItem metricItem) {
      if (metricItem.getId() != null) {
         writeTag(1, 1);
         writeString(metricItem.getId());
      }

      if (metricItem.getType() != null) {
         writeTag(2, 1);
         writeString(metricItem.getType());
      }

      if (!metricItem.getDomains().isEmpty()) {
         writeTag(3, 2);
         writeInt(metricItem.getDomains().size());

         for (String domain : metricItem.getDomains()) {
            writeString(domain);
         }
      }

      if (!metricItem.getAbtests().isEmpty()) {
         writeTag(33, 2);
         writeInt(metricItem.getAbtests().size());

         for (Abtest abtest : metricItem.getAbtests().values()) {
            abtest.accept(m_visitor);
         }
      }

      if (!metricItem.getSegments().isEmpty()) {
         writeTag(34, 2);
         writeInt(metricItem.getSegments().size());

         for (Segment segment : metricItem.getSegments().values()) {
            segment.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitMetricReport(MetricReport metricReport) {
      writeTag(63, 0);

      if (metricReport.getProduct() != null) {
         writeTag(1, 1);
         writeString(metricReport.getProduct());
      }

      if (metricReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(metricReport.getStartTime());
      }

      if (metricReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(metricReport.getEndTime());
      }

      if (!metricReport.getMetricItems().isEmpty()) {
         writeTag(33, 2);
         writeInt(metricReport.getMetricItems().size());

         for (MetricItem metricItem : metricReport.getMetricItems().values()) {
            metricItem.accept(m_visitor);
         }
      }

      if (!metricReport.getStatistics().isEmpty()) {
         writeTag(34, 2);
         writeInt(metricReport.getStatistics().size());

         for (Statistic statistic : metricReport.getStatistics().values()) {
            statistic.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitPoint(Point point) {
      if (point.getId() != null) {
         writeTag(1, 1);
         writeInt(point.getId());
      }

      writeTag(2, 0);
      writeInt(point.getCount());

      writeTag(3, 0);
      writeDouble(point.getSum());

      writeTag(4, 0);
      writeDouble(point.getAvg());

      writeTag(63, 3);
   }

   @Override
   public void visitSegment(Segment segment) {
      if (segment.getId() != null) {
         writeTag(1, 1);
         writeInt(segment.getId());
      }

      writeTag(2, 0);
      writeInt(segment.getCount());

      writeTag(3, 0);
      writeDouble(segment.getSum());

      writeTag(4, 0);
      writeDouble(segment.getAvg());

      writeTag(63, 3);
   }

   @Override
   public void visitStatistic(Statistic statistic) {
      if (statistic.getId() != null) {
         writeTag(1, 1);
         writeString(statistic.getId());
      }

      if (!statistic.getStatisticsItems().isEmpty()) {
         writeTag(33, 2);
         writeInt(statistic.getStatisticsItems().size());

         for (StatisticsItem statisticsItem : statistic.getStatisticsItems().values()) {
            statisticsItem.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitStatisticsItem(StatisticsItem statisticsItem) {
      if (statisticsItem.getId() != null) {
         writeTag(1, 1);
         writeString(statisticsItem.getId());
      }

      writeTag(2, 0);
      writeInt(statisticsItem.getCount());

      writeTag(63, 3);
   }

   private void writeDate(java.util.Date value) {
      try {
         writeVarint(value.getTime());
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeDouble(double value) {
      try {
         m_out.writeDouble(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeString(String value) {
      try {
         m_out.writeUTF(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeTag(int field, int type) {
      try {
         m_out.writeByte((field << 2) + type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected void writeVarint(long value) throws IOException {
      while (true) {
         if ((value & ~0x7FL) == 0) {
            m_out.writeByte((byte) value);
            return;
         } else {
            m_out.writeByte(((byte) value & 0x7F) | 0x80);
            value >>>= 7;
         }
      }
   }
}
