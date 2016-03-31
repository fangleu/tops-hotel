package com.dianping.cat.home.heartbeat.transform;

import com.dianping.cat.home.heartbeat.entity.Group;
import com.dianping.cat.home.heartbeat.entity.HeartbeatDisplayPolicy;
import com.dianping.cat.home.heartbeat.entity.Metric;

public interface ILinker {

   public boolean onGroup(HeartbeatDisplayPolicy parent, Group group);

   public boolean onMetric(Group parent, Metric metric);
}
