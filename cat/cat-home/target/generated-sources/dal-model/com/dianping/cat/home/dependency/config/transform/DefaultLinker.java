package com.dianping.cat.home.dependency.config.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.dependency.config.entity.DomainConfig;
import com.dianping.cat.home.dependency.config.entity.EdgeConfig;
import com.dianping.cat.home.dependency.config.entity.NodeConfig;
import com.dianping.cat.home.dependency.config.entity.TopologyGraphConfig;

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
   public boolean onDomainConfig(final NodeConfig parent, final DomainConfig domainConfig) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addDomainConfig(domainConfig);
            }
         });
      } else {
         parent.addDomainConfig(domainConfig);
      }

      return true;
   }

   @Override
   public boolean onEdgeConfig(final TopologyGraphConfig parent, final EdgeConfig edgeConfig) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addEdgeConfig(edgeConfig);
            }
         });
      } else {
         parent.addEdgeConfig(edgeConfig);
      }

      return true;
   }

   @Override
   public boolean onNodeConfig(final TopologyGraphConfig parent, final NodeConfig nodeConfig) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addNodeConfig(nodeConfig);
            }
         });
      } else {
         parent.addNodeConfig(nodeConfig);
      }

      return true;
   }
}
