package com.dianping.cat.configuration.app.comparison.transform;

import com.dianping.cat.configuration.app.comparison.entity.AppComparison;
import com.dianping.cat.configuration.app.comparison.entity.AppComparisonConfig;
import com.dianping.cat.configuration.app.comparison.entity.Item;

public interface IParser<T> {
   public AppComparisonConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForAppComparison(IMaker<T> maker, ILinker linker, AppComparison parent, T node);

   public void parseForItem(IMaker<T> maker, ILinker linker, Item parent, T node);
}
