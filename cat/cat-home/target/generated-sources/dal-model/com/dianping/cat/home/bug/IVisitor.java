package com.dianping.cat.home.bug;

import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public interface IVisitor {

   public void visitBugReport(BugReport bugReport);

   public void visitDomain(Domain domain);

   public void visitExceptionItem(ExceptionItem exceptionItem);
}
