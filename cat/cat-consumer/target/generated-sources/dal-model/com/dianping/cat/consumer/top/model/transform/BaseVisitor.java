package com.dianping.cat.consumer.top.model.transform;

import com.dianping.cat.consumer.top.model.IVisitor;
import com.dianping.cat.consumer.top.model.entity.Domain;
import com.dianping.cat.consumer.top.model.entity.Error;
import com.dianping.cat.consumer.top.model.entity.Machine;
import com.dianping.cat.consumer.top.model.entity.Segment;
import com.dianping.cat.consumer.top.model.entity.TopReport;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDomain(Domain domain) {
      for (Segment segment : domain.getSegments().values()) {
         visitSegment(segment);
      }
   }

   @Override
   public void visitError(Error error) {
   }

   @Override
   public void visitMachine(Machine machine) {
   }

   @Override
   public void visitSegment(Segment segment) {
      for (Error error : segment.getErrors().values()) {
         visitError(error);
      }

      for (Machine machine : segment.getMachines().values()) {
         visitMachine(machine);
      }
   }

   @Override
   public void visitTopReport(TopReport topReport) {
      for (Domain domain : topReport.getDomains().values()) {
         visitDomain(domain);
      }
   }
}
