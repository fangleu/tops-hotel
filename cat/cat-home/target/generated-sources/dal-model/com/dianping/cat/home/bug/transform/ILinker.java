package com.dianping.cat.home.bug.transform;

import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public interface ILinker {

   public boolean onDomain(BugReport parent, Domain domain);

   public boolean onExceptionItem(Domain parent, ExceptionItem exceptionItem);
}
