package com.dianping.cat.consumer.heartbeat.model.transform;

import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public interface ILinker {

   public boolean onDetail(Extension parent, Detail detail);

   public boolean onDisk(Period parent, Disk disk);

   public boolean onExtension(Period parent, Extension extension);

   public boolean onMachine(HeartbeatReport parent, Machine machine);

   public boolean onPeriod(Machine parent, Period period);
}
