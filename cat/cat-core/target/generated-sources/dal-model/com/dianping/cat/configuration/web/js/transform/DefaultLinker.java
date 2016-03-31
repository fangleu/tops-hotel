package com.dianping.cat.configuration.web.js.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.web.js.entity.Aggregation;
import com.dianping.cat.configuration.web.js.entity.AggregationRule;

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
   public boolean onAggregationRule(final Aggregation parent, final AggregationRule aggregationRule) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addAggregationRule(aggregationRule);
            }
         });
      } else {
         parent.addAggregationRule(aggregationRule);
      }

      return true;
   }
}
