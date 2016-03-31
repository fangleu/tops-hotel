package com.dianping.cat.configuration.web.js.transform;

import com.dianping.cat.configuration.web.js.entity.Aggregation;
import com.dianping.cat.configuration.web.js.entity.AggregationRule;

public interface ILinker {

   public boolean onAggregationRule(Aggregation parent, AggregationRule aggregationRule);
}
