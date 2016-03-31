package com.dianping.cat.consumer.state.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.state.model.IEntity;
import com.dianping.cat.consumer.state.model.IVisitor;
import com.dianping.cat.consumer.state.model.entity.Detail;
import com.dianping.cat.consumer.state.model.entity.Machine;
import com.dianping.cat.consumer.state.model.entity.Message;
import com.dianping.cat.consumer.state.model.entity.ProcessDomain;
import com.dianping.cat.consumer.state.model.entity.StateReport;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private StateReport m_stateReport;

   public DefaultMerger() {
   }

   public DefaultMerger(StateReport stateReport) {
      m_stateReport = stateReport;
      m_objs.push(stateReport);
   }

   public StateReport getStateReport() {
      return m_stateReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDetail(Detail to, Detail from) {
      to.mergeAttributes(from);
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergeMessage(Message to, Message from) {
      to.mergeAttributes(from);
   }

   protected void mergeProcessDomain(ProcessDomain to, ProcessDomain from) {
      to.mergeAttributes(from);
      to.getIps().addAll(from.getIps());
   }

   protected void mergeStateReport(StateReport to, StateReport from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDetail(Detail from) {
      Detail to = (Detail) m_objs.peek();

      mergeDetail(to, from);
      visitDetailChildren(to, from);
   }

   protected void visitDetailChildren(Detail to, Detail from) {
   }

   @Override
   public void visitMachine(Machine from) {
      Machine to = (Machine) m_objs.peek();

      mergeMachine(to, from);
      visitMachineChildren(to, from);
   }

   protected void visitMachineChildren(Machine to, Machine from) {
      for (ProcessDomain source : from.getProcessDomains().values()) {
         ProcessDomain target = to.findProcessDomain(source.getName());

         if (target == null) {
            target = new ProcessDomain(source.getName());
            to.addProcessDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Message source : from.getMessages().values()) {
         Message target = to.findMessage(source.getId());

         if (target == null) {
            target = new Message(source.getId());
            to.addMessage(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMessage(Message from) {
      Message to = (Message) m_objs.peek();

      mergeMessage(to, from);
      visitMessageChildren(to, from);
   }

   protected void visitMessageChildren(Message to, Message from) {
   }

   @Override
   public void visitProcessDomain(ProcessDomain from) {
      ProcessDomain to = (ProcessDomain) m_objs.peek();

      mergeProcessDomain(to, from);
      visitProcessDomainChildren(to, from);
   }

   protected void visitProcessDomainChildren(ProcessDomain to, ProcessDomain from) {
      for (Detail source : from.getDetails().values()) {
         Detail target = to.findDetail(source.getId());

         if (target == null) {
            target = new Detail(source.getId());
            to.addDetail(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitStateReport(StateReport from) {
      StateReport to = (StateReport) m_objs.peek();

      mergeStateReport(to, from);
      visitStateReportChildren(to, from);
   }

   protected void visitStateReportChildren(StateReport to, StateReport from) {
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
}
