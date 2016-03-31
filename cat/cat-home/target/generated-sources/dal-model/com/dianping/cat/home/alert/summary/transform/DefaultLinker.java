package com.dianping.cat.home.alert.summary.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.alert.summary.entity.Alert;
import com.dianping.cat.home.alert.summary.entity.AlertSummary;
import com.dianping.cat.home.alert.summary.entity.Category;

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
   public boolean onAlert(final Category parent, final Alert alert) {
      parent.addAlert(alert);
      return true;
   }

   @Override
   public boolean onCategory(final AlertSummary parent, final Category category) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addCategory(category);
            }
         });
      } else {
         parent.addCategory(category);
      }

      return true;
   }
}
