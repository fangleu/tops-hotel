package com.dianping.cat.home.system.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

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
   public boolean onDay(final Entity parent, final Day day) {
      parent.setDay(day);
      return true;
   }

   @Override
   public boolean onDomain(final SystemReport parent, final Domain domain) {
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
   public boolean onEntity(final Domain parent, final Entity entity) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addEntity(entity);
            }
         });
      } else {
         parent.addEntity(entity);
      }

      return true;
   }

   @Override
   public boolean onMachine(final Entity parent, final Machine machine) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addMachine(machine);
            }
         });
      } else {
         parent.addMachine(machine);
      }

      return true;
   }

   @Override
   public boolean onRush(final Entity parent, final Rush rush) {
      parent.setRush(rush);
      return true;
   }
}
