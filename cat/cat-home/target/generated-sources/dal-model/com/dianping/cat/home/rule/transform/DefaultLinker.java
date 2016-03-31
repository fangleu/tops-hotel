package com.dianping.cat.home.rule.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

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
   public boolean onCondition(final Config parent, final Condition condition) {
      parent.addCondition(condition);
      return true;
   }

   @Override
   public boolean onConfig(final Rule parent, final Config config) {
      parent.addConfig(config);
      return true;
   }

   @Override
   public boolean onMetricItem(final Rule parent, final MetricItem metricItem) {
      parent.addMetricItem(metricItem);
      return true;
   }

   @Override
   public boolean onRule(final MonitorRules parent, final Rule rule) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addRule(rule);
            }
         });
      } else {
         parent.addRule(rule);
      }

      return true;
   }

   @Override
   public boolean onSubCondition(final Condition parent, final SubCondition subCondition) {
      parent.addSubCondition(subCondition);
      return true;
   }
}
