package com.dianping.cat.consumer.storage.model.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

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
   public boolean onDomain(final Machine parent, final Domain domain) {
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
   public boolean onMachine(final StorageReport parent, final Machine machine) {
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
   public boolean onOperation(final Domain parent, final Operation operation) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addOperation(operation);
            }
         });
      } else {
         parent.addOperation(operation);
      }

      return true;
   }

   @Override
   public boolean onSegment(final Operation parent, final Segment segment) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addSegment(segment);
            }
         });
      } else {
         parent.addSegment(segment);
      }

      return true;
   }

   @Override
   public boolean onSql(final Domain parent, final Sql sql) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addSql(sql);
            }
         });
      } else {
         parent.addSql(sql);
      }

      return true;
   }
}
