package com.dianping.cat.home.rule;

import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public interface IVisitor {

   public void visitCondition(Condition condition);

   public void visitConfig(Config config);

   public void visitMetricItem(MetricItem metricItem);

   public void visitMonitorRules(MonitorRules monitorRules);

   public void visitRule(Rule rule);

   public void visitSubCondition(SubCondition subCondition);
}
