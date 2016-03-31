package com.dianping.cat.home.bug.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

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
   public boolean onDomain(final BugReport parent, final Domain domain) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addDomain(domain);
            }
         });
      } else {
         parent.addDomain(domain);
      }

      return true;
   }

   @Override
   public boolean onExceptionItem(final Domain parent, final ExceptionItem exceptionItem) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addExceptionItem(exceptionItem);
            }
         });
      } else {
         parent.addExceptionItem(exceptionItem);
      }

      return true;
   }
}
