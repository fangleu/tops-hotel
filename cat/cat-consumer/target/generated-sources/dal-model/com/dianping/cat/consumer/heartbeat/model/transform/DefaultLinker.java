package com.dianping.cat.consumer.heartbeat.model.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

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
   public boolean onDetail(final Extension parent, final Detail detail) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addDetail(detail);
            }
         });
      } else {
         parent.addDetail(detail);
      }

      return true;
   }

   @Override
   public boolean onDisk(final Period parent, final Disk disk) {
      parent.addDisk(disk);
      return true;
   }

   @Override
   public boolean onExtension(final Period parent, final Extension extension) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addExtension(extension);
            }
         });
      } else {
         parent.addExtension(extension);
      }

      return true;
   }

   @Override
   public boolean onMachine(final HeartbeatReport parent, final Machine machine) {
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
   public boolean onPeriod(final Machine parent, final Period period) {
      parent.addPeriod(period);
      return true;
   }
}
