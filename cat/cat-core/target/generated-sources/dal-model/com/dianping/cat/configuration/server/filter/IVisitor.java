package com.dianping.cat.configuration.server.filter;

import com.dianping.cat.configuration.server.filter.entity.CrashLogDomain;
import com.dianping.cat.configuration.server.filter.entity.ServerFilterConfig;

public interface IVisitor {

   public void visitCrashLogDomain(CrashLogDomain crashLogDomain);

   public void visitServerFilterConfig(ServerFilterConfig serverFilterConfig);
}
