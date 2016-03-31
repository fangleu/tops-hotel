package com.dianping.cat.consumer.metric.config.transform;

import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

public interface IMaker<T> {

   public MetricConfig buildMetricConfig(T node);

   public MetricItemConfig buildMetricItemConfig(T node);

   public Tag buildTag(T node);
}
