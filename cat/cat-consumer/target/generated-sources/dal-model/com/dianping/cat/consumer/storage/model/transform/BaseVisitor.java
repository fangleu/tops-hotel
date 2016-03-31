package com.dianping.cat.consumer.storage.model.transform;

import com.dianping.cat.consumer.storage.model.IVisitor;
import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDomain(Domain domain) {
      for (Operation operation : domain.getOperations().values()) {
         visitOperation(operation);
      }

      for (Sql sql : domain.getSqls().values()) {
         visitSql(sql);
      }
   }

   @Override
   public void visitMachine(Machine machine) {
      for (Domain domain : machine.getDomains().values()) {
         visitDomain(domain);
      }
   }

   @Override
   public void visitOperation(Operation operation) {
      for (Segment segment : operation.getSegments().values()) {
         visitSegment(segment);
      }
   }

   @Override
   public void visitSegment(Segment segment) {
   }

   @Override
   public void visitSql(Sql sql) {
   }

   @Override
   public void visitStorageReport(StorageReport storageReport) {
      for (Machine machine : storageReport.getMachines().values()) {
         visitMachine(machine);
      }
   }
}
