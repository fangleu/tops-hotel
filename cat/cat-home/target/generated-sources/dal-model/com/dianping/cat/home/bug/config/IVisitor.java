package com.dianping.cat.home.bug.config;

import com.dianping.cat.home.bug.config.entity.BugConfig;
import com.dianping.cat.home.bug.config.entity.Domain;

public interface IVisitor {

   public void visitBugConfig(BugConfig bugConfig);

   public void visitDomain(Domain domain);
}
