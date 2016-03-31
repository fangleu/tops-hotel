package com.dianping.cat.home.heavy.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

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
   public boolean onHeavyCache(final HeavyReport parent, final HeavyCache heavyCache) {
      parent.setHeavyCache(heavyCache);
      return true;
   }

   @Override
   public boolean onHeavyCall(final HeavyReport parent, final HeavyCall heavyCall) {
      parent.setHeavyCall(heavyCall);
      return true;
   }

   @Override
   public boolean onHeavySql(final HeavyReport parent, final HeavySql heavySql) {
      parent.setHeavySql(heavySql);
      return true;
   }

   @Override
   public boolean onService(final HeavySql parent, final Service service) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addService(service);
            }
         });
      } else {
         parent.addService(service);
      }

      return true;
   }

   @Override
   public boolean onService(final HeavyCall parent, final Service service) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addService(service);
            }
         });
      } else {
         parent.addService(service);
      }

      return true;
   }

   @Override
   public boolean onService(final HeavyCache parent, final Service service) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addService(service);
            }
         });
      } else {
         parent.addService(service);
      }

      return true;
   }

   @Override
   public boolean onUrl(final HeavySql parent, final Url url) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addUrl(url);
            }
         });
      } else {
         parent.addUrl(url);
      }

      return true;
   }

   @Override
   public boolean onUrl(final HeavyCall parent, final Url url) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addUrl(url);
            }
         });
      } else {
         parent.addUrl(url);
      }

      return true;
   }

   @Override
   public boolean onUrl(final HeavyCache parent, final Url url) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addUrl(url);
            }
         });
      } else {
         parent.addUrl(url);
      }

      return true;
   }
}
