package com.dianping.cat.home.alert.config.transform;

import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public interface IParser<T> {
   public AlertConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForReceiver(IMaker<T> maker, ILinker linker, Receiver parent, T node);
}
