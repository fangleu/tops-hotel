package com.dianping.cat.home.bug.transform;

import com.dianping.cat.home.bug.IVisitor;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitBugReport(BugReport bugReport) {
      for (Domain domain : bugReport.getDomains().values()) {
         visitDomain(domain);
      }
   }

   @Override
   public void visitDomain(Domain domain) {
      for (ExceptionItem exceptionItem : domain.getExceptionItems().values()) {
         visitExceptionItem(exceptionItem);
      }
   }

   @Override
   public void visitExceptionItem(ExceptionItem exceptionItem) {
   }
}
