package com.dianping.cat.home.bug.config.transform;

import com.dianping.cat.home.bug.config.entity.BugConfig;
import com.dianping.cat.home.bug.config.entity.Domain;

public interface IParser<T> {
   public BugConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDomain(IMaker<T> maker, ILinker linker, Domain parent, T node);
}
