package com.dianping.cat.home.bug.transform;

import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public interface IParser<T> {
   public BugReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDomain(IMaker<T> maker, ILinker linker, Domain parent, T node);

   public void parseForExceptionItem(IMaker<T> maker, ILinker linker, ExceptionItem parent, T node);
}
