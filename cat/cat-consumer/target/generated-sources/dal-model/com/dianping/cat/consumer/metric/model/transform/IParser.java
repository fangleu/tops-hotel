package com.dianping.cat.consumer.metric.model.transform;

import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

public interface IParser<T> {
   public MetricReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForAbtest(IMaker<T> maker, ILinker linker, Abtest parent, T node);

   public void parseForGroup(IMaker<T> maker, ILinker linker, Group parent, T node);

   public void parseForMetricItem(IMaker<T> maker, ILinker linker, MetricItem parent, T node);

   public void parseForPoint(IMaker<T> maker, ILinker linker, Point parent, T node);

   public void parseForSegment(IMaker<T> maker, ILinker linker, Segment parent, T node);

   public void parseForStatistic(IMaker<T> maker, ILinker linker, Statistic parent, T node);

   public void parseForStatisticsItem(IMaker<T> maker, ILinker linker, StatisticsItem parent, T node);
}
