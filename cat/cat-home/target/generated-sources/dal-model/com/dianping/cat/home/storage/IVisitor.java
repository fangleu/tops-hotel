package com.dianping.cat.home.storage;

import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

public interface IVisitor {

   public void visitLink(Link link);

   public void visitMachine(Machine machine);

   public void visitStorage(Storage storage);

   public void visitStorageGroup(StorageGroup storageGroup);

   public void visitStorageGroupConfig(StorageGroupConfig storageGroupConfig);
}
