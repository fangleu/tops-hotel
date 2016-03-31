package com.dianping.cat.configuration.web.url.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

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
   public boolean onCode(final UrlPattern parent, final Code code) {
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
   public boolean onPatternItem(final UrlPattern parent, final PatternItem patternItem) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addPatternItem(patternItem);
            }
         });
      } else {
         parent.addPatternItem(patternItem);
      }

      return true;
   }
}
