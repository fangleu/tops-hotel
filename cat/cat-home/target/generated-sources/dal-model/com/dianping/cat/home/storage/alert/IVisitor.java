package com.dianping.cat.home.storage.alert;

import com.dianping.cat.home.storage.alert.entity.Detail;
import com.dianping.cat.home.storage.alert.entity.Machine;
import com.dianping.cat.home.storage.alert.entity.Operation;
import com.dianping.cat.home.storage.alert.entity.Storage;
import com.dianping.cat.home.storage.alert.entity.StorageAlertInfo;
import com.dianping.cat.home.storage.alert.entity.Target;

public interface IVisitor {

   public void visitDetail(Detail detail);

   public void visitMachine(Machine machine);

   public void visitOperation(Operation operation);

   public void visitStorage(Storage storage);

   public void visitStorageAlertInfo(StorageAlertInfo storageAlertInfo);

   public void visitTarget(Target target);
}
