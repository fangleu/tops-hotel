package com.dianping.cat.consumer.metric.model.transform;

import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public interface ILinker {

   public boolean onAbtest(MetricItem parent, Abtest abtest);

   public boolean onGroup(Abtest parent, Group group);

   public boolean onMetricItem(MetricReport parent, MetricItem metricItem);

   public boolean onPoint(Group parent, Point point);

   public boolean onSegment(MetricItem parent, Segment segment);

   public boolean onStatistic(MetricReport parent, Statistic statistic);

   public boolean onStatisticsItem(Statistic parent, StatisticsItem statisticsItem);
}
