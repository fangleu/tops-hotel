package com.dianping.cat.consumer.metric.model.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.consumer.metric.model.entity.Abtest;
import com.dianping.cat.consumer.metric.model.entity.Group;
import com.dianping.cat.consumer.metric.model.entity.MetricItem;
import com.dianping.cat.consumer.metric.model.entity.MetricReport;
import com.dianping.cat.consumer.metric.model.entity.Point;
import com.dianping.cat.consumer.metric.model.entity.Segment;
import com.dianping.cat.consumer.metric.model.entity.Statistic;
import com.dianping.cat.consumer.metric.model.entity.StatisticsItem;

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
   public boolean onAbtest(final MetricItem parent, final Abtest abtest) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addAbtest(abtest);
            }
         });
      } else {
         parent.addAbtest(abtest);
      }

      return true;
   }

   @Override
   public boolean onGroup(final Abtest parent, final Group group) {
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
   public boolean onMetricItem(final MetricReport parent, final MetricItem metricItem) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addMetricItem(metricItem);
            }
         });
      } else {
         parent.addMetricItem(metricItem);
      }

      return true;
   }

   @Override
   public boolean onPoint(final Group parent, final Point point) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addPoint(point);
            }
         });
      } else {
         parent.addPoint(point);
      }

      return true;
   }

   @Override
   public boolean onSegment(final MetricItem parent, final Segment segment) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addSegment(segment);
            }
         });
      } else {
         parent.addSegment(segment);
      }

      return true;
   }

   @Override
   public boolean onStatistic(final MetricReport parent, final Statistic statistic) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addStatistic(statistic);
            }
         });
      } else {
         parent.addStatistic(statistic);
      }

      return true;
   }

   @Override
   public boolean onStatisticsItem(final Statistic parent, final StatisticsItem statisticsItem) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addStatisticsItem(statisticsItem);
            }
         });
      } else {
         parent.addStatisticsItem(statisticsItem);
      }

      return true;
   }
}
