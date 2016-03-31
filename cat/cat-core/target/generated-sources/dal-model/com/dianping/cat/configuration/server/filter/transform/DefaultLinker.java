package com.dianping.cat.configuration.server.filter.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.server.filter.entity.CrashLogDomain;
import com.dianping.cat.configuration.server.filter.entity.ServerFilterConfig;

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
   public boolean onCrashLogDomain(final ServerFilterConfig parent, final CrashLogDomain crashLogDomain) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addCrashLogDomain(crashLogDomain);
            }
         });
      } else {
         parent.addCrashLogDomain(crashLogDomain);
      }

      return true;
   }
}
