package com.dianping.cat.consumer.all.config.transform;

import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public interface ILinker {

   public boolean onName(Type parent, Name name);

   public boolean onReport(AllConfig parent, Report report);

   public boolean onType(Report parent, Type type);
}
