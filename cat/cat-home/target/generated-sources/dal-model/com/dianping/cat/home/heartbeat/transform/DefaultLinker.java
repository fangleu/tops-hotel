package com.dianping.cat.home.heartbeat.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.heartbeat.entity.Group;
import com.dianping.cat.home.heartbeat.entity.HeartbeatDisplayPolicy;
import com.dianping.cat.home.heartbeat.entity.Metric;

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
   public boolean onGroup(final HeartbeatDisplayPolicy parent, final Group group) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addGroup(group);
            }
         });
      } else {
         parent.addGroup(group);
      }

      return true;
   }

   @Override
   public boolean onMetric(final Group parent, final Metric metric) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addMetric(metric);
            }
         });
      } else {
         parent.addMetric(metric);
      }

      return true;
   }
}
