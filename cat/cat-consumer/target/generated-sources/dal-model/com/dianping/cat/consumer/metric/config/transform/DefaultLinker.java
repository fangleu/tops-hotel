package com.dianping.cat.consumer.metric.config.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

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
   public boolean onMetricItemConfig(final MetricConfig parent, final MetricItemConfig metricItemConfig) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addMetricItemConfig(metricItemConfig);
            }
         });
      } else {
         parent.addMetricItemConfig(metricItemConfig);
      }

      return true;
   }

   @Override
   public boolean onTag(final MetricItemConfig parent, final Tag tag) {
      parent.addTag(tag);
      return true;
   }
}
