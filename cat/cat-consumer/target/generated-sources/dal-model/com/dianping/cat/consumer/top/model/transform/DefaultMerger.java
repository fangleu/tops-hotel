package com.dianping.cat.consumer.top.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.top.model.IEntity;
import com.dianping.cat.consumer.top.model.IVisitor;
import com.dianping.cat.consumer.top.model.entity.Domain;
import com.dianping.cat.consumer.top.model.entity.Error;
import com.dianping.cat.consumer.top.model.entity.Machine;
import com.dianping.cat.consumer.top.model.entity.Segment;
import com.dianping.cat.consumer.top.model.entity.TopReport;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private TopReport m_topReport;

   public DefaultMerger() {
   }

   public DefaultMerger(TopReport topReport) {
      m_topReport = topReport;
      m_objs.push(topReport);
   }

   public TopReport getTopReport() {
      return m_topReport;
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

   protected void mergeError(Error to, Error from) {
      to.mergeAttributes(from);
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergeSegment(Segment to, Segment from) {
      to.mergeAttributes(from);
   }

   protected void mergeTopReport(TopReport to, TopReport from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
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
   public void visitError(Error from) {
      Error to = (Error) m_objs.peek();

      mergeError(to, from);
      visitErrorChildren(to, from);
   }

   protected void visitErrorChildren(Error to, Error from) {
   }

   @Override
   public void visitMachine(Machine from) {
      Machine to = (Machine) m_objs.peek();

      mergeMachine(to, from);
      visitMachineChildren(to, from);
   }

   protected void visitMachineChildren(Machine to, Machine from) {
   }

   @Override
   public void visitSegment(Segment from) {
      Segment to = (Segment) m_objs.peek();

      mergeSegment(to, from);
      visitSegmentChildren(to, from);
   }

   protected void visitSegmentChildren(Segment to, Segment from) {
      for (Error source : from.getErrors().values()) {
         Error target = to.findError(source.getId());

         if (target == null) {
            target = new Error(source.getId());
            to.addError(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

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

   @Override
   public void visitTopReport(TopReport from) {
      TopReport to = (TopReport) m_objs.peek();

      mergeTopReport(to, from);
      visitTopReportChildren(to, from);
   }

   protected void visitTopReportChildren(TopReport to, TopReport from) {
      for (Domain source : from.getDomains().values()) {
         Domain target = to.findDomain(source.getName());

         if (target == null) {
            target = new Domain(source.getName());
            to.addDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
