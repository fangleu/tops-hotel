package com.dianping.cat.consumer.metric.model.transform;

import com.dianping.cat.consumer.metric.model.IVisitor;
import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitAbtest(Abtest abtest) {
      for (Group group : abtest.getGroups().values()) {
         visitGroup(group);
      }
   }

   @Override
   public void visitGroup(Group group) {
      for (Point point : group.getPoints().values()) {
         visitPoint(point);
      }
   }

   @Override
   public void visitMetricItem(MetricItem metricItem) {
      for (Abtest abtest : metricItem.getAbtests().values()) {
         visitAbtest(abtest);
      }

      for (Segment segment : metricItem.getSegments().values()) {
         visitSegment(segment);
      }
   }

   @Override
   public void visitMetricReport(MetricReport metricReport) {
      for (MetricItem metricItem : metricReport.getMetricItems().values()) {
         visitMetricItem(metricItem);
      }

      for (Statistic statistic : metricReport.getStatistics().values()) {
         visitStatistic(statistic);
      }
   }

   @Override
   public void visitPoint(Point point) {
   }

   @Override
   public void visitSegment(Segment segment) {
   }

   @Override
   public void visitStatistic(Statistic statistic) {
      for (StatisticsItem statisticsItem : statistic.getStatisticsItems().values()) {
         visitStatisticsItem(statisticsItem);
      }
   }

   @Override
   public void visitStatisticsItem(StatisticsItem statisticsItem) {
   }
}
