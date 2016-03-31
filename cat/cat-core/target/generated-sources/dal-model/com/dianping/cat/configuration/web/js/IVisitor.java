package com.dianping.cat.configuration.web.js;

import com.dianping.cat.configuration.web.js.entity.Aggregation;
import com.dianping.cat.configuration.web.js.entity.AggregationRule;

public interface IVisitor {

   public void visitAggregation(Aggregation aggregation);

   public void visitAggregationRule(AggregationRule aggregationRule);
}
