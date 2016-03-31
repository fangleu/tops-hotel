package com.dianping.cat.configuration.app.command.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public class DefaultLinker implements ILinker {
   @SuppressWarnings("unused")
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
   public boolean onCommand(final Rule parent, final Command command) {
      parent.addCommand(command);
      return true;
   }

   @Override
   public boolean onRule(final CommandFormat parent, final Rule rule) {
      parent.addRule(rule);
      return true;
   }
}
