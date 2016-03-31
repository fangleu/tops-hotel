package com.dianping.cat.home.bug.config.transform;

import com.dianping.cat.home.bug.config.entity.BugConfig;
import com.dianping.cat.home.bug.config.entity.Domain;

public interface ILinker {

   public boolean onDomain(BugConfig parent, Domain domain);
}
