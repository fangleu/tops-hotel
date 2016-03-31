package com.dianping.cat.configuration.app.speed;

import com.dianping.cat.configuration.app.speed.entity.AppSpeedConfig;
import com.dianping.cat.configuration.app.speed.entity.Speed;

public interface IVisitor {

   public void visitAppSpeedConfig(AppSpeedConfig appSpeedConfig);

   public void visitSpeed(Speed speed);
}
