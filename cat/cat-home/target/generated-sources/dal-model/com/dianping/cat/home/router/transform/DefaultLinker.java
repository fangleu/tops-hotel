package com.dianping.cat.home.router.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

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
   public boolean onDefaultServer(final RouterConfig parent, final DefaultServer defaultServer) {
      parent.addDefaultServer(defaultServer);
      return true;
   }

   @Override
   public boolean onDomain(final RouterConfig parent, final Domain domain) {
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
   public boolean onServer(final Domain parent, final Server server) {
      parent.addServer(server);
      return true;
   }
}
