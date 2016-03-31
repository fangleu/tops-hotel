package com.dianping.cat.home.utilization.transform;

import java.util.Stack;

import com.dianping.cat.home.utilization.IEntity;
import com.dianping.cat.home.utilization.IVisitor;
import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private UtilizationReport m_utilizationReport;

   public DefaultMerger() {
   }

   public DefaultMerger(UtilizationReport utilizationReport) {
      m_utilizationReport = utilizationReport;
      m_objs.push(utilizationReport);
   }

   public UtilizationReport getUtilizationReport() {
      return m_utilizationReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeApplicationState(ApplicationState to, ApplicationState from) {
      to.mergeAttributes(from);
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
   }

   protected void mergeMachineState(MachineState to, MachineState from) {
      to.mergeAttributes(from);
   }

   protected void mergeUtilizationReport(UtilizationReport to, UtilizationReport from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitApplicationState(ApplicationState from) {
      ApplicationState to = (ApplicationState) m_objs.peek();

      mergeApplicationState(to, from);
      visitApplicationStateChildren(to, from);
   }

   protected void visitApplicationStateChildren(ApplicationState to, ApplicationState from) {
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
      for (MachineState source : from.getMachineStates().values()) {
         MachineState target = to.findMachineState(source.getId());

         if (target == null) {
            target = new MachineState(source.getId());
            to.addMachineState(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (ApplicationState source : from.getApplicationStates().values()) {
         ApplicationState target = to.findApplicationState(source.getId());

         if (target == null) {
            target = new ApplicationState(source.getId());
            to.addApplicationState(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMachineState(MachineState from) {
      MachineState to = (MachineState) m_objs.peek();

      mergeMachineState(to, from);
      visitMachineStateChildren(to, from);
   }

   protected void visitMachineStateChildren(MachineState to, MachineState from) {
   }

   @Override
   public void visitUtilizationReport(UtilizationReport from) {
      UtilizationReport to = (UtilizationReport) m_objs.peek();

      mergeUtilizationReport(to, from);
      visitUtilizationReportChildren(to, from);
   }

   protected void visitUtilizationReportChildren(UtilizationReport to, UtilizationReport from) {
      for (Domain source : from.getDomains().values()) {
         Domain target = to.findDomain(source.getId());

         if (target == null) {
            target = new Domain(source.getId());
            to.addDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
