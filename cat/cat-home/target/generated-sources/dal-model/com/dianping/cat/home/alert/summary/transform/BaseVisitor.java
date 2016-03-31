package com.dianping.cat.home.alert.summary.transform;

import com.dianping.cat.home.alert.summary.IVisitor;
import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitAlert(Alert alert) {
   }

   @Override
   public void visitAlertSummary(AlertSummary alertSummary) {
      for (Category category : alertSummary.getCategories().values()) {
         visitCategory(category);
      }
   }

   @Override
   public void visitCategory(Category category) {
      for (Alert alert : category.getAlerts()) {
         visitAlert(alert);
      }
   }
}
