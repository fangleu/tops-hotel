package com.dianping.cat.home.heartbeat.transform;

import com.dianping.cat.home.heartbeat.entity.Group;
import com.dianping.cat.home.heartbeat.entity.HeartbeatDisplayPolicy;
import com.dianping.cat.home.heartbeat.entity.Metric;

public interface IMaker<T> {

   public Group buildGroup(T node);

   public HeartbeatDisplayPolicy buildHeartbeatDisplayPolicy(T node);

   public Metric buildMetric(T node);
}
