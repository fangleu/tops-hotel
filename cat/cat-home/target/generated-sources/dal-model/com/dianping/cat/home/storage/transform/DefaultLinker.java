package com.dianping.cat.home.storage.transform;

import java.util.ArrayList;
import java.util.List;
import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

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
   public boolean onLink(final StorageGroup parent, final Link link) {
      parent.setLink(link);
      return true;
   }

   @Override
   public boolean onMachine(final Storage parent, final Machine machine) {
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
   public boolean onStorage(final StorageGroup parent, final Storage storage) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addStorage(storage);
            }
         });
      } else {
         parent.addStorage(storage);
      }

      return true;
   }

   @Override
   public boolean onStorageGroup(final StorageGroupConfig parent, final StorageGroup storageGroup) {
      if (m_deferrable) {
         m_deferedJobs.add(new Runnable() {
            @Override
            public void run() {
               parent.addStorageGroup(storageGroup);
            }
         });
      } else {
         parent.addStorageGroup(storageGroup);
      }

      return true;
   }
}
