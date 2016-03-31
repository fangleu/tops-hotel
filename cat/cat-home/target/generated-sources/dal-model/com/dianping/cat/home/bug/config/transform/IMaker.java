package com.dianping.cat.home.bug.config.transform;

import com.dianping.cat.home.bug.config.entity.BugConfig;
import com.dianping.cat.home.bug.config.entity.Domain;

public interface IMaker<T> {

   public BugConfig buildBugConfig(T node);

   public Domain buildDomain(T node);

   public String buildException(T node);
}
