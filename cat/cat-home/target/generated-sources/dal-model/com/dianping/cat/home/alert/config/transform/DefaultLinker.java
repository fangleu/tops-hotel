package com.dianping.cat.home.alert.config.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

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
   public boolean onReceiver(final AlertConfig parent, final Receiver receiver) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addReceiver(receiver);
            }
         });
      } else {
         parent.addReceiver(receiver);
      }

      return true;
   }
}
