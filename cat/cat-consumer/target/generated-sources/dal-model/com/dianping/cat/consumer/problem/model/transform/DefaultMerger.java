package com.dianping.cat.consumer.problem.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.problem.model.IEntity;
import com.dianping.cat.consumer.problem.model.IVisitor;
import com.dianping.cat.consumer.problem.model.entity.Duration;
import com.dianping.cat.consumer.problem.model.entity.Entity;
import com.dianping.cat.consumer.problem.model.entity.Entry;
import com.dianping.cat.consumer.problem.model.entity.Machine;
import com.dianping.cat.consumer.problem.model.entity.ProblemReport;
import com.dianping.cat.consumer.problem.model.entity.Segment;
import com.dianping.cat.consumer.problem.model.entity.JavaThread;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private ProblemReport m_problemReport;

   public DefaultMerger() {
   }

   public DefaultMerger(ProblemReport problemReport) {
      m_problemReport = problemReport;
      m_objs.push(problemReport);
   }

   public ProblemReport getProblemReport() {
      return m_problemReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDuration(Duration to, Duration from) {
      to.mergeAttributes(from);
      to.getMessages().addAll(from.getMessages());
   }

   protected void mergeEntity(Entity to, Entity from) {
      to.mergeAttributes(from);
   }

   protected void mergeEntry(Entry to, Entry from) {
      to.mergeAttributes(from);
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergeProblemReport(ProblemReport to, ProblemReport from) {
      to.mergeAttributes(from);
      to.getDomainNames().addAll(from.getDomainNames());
      to.getIps().addAll(from.getIps());
   }

   protected void mergeSegment(Segment to, Segment from) {
      to.mergeAttributes(from);
      to.getMessages().addAll(from.getMessages());
   }

   protected void mergeThread(JavaThread to, JavaThread from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDuration(Duration from) {
      Duration to = (Duration) m_objs.peek();

      mergeDuration(to, from);
      visitDurationChildren(to, from);
   }

   protected void visitDurationChildren(Duration to, Duration from) {
   }

   @Override
   public void visitEntity(Entity from) {
      Entity to = (Entity) m_objs.peek();

      mergeEntity(to, from);
      visitEntityChildren(to, from);
   }

   protected void visitEntityChildren(Entity to, Entity from) {
      for (JavaThread source : from.getThreads().values()) {
         JavaThread target = to.findThread(source.getId());

         if (target == null) {
            target = new JavaThread(source.getId());
            to.addThread(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Duration source : from.getDurations().values()) {
         Duration target = to.findDuration(source.getValue());

         if (target == null) {
            target = new Duration(source.getValue());
            to.addDuration(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitEntry(Entry from) {
      Entry to = (Entry) m_objs.peek();

      mergeEntry(to, from);
      visitEntryChildren(to, from);
   }

   protected void visitEntryChildren(Entry to, Entry from) {
      for (Duration source : from.getDurations().values()) {
         Duration target = to.findDuration(source.getValue());

         if (target == null) {
            target = new Duration(source.getValue());
            to.addDuration(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (JavaThread source : from.getThreads().values()) {
         JavaThread target = to.findThread(source.getId());

         if (target == null) {
            target = new JavaThread(source.getId());
            to.addThread(target);
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
      for (Entry source : from.getEntries()) {
         Entry target = null;

         if (target == null) {
            target = new Entry();
            to.addEntry(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Entity source : from.getEntities().values()) {
         Entity target = to.findEntity(source.getId());

         if (target == null) {
            target = new Entity(source.getId());
            to.addEntity(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitProblemReport(ProblemReport from) {
      ProblemReport to = (ProblemReport) m_objs.peek();

      mergeProblemReport(to, from);
      visitProblemReportChildren(to, from);
   }

   protected void visitProblemReportChildren(ProblemReport to, ProblemReport from) {
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
   public void visitSegment(Segment from) {
      Segment to = (Segment) m_objs.peek();

      mergeSegment(to, from);
      visitSegmentChildren(to, from);
   }

   protected void visitSegmentChildren(Segment to, Segment from) {
   }

   @Override
   public void visitThread(JavaThread from) {
      JavaThread to = (JavaThread) m_objs.peek();

      mergeThread(to, from);
      visitThreadChildren(to, from);
   }

   protected void visitThreadChildren(JavaThread to, JavaThread from) {
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
}
