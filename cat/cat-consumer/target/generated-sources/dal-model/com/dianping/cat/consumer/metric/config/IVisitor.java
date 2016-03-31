package com.dianping.cat.consumer.metric.config;

import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

public interface IVisitor {

   public void visitMetricConfig(MetricConfig metricConfig);

   public void visitMetricItemConfig(MetricItemConfig metricItemConfig);

   public void visitTag(Tag tag);
}
