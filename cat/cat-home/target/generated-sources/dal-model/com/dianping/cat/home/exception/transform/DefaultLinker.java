package com.dianping.cat.home.exception.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.exception.entity.ExceptionExclude;
import com.dianping.cat.home.exception.entity.ExceptionLimit;
import com.dianping.cat.home.exception.entity.ExceptionRuleConfig;

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
   public boolean onExceptionExclude(final ExceptionRuleConfig parent, final ExceptionExclude exceptionExclude) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addExceptionExclude(exceptionExclude);
            }
         });
      } else {
         parent.addExceptionExclude(exceptionExclude);
      }

      return true;
   }

   @Override
   public boolean onExceptionLimit(final ExceptionRuleConfig parent, final ExceptionLimit exceptionLimit) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addExceptionLimit(exceptionLimit);
            }
         });
      } else {
         parent.addExceptionLimit(exceptionLimit);
      }

      return true;
   }
}
