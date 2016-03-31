package com.dianping.cat.home.alert.policy.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.alert.policy.entity.AlertPolicy;
import com.dianping.cat.home.alert.policy.entity.Group;
import com.dianping.cat.home.alert.policy.entity.Level;
import com.dianping.cat.home.alert.policy.entity.Type;

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
   public boolean onGroup(final Type parent, final Group group) {
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
   public boolean onLevel(final Group parent, final Level level) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addLevel(level);
            }
         });
      } else {
         parent.addLevel(level);
      }

      return true;
   }

   @Override
   public boolean onType(final AlertPolicy parent, final Type type) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addType(type);
            }
         });
      } else {
         parent.addType(type);
      }

      return true;
   }
}
