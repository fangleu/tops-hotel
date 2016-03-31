package com.dianping.cat.home.utilization.transform;

import com.dianping.cat.home.utilization.IVisitor;
import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitApplicationState(ApplicationState applicationState) {
   }

   @Override
   public void visitDomain(Domain domain) {
      for (MachineState machineState : domain.getMachineStates().values()) {
         visitMachineState(machineState);
      }

      for (ApplicationState applicationState : domain.getApplicationStates().values()) {
         visitApplicationState(applicationState);
      }
   }

   @Override
   public void visitMachineState(MachineState machineState) {
   }

   @Override
   public void visitUtilizationReport(UtilizationReport utilizationReport) {
      for (Domain domain : utilizationReport.getDomains().values()) {
         visitDomain(domain);
      }
   }
}
