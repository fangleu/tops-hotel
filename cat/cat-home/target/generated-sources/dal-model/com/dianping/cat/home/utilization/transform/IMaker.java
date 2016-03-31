package com.dianping.cat.home.utilization.transform;

import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public interface IMaker<T> {

   public ApplicationState buildApplicationState(T node);

   public Domain buildDomain(T node);

   public MachineState buildMachineState(T node);

   public UtilizationReport buildUtilizationReport(T node);
}
