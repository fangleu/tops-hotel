package com.dianping.cat.home.heartbeat;

import com.dianping.cat.home.heartbeat.entity.Group;
import com.dianping.cat.home.heartbeat.entity.HeartbeatDisplayPolicy;
import com.dianping.cat.home.heartbeat.entity.Metric;

public interface IVisitor {

   public void visitGroup(Group group);

   public void visitHeartbeatDisplayPolicy(HeartbeatDisplayPolicy heartbeatDisplayPolicy);

   public void visitMetric(Metric metric);
}
