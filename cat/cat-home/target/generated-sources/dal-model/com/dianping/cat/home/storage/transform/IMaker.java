package com.dianping.cat.home.storage.transform;

import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

public interface IMaker<T> {

   public Link buildLink(T node);

   public Machine buildMachine(T node);

   public String buildPar(T node);

   public Storage buildStorage(T node);

   public StorageGroup buildStorageGroup(T node);

   public StorageGroupConfig buildStorageGroupConfig(T node);
}
