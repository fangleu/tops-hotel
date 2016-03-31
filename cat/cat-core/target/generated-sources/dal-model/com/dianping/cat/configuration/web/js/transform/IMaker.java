package com.dianping.cat.configuration.web.js.transform;

import com.dianping.cat.configuration.web.js.entity.Aggregation;
import com.dianping.cat.configuration.web.js.entity.AggregationRule;

public interface IMaker<T> {

   public Aggregation buildAggregation(T node);

   public AggregationRule buildAggregationRule(T node);
}
