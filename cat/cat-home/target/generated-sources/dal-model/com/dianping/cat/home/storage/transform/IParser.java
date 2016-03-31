package com.dianping.cat.home.storage.transform;

import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

public interface IParser<T> {
   public StorageGroupConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForLink(IMaker<T> maker, ILinker linker, Link parent, T node);

   public void parseForMachine(IMaker<T> maker, ILinker linker, Machine parent, T node);

   public void parseForStorage(IMaker<T> maker, ILinker linker, Storage parent, T node);

   public void parseForStorageGroup(IMaker<T> maker, ILinker linker, StorageGroup parent, T node);
}
