package com.dianping.cat.configuration.app.comparison;

import com.dianping.cat.configuration.app.comparison.entity.AppComparison;
import com.dianping.cat.configuration.app.comparison.entity.AppComparisonConfig;
import com.dianping.cat.configuration.app.comparison.entity.Item;

public interface IVisitor {

   public void visitAppComparison(AppComparison appComparison);

   public void visitAppComparisonConfig(AppComparisonConfig appComparisonConfig);

   public void visitItem(Item item);
}
