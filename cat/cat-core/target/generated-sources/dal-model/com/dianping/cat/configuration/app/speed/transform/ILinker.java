package com.dianping.cat.configuration.app.speed.transform;

import com.dianping.cat.configuration.app.speed.entity.AppSpeedConfig;
import com.dianping.cat.configuration.app.speed.entity.Speed;

public interface ILinker {

   public boolean onSpeed(AppSpeedConfig parent, Speed speed);
}
