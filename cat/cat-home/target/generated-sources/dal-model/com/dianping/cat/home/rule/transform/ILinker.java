package com.dianping.cat.home.rule.transform;

import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public interface ILinker {

   public boolean onCondition(Config parent, Condition condition);

   public boolean onConfig(Rule parent, Config config);

   public boolean onMetricItem(Rule parent, MetricItem metricItem);

   public boolean onRule(MonitorRules parent, Rule rule);

   public boolean onSubCondition(Condition parent, SubCondition subCondition);
}
