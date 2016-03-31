package com.dianping.cat.configuration.app.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

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
   public boolean onCode(final AppConfig parent, final Code code) {
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
   public boolean onCommand(final AppConfig parent, final Command command) {
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
   public boolean onConfigItem(final AppConfig parent, final ConfigItem configItem) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addConfigItem(configItem);
            }
         });
      } else {
         parent.addConfigItem(configItem);
      }

      return true;
   }

   @Override
   public boolean onItem(final ConfigItem parent, final Item item) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addItem(item);
            }
         });
      } else {
         parent.addItem(item);
      }

      return true;
   }
}
