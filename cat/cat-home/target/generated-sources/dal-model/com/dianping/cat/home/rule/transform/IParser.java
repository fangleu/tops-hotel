package com.dianping.cat.home.rule.transform;

import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public interface IParser<T> {
   public MonitorRules parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForCondition(IMaker<T> maker, ILinker linker, Condition parent, T node);

   public void parseForConfig(IMaker<T> maker, ILinker linker, Config parent, T node);

   public void parseForMetricItem(IMaker<T> maker, ILinker linker, MetricItem parent, T node);

   public void parseForRule(IMaker<T> maker, ILinker linker, Rule parent, T node);

   public void parseForSubCondition(IMaker<T> maker, ILinker linker, SubCondition parent, T node);
}
