package com.dianping.cat.home.alert.summary.transform;

import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public interface IParser<T> {
   public AlertSummary parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForAlert(IMaker<T> maker, ILinker linker, Alert parent, T node);

   public void parseForCategory(IMaker<T> maker, ILinker linker, Category parent, T node);
}
