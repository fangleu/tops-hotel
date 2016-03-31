package com.dianping.cat.home.alert.summary;

import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public interface IVisitor {

   public void visitAlert(Alert alert);

   public void visitAlertSummary(AlertSummary alertSummary);

   public void visitCategory(Category category);
}
