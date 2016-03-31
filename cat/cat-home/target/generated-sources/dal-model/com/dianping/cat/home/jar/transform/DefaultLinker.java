package com.dianping.cat.home.jar.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

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
   public boolean onDomain(final JarReport parent, final Domain domain) {
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
   public boolean onJar(final Machine parent, final Jar jar) {
      parent.addJar(jar);
      return true;
   }

   @Override
   public boolean onMachine(final Domain parent, final Machine machine) {
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
}
