package com.dianping.cat.configuration.app.speed.transform;

import com.dianping.cat.configuration.app.speed.entity.AppSpeedConfig;
import com.dianping.cat.configuration.app.speed.entity.Speed;

public interface IParser<T> {
   public AppSpeedConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForSpeed(IMaker<T> maker, ILinker linker, Speed parent, T node);
}
