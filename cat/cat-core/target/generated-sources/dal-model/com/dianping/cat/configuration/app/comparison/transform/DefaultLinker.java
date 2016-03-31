package com.dianping.cat.configuration.app.comparison.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.app.comparison.entity.AppComparison;
import com.dianping.cat.configuration.app.comparison.entity.AppComparisonConfig;
import com.dianping.cat.configuration.app.comparison.entity.Item;

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
   public boolean onAppComparison(final AppComparisonConfig parent, final AppComparison appComparison) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addAppComparison(appComparison);
            }
         });
      } else {
         parent.addAppComparison(appComparison);
      }

      return true;
   }

   @Override
   public boolean onItem(final AppComparison parent, final Item item) {
      parent.addItem(item);
      return true;
   }
}
