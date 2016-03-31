package com.dianping.cat.home.alert.summary.transform;

import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public interface ILinker {

   public boolean onAlert(Category parent, Alert alert);

   public boolean onCategory(AlertSummary parent, Category category);
}
