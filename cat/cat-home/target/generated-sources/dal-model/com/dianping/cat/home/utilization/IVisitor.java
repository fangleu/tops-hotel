package com.dianping.cat.home.utilization;

import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public interface IVisitor {

   public void visitApplicationState(ApplicationState applicationState);

   public void visitDomain(Domain domain);

   public void visitMachineState(MachineState machineState);

   public void visitUtilizationReport(UtilizationReport utilizationReport);
}
