package com.dianping.cat.home.dependency.graph.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

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
   public boolean onTopologyEdge(final TopologyGraph parent, final TopologyEdge topologyEdge) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addTopologyEdge(topologyEdge);
            }
         });
      } else {
         parent.addTopologyEdge(topologyEdge);
      }

      return true;
   }

   @Override
   public boolean onTopologyNode(final TopologyGraph parent, final TopologyNode topologyNode) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addTopologyNode(topologyNode);
            }
         });
      } else {
         parent.addTopologyNode(topologyNode);
      }

      return true;
   }
}
