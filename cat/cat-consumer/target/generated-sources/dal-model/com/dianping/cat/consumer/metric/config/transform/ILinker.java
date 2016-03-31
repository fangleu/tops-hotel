package com.dianping.cat.consumer.metric.config.transform;

import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

public interface ILinker {

   public boolean onMetricItemConfig(MetricConfig parent, MetricItemConfig metricItemConfig);

   public boolean onTag(MetricItemConfig parent, Tag tag);
}
