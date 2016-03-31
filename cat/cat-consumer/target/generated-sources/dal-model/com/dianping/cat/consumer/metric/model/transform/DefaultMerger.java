package com.dianping.cat.consumer.metric.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.metric.model.IEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;
import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private MetricReport m_metricReport;

   public DefaultMerger() {
   }

   public DefaultMerger(MetricReport metricReport) {
      m_metricReport = metricReport;
      m_objs.push(metricReport);
   }

   public MetricReport getMetricReport() {
      return m_metricReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeAbtest(Abtest to, Abtest from) {
      to.mergeAttributes(from);
   }

   protected void mergeGroup(Group to, Group from) {
      to.mergeAttributes(from);
   }

   protected void mergeMetricItem(MetricItem to, MetricItem from) {
      to.mergeAttributes(from);
      to.getDomains().addAll(from.getDomains());
   }

   protected void mergeMetricReport(MetricReport to, MetricReport from) {
      to.mergeAttributes(from);
   }

   protected void mergePoint(Point to, Point from) {
      to.mergeAttributes(from);
   }

   protected void mergeSegment(Segment to, Segment from) {
      to.mergeAttributes(from);
   }

   protected void mergeStatistic(Statistic to, Statistic from) {
      to.mergeAttributes(from);
   }

   protected void mergeStatisticsItem(StatisticsItem to, StatisticsItem from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitAbtest(Abtest from) {
      Abtest to = (Abtest) m_objs.peek();

      mergeAbtest(to, from);
      visitAbtestChildren(to, from);
   }

   protected void visitAbtestChildren(Abtest to, Abtest from) {
      for (Group source : from.getGroups().values()) {
         Group target = to.findGroup(source.getName());

         if (target == null) {
            target = new Group(source.getName());
            to.addGroup(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitGroup(Group from) {
      Group to = (Group) m_objs.peek();

      mergeGroup(to, from);
      visitGroupChildren(to, from);
   }

   protected void visitGroupChildren(Group to, Group from) {
      for (Point source : from.getPoints().values()) {
         Point target = to.findPoint(source.getId());

         if (target == null) {
            target = new Point(source.getId());
            to.addPoint(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMetricItem(MetricItem from) {
      MetricItem to = (MetricItem) m_objs.peek();

      mergeMetricItem(to, from);
      visitMetricItemChildren(to, from);
   }

   protected void visitMetricItemChildren(MetricItem to, MetricItem from) {
      for (Abtest source : from.getAbtests().values()) {
         Abtest target = to.findAbtest(source.getRunId());

         if (target == null) {
            target = new Abtest(source.getRunId());
            to.addAbtest(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Segment source : from.getSegments().values()) {
         Segment target = to.findSegment(source.getId());

         if (target == null) {
            target = new Segment(source.getId());
            to.addSegment(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMetricReport(MetricReport from) {
      MetricReport to = (MetricReport) m_objs.peek();

      mergeMetricReport(to, from);
      visitMetricReportChildren(to, from);
   }

   protected void visitMetricReportChildren(MetricReport to, MetricReport from) {
      for (MetricItem source : from.getMetricItems().values()) {
         MetricItem target = to.findMetricItem(source.getId());

         if (target == null) {
            target = new MetricItem(source.getId());
            to.addMetricItem(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Statistic source : from.getStatistics().values()) {
         Statistic target = to.findStatistic(source.getId());

         if (target == null) {
            target = new Statistic(source.getId());
            to.addStatistic(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitPoint(Point from) {
      Point to = (Point) m_objs.peek();

      mergePoint(to, from);
      visitPointChildren(to, from);
   }

   protected void visitPointChildren(Point to, Point from) {
   }

   @Override
   public void visitSegment(Segment from) {
      Segment to = (Segment) m_objs.peek();

      mergeSegment(to, from);
      visitSegmentChildren(to, from);
   }

   protected void visitSegmentChildren(Segment to, Segment from) {
   }

   @Override
   public void visitStatistic(Statistic from) {
      Statistic to = (Statistic) m_objs.peek();

      mergeStatistic(to, from);
      visitStatisticChildren(to, from);
   }

   protected void visitStatisticChildren(Statistic to, Statistic from) {
      for (StatisticsItem source : from.getStatisticsItems().values()) {
         StatisticsItem target = to.findStatisticsItem(source.getId());

         if (target == null) {
            target = new StatisticsItem(source.getId());
            to.addStatisticsItem(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitStatisticsItem(StatisticsItem from) {
      StatisticsItem to = (StatisticsItem) m_objs.peek();

      mergeStatisticsItem(to, from);
      visitStatisticsItemChildren(to, from);
   }

   protected void visitStatisticsItemChildren(StatisticsItem to, StatisticsItem from) {
   }
}
