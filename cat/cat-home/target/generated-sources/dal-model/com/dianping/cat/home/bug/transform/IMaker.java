package com.dianping.cat.home.bug.transform;

import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public interface IMaker<T> {

   public BugReport buildBugReport(T node);

   public Domain buildDomain(T node);

   public ExceptionItem buildExceptionItem(T node);

   public String buildMessage(T node);
}
