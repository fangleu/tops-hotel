package com.dianping.cat.consumer.heartbeat.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.heartbeat.model.IEntity;
import com.dianping.cat.consumer.heartbeat.model.IVisitor;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private HeartbeatReport m_heartbeatReport;

   public DefaultMerger() {
   }

   public DefaultMerger(HeartbeatReport heartbeatReport) {
      m_heartbeatReport = heartbeatReport;
      m_objs.push(heartbeatReport);
   }

   public HeartbeatReport getHeartbeatReport() {
      return m_heartbeatReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDetail(Detail to, Detail from) {
      to.mergeAttributes(from);
   }

   protected void mergeDisk(Disk to, Disk from) {
      to.mergeAttributes(from);
   }

   protected void mergeExtension(Extension to, Extension from) {
      to.mergeAttributes(from);
   }

   protected void mergeHeartbeatReport(HeartbeatReport to, HeartbeatReport from) {
      to.mergeAttributes(from);
      to.getDomainNames().addAll(from.getDomainNames());
      to.getIps().addAll(from.getIps());
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergePeriod(Period to, Period from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDetail(Detail from) {
      Detail to = (Detail) m_objs.peek();

      mergeDetail(to, from);
      visitDetailChildren(to, from);
   }

   protected void visitDetailChildren(Detail to, Detail from) {
   }

   @Override
   public void visitDisk(Disk from) {
      Disk to = (Disk) m_objs.peek();

      mergeDisk(to, from);
      visitDiskChildren(to, from);
   }

   protected void visitDiskChildren(Disk to, Disk from) {
   }

   @Override
   public void visitExtension(Extension from) {
      Extension to = (Extension) m_objs.peek();

      mergeExtension(to, from);
      visitExtensionChildren(to, from);
   }

   protected void visitExtensionChildren(Extension to, Extension from) {
      for (Detail source : from.getDetails().values()) {
         Detail target = to.findDetail(source.getId());

         if (target == null) {
            target = new Detail(source.getId());
            to.addDetail(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitHeartbeatReport(HeartbeatReport from) {
      HeartbeatReport to = (HeartbeatReport) m_objs.peek();

      mergeHeartbeatReport(to, from);
      visitHeartbeatReportChildren(to, from);
   }

   protected void visitHeartbeatReportChildren(HeartbeatReport to, HeartbeatReport from) {
      for (Machine source : from.getMachines().values()) {
         Machine target = to.findMachine(source.getIp());

         if (target == null) {
            target = new Machine(source.getIp());
            to.addMachine(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMachine(Machine from) {
      Machine to = (Machine) m_objs.peek();

      mergeMachine(to, from);
      visitMachineChildren(to, from);
   }

   protected void visitMachineChildren(Machine to, Machine from) {
      for (Period source : from.getPeriods()) {
         Period target = to.findPeriod(source.getMinute());

         if (target == null) {
            target = new Period(source.getMinute());
            to.addPeriod(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitPeriod(Period from) {
      Period to = (Period) m_objs.peek();

      mergePeriod(to, from);
      visitPeriodChildren(to, from);
   }

   protected void visitPeriodChildren(Period to, Period from) {
      for (Disk source : from.getDisks()) {
         Disk target = to.findDisk(source.getPath());

         if (target == null) {
            target = new Disk(source.getPath());
            to.addDisk(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Extension source : from.getExtensions().values()) {
         Extension target = to.findExtension(source.getId());

         if (target == null) {
            target = new Extension(source.getId());
            to.addExtension(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
