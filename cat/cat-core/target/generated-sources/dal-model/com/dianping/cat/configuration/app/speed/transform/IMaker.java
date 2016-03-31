package com.dianping.cat.configuration.app.speed.transform;

import com.dianping.cat.configuration.app.speed.entity.AppSpeedConfig;
import com.dianping.cat.configuration.app.speed.entity.Speed;

public interface IMaker<T> {

   public AppSpeedConfig buildAppSpeedConfig(T node);

   public Speed buildSpeed(T node);
}
