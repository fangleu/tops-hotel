package com.dianping.cat.home.network.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

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
   public boolean onAnchor(final NetTopology parent, final Anchor anchor) {
      parent.addAnchor(anchor);
      return true;
   }

   @Override
   public boolean onConnection(final NetTopology parent, final Connection connection) {
      parent.addConnection(connection);
      return true;
   }

   @Override
   public boolean onInterface(final Connection parent, final Interface _interface) {
      parent.addInterface(_interface);
      return true;
   }

   @Override
   public boolean onNetGraph(final NetGraphSet parent, final NetGraph netGraph) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addNetGraph(netGraph);
            }
         });
      } else {
         parent.addNetGraph(netGraph);
      }

      return true;
   }

   @Override
   public boolean onNetTopology(final NetGraph parent, final NetTopology netTopology) {
      parent.addNetTopology(netTopology);
      return true;
   }

   @Override
   public boolean onSwitch(final NetTopology parent, final Switch _switch) {
      parent.addSwitch(_switch);
      return true;
   }
}
