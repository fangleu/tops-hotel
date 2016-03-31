package com.dianping.cat.home.utilization.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public class DefaultLinker implements ILinker {
   private boolean m_deferrable;

   private List<Runnable> m_deferedJobs = new ArrayList<Runnable>();

   public DefaultLinker(boolean deferrable) {
      m_deferrable = deferrable;
   }

   public void finish() {
      for (Runnable job : m_deferedJobs) {
         job.run();
      }
   }

   @Override
   public boolean onApplicationState(final Domain parent, final ApplicationState applicationState) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addApplicationState(applicationState);
            }
         });
      } else {
         parent.addApplicationState(applicationState);
      }

      return true;
   }

   @Override
   public boolean onDomain(final UtilizationReport parent, final Domain domain) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addDomain(domain);
            }
         });
      } else {
         parent.addDomain(domain);
      }

      return true;
   }

   @Override
   public boolean onMachineState(final Domain parent, final MachineState machineState) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addMachineState(machineState);
            }
         });
      } else {
         parent.addMachineState(machineState);
      }

      return true;
   }
}
