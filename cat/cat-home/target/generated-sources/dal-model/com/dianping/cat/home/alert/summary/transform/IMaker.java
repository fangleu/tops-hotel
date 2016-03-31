package com.dianping.cat.home.alert.summary.transform;

import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public interface IMaker<T> {

   public Alert buildAlert(T node);

   public AlertSummary buildAlertSummary(T node);

   public Category buildCategory(T node);
}
