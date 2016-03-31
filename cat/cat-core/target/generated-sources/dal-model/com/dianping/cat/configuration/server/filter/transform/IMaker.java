package com.dianping.cat.configuration.server.filter.transform;

import com.dianping.cat.configuration.server.filter.entity.CrashLogDomain;
import com.dianping.cat.configuration.server.filter.entity.ServerFilterConfig;

public interface IMaker<T> {

   public CrashLogDomain buildCrashLogDomain(T node);

   public String buildDomain(T node);

   public ServerFilterConfig buildServerFilterConfig(T node);

   public String buildTransactionName(T node);

   public String buildTransactionType(T node);
}
