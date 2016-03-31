package com.dianping.cat.consumer.metric.model;

import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public interface IVisitor {

   public void visitAbtest(Abtest abtest);

   public void visitGroup(Group group);

   public void visitMetricItem(MetricItem metricItem);

   public void visitMetricReport(MetricReport metricReport);

   public void visitPoint(Point point);

   public void visitSegment(Segment segment);

   public void visitStatistic(Statistic statistic);

   public void visitStatisticsItem(StatisticsItem statisticsItem);
}
