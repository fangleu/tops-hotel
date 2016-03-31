package com.dianping.cat.home.heartbeat.transform;

import com.dianping.cat.home.heartbeat.entity.Group;
import com.dianping.cat.home.heartbeat.entity.HeartbeatDisplayPolicy;
import com.dianping.cat.home.heartbeat.entity.Metric;

public interface IParser<T> {
   public HeartbeatDisplayPolicy parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForGroup(IMaker<T> maker, ILinker linker, Group parent, T node);

   public void parseForMetric(IMaker<T> maker, ILinker linker, Metric parent, T node);
}
