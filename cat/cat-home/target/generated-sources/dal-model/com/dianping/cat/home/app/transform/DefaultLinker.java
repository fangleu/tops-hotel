package com.dianping.cat.home.app.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

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
   public boolean onCode(final Command parent, final Code code) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addCode(code);
            }
         });
      } else {
         parent.addCode(code);
      }

      return true;
   }

   @Override
   public boolean onCommand(final AppReport parent, final Command command) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addCommand(command);
            }
         });
      } else {
         parent.addCommand(command);
      }

      return true;
   }

   @Override
   public boolean onTransaction(final Command parent, final Transaction transaction) {
      parent.setTransaction(transaction);
      return true;
   }
}
