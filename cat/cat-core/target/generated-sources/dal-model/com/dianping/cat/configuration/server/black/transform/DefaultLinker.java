package com.dianping.cat.configuration.server.black.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.server.black.entity.BlackList;
import com.dianping.cat.configuration.server.black.entity.Property;

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
   public boolean onProperty(final BlackList parent, final Property property) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addProperty(property);
            }
         });
      } else {
         parent.addProperty(property);
      }

      return true;
   }
}
