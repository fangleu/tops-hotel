package com.dianping.cat.consumer.heartbeat.model.transform;

import com.dianping.cat.consumer.heartbeat.model.IVisitor;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDetail(Detail detail) {
   }

   @Override
   public void visitDisk(Disk disk) {
   }

   @Override
   public void visitExtension(Extension extension) {
      for (Detail detail : extension.getDetails().values()) {
         visitDetail(detail);
      }
   }

   @Override
   public void visitHeartbeatReport(HeartbeatReport heartbeatReport) {
      for (Machine machine : heartbeatReport.getMachines().values()) {
         visitMachine(machine);
      }
   }

   @Override
   public void visitMachine(Machine machine) {
      for (Period period : machine.getPeriods()) {
         visitPeriod(period);
      }
   }

   @Override
   public void visitPeriod(Period period) {
      for (Disk disk : period.getDisks()) {
         visitDisk(disk);
      }

      for (Extension extension : period.getExtensions().values()) {
         visitExtension(extension);
      }
   }
}
