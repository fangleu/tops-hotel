package com.dianping.cat.configuration.app.comparison.transform;

import com.dianping.cat.configuration.app.comparison.entity.AppComparison;
import com.dianping.cat.configuration.app.comparison.entity.AppComparisonConfig;
import com.dianping.cat.configuration.app.comparison.entity.Item;

public interface IMaker<T> {

   public AppComparison buildAppComparison(T node);

   public AppComparisonConfig buildAppComparisonConfig(T node);

   public Item buildItem(T node);
}
