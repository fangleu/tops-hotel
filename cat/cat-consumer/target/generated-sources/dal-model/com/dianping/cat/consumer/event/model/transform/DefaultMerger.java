package com.dianping.cat.consumer.event.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.event.model.IEntity;
import com.dianping.cat.consumer.event.model.IVisitor;
import com.dianping.cat.consumer.event.model.entity.EventReport;
import com.dianping.cat.consumer.event.model.entity.Machine;
import com.dianping.cat.consumer.event.model.entity.EventName;
import com.dianping.cat.consumer.event.model.entity.Range;
import com.dianping.cat.consumer.event.model.entity.EventType;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private EventReport m_eventReport;

   public DefaultMerger() {
   }

   public DefaultMerger(EventReport eventReport) {
      m_eventReport = eventReport;
      m_objs.push(eventReport);
   }

   public EventReport getEventReport() {
      return m_eventReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeEventReport(EventReport to, EventReport from) {
      to.mergeAttributes(from);
      to.getDomainNames().addAll(from.getDomainNames());
      to.getIps().addAll(from.getIps());
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergeName(EventName to, EventName from) {
      to.mergeAttributes(from);
      to.setSuccessMessageUrl(from.getSuccessMessageUrl());
      to.setFailMessageUrl(from.getFailMessageUrl());
   }

   protected void mergeRange(Range to, Range from) {
      to.mergeAttributes(from);
   }

   protected void mergeType(EventType to, EventType from) {
      to.mergeAttributes(from);
      to.setSuccessMessageUrl(from.getSuccessMessageUrl());
      to.setFailMessageUrl(from.getFailMessageUrl());
   }

   @Override
   public void visitEventReport(EventReport from) {
      EventReport to = (EventReport) m_objs.peek();

      mergeEventReport(to, from);
      visitEventReportChildren(to, from);
   }

   protected void visitEventReportChildren(EventReport to, EventReport from) {
      for (Machine source : from.getMachines().values()) {
         Machine target = to.findMachine(source.getIp());

         if (target == null) {
            target = new Machine(source.getIp());
            to.addMachine(target);
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
      for (EventType source : from.getTypes().values()) {
         EventType target = to.findType(source.getId());

         if (target == null) {
            target = new EventType(source.getId());
            to.addType(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitName(EventName from) {
      EventName to = (EventName) m_objs.peek();

      mergeName(to, from);
      visitNameChildren(to, from);
   }

   protected void visitNameChildren(EventName to, EventName from) {
      for (Range source : from.getRanges().values()) {
         Range target = to.findRange(source.getValue());

         if (target == null) {
            target = new Range(source.getValue());
            to.addRange(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitRange(Range from) {
      Range to = (Range) m_objs.peek();

      mergeRange(to, from);
      visitRangeChildren(to, from);
   }

   protected void visitRangeChildren(Range to, Range from) {
   }

   @Override
   public void visitType(EventType from) {
      EventType to = (EventType) m_objs.peek();

      mergeType(to, from);
      visitTypeChildren(to, from);
   }

   protected void visitTypeChildren(EventType to, EventType from) {
      for (EventName source : from.getNames().values()) {
         EventName target = to.findName(source.getId());

         if (target == null) {
            target = new EventName(source.getId());
            to.addName(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
