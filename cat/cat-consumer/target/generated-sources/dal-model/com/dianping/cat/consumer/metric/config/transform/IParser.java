package com.dianping.cat.consumer.metric.config.transform;

import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

public interface IParser<T> {
   public MetricConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForMetricItemConfig(IMaker<T> maker, ILinker linker, MetricItemConfig parent, T node);

   public void parseForTag(IMaker<T> maker, ILinker linker, Tag parent, T node);
}
