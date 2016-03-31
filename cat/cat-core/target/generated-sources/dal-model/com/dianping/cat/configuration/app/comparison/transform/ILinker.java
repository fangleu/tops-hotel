package com.dianping.cat.configuration.app.comparison.transform;

import com.dianping.cat.configuration.app.comparison.entity.AppComparison;
import com.dianping.cat.configuration.app.comparison.entity.AppComparisonConfig;
import com.dianping.cat.configuration.app.comparison.entity.Item;

public interface ILinker {

   public boolean onAppComparison(AppComparisonConfig parent, AppComparison appComparison);

   public boolean onItem(AppComparison parent, Item item);
}
