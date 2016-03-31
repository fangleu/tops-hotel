package com.dianping.cat.consumer.metric.model.transform;

import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public interface IMaker<T> {

   public Abtest buildAbtest(T node);

   public String buildDomain(T node);

   public Group buildGroup(T node);

   public MetricItem buildMetricItem(T node);

   public MetricReport buildMetricReport(T node);

   public Point buildPoint(T node);

   public Segment buildSegment(T node);

   public Statistic buildStatistic(T node);

   public StatisticsItem buildStatisticsItem(T node);
}
