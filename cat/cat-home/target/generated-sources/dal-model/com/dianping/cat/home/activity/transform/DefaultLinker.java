package com.dianping.cat.home.activity.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public class DefaultLinker implements ILinker {
   @SuppressWarnings("unused")
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
   public boolean onActivity(final ActivityConfig parent, final Activity activity) {
      parent.addActivity(activity);
      return true;
   }
}
