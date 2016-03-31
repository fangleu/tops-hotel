package com.dianping.cat.consumer.storage.model;

import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public interface IVisitor {

   public void visitDomain(Domain domain);

   public void visitMachine(Machine machine);

   public void visitOperation(Operation operation);

   public void visitSegment(Segment segment);

   public void visitSql(Sql sql);

   public void visitStorageReport(StorageReport storageReport);
}
