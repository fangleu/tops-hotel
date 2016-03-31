package com.dianping.cat.consumer.dependency.model.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

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
   public boolean onDependency(final Segment parent, final Dependency dependency) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addDependency(dependency);
            }
         });
      } else {
         parent.addDependency(dependency);
      }

      return true;
   }

   @Override
   public boolean onIndex(final Segment parent, final Index index) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addIndex(index);
            }
         });
      } else {
         parent.addIndex(index);
      }

      return true;
   }

   @Override
   public boolean onSegment(final DependencyReport parent, final Segment segment) {
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
}
