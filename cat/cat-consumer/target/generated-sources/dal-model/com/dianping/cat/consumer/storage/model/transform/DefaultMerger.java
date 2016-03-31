package com.dianping.cat.consumer.storage.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.storage.model.IEntity;
import com.dianping.cat.consumer.storage.model.IVisitor;
import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private StorageReport m_storageReport;

   public DefaultMerger() {
   }

   public DefaultMerger(StorageReport storageReport) {
      m_storageReport = storageReport;
      m_objs.push(storageReport);
   }

   public StorageReport getStorageReport() {
      return m_storageReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergeOperation(Operation to, Operation from) {
      to.mergeAttributes(from);
   }

   protected void mergeSegment(Segment to, Segment from) {
      to.mergeAttributes(from);
   }

   protected void mergeSql(Sql to, Sql from) {
      to.mergeAttributes(from);
   }

   protected void mergeStorageReport(StorageReport to, StorageReport from) {
      to.mergeAttributes(from);
      to.getIds().addAll(from.getIds());
      to.getIps().addAll(from.getIps());
      to.getOps().addAll(from.getOps());
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
      for (Operation source : from.getOperations().values()) {
         Operation target = to.findOperation(source.getId());

         if (target == null) {
            target = new Operation(source.getId());
            to.addOperation(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Sql source : from.getSqls().values()) {
         Sql target = to.findSql(source.getId());

         if (target == null) {
            target = new Sql(source.getId());
            to.addSql(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMachine(Machine from) {
      Machine to = (Machine) m_objs.peek();

      mergeMachine(to, from);
      visitMachineChildren(to, from);
   }

   protected void visitMachineChildren(Machine to, Machine from) {
      for (Domain source : from.getDomains().values()) {
         Domain target = to.findDomain(source.getId());

         if (target == null) {
            target = new Domain(source.getId());
            to.addDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitOperation(Operation from) {
      Operation to = (Operation) m_objs.peek();

      mergeOperation(to, from);
      visitOperationChildren(to, from);
   }

   protected void visitOperationChildren(Operation to, Operation from) {
      for (Segment source : from.getSegments().values()) {
         Segment target = to.findSegment(source.getId());

         if (target == null) {
            target = new Segment(source.getId());
            to.addSegment(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitSegment(Segment from) {
      Segment to = (Segment) m_objs.peek();

      mergeSegment(to, from);
      visitSegmentChildren(to, from);
   }

   protected void visitSegmentChildren(Segment to, Segment from) {
   }

   @Override
   public void visitSql(Sql from) {
      Sql to = (Sql) m_objs.peek();

      mergeSql(to, from);
      visitSqlChildren(to, from);
   }

   protected void visitSqlChildren(Sql to, Sql from) {
   }

   @Override
   public void visitStorageReport(StorageReport from) {
      StorageReport to = (StorageReport) m_objs.peek();

      mergeStorageReport(to, from);
      visitStorageReportChildren(to, from);
   }

   protected void visitStorageReportChildren(StorageReport to, StorageReport from) {
      for (Machine source : from.getMachines().values()) {
         Machine target = to.findMachine(source.getId());

         if (target == null) {
            target = new Machine(source.getId());
            to.addMachine(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
