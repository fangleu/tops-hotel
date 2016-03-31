package com.dianping.cat.consumer.all.config.transform;

import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public interface IMaker<T> {

   public AllConfig buildAllConfig(T node);

   public Name buildName(T node);

   public Report buildReport(T node);

   public Type buildType(T node);
}
