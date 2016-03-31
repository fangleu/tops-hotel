package com.dianping.cat.consumer.heartbeat.model.transform;

import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public interface IMaker<T> {

   public Detail buildDetail(T node);

   public Disk buildDisk(T node);

   public String buildDomain(T node);

   public Extension buildExtension(T node);

   public HeartbeatReport buildHeartbeatReport(T node);

   public String buildIp(T node);

   public Machine buildMachine(T node);

   public Period buildPeriod(T node);
}
