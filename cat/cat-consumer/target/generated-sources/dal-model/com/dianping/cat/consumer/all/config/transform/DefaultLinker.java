package com.dianping.cat.consumer.all.config.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

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
   public boolean onName(final Type parent, final Name name) {
      parent.addName(name);
      return true;
   }

   @Override
   public boolean onReport(final AllConfig parent, final Report report) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addReport(report);
            }
         });
      } else {
         parent.addReport(report);
      }

      return true;
   }

   @Override
   public boolean onType(final Report parent, final Type type) {
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
