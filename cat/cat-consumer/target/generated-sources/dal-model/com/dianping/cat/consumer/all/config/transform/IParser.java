package com.dianping.cat.consumer.all.config.transform;

import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public interface IParser<T> {
   public AllConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForName(IMaker<T> maker, ILinker linker, Name parent, T node);

   public void parseForReport(IMaker<T> maker, ILinker linker, Report parent, T node);

   public void parseForType(IMaker<T> maker, ILinker linker, Type parent, T node);
}
