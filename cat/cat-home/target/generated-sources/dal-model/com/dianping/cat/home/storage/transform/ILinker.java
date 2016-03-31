package com.dianping.cat.home.storage.transform;

import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

public interface ILinker {

   public boolean onLink(StorageGroup parent, Link link);

   public boolean onMachine(Storage parent, Machine machine);

   public boolean onStorage(StorageGroup parent, Storage storage);

   public boolean onStorageGroup(StorageGroupConfig parent, StorageGroup storageGroup);
}
