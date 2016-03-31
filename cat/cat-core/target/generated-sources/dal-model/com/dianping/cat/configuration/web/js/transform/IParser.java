package com.dianping.cat.configuration.web.js.transform;

import com.dianping.cat.configuration.web.js.entity.Aggregation;
import com.dianping.cat.configuration.web.js.entity.AggregationRule;

public interface IParser<T> {
   public Aggregation parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForAggregationRule(IMaker<T> maker, ILinker linker, AggregationRule parent, T node);
}
