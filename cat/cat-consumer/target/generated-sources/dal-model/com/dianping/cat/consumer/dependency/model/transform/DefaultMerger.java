package com.dianping.cat.consumer.dependency.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.dependency.model.IEntity;
import com.dianping.cat.consumer.dependency.model.IVisitor;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private DependencyReport m_dependencyReport;

   public DefaultMerger() {
   }

   public DefaultMerger(DependencyReport dependencyReport) {
      m_dependencyReport = dependencyReport;
      m_objs.push(dependencyReport);
   }

   public DependencyReport getDependencyReport() {
      return m_dependencyReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDependency(Dependency to, Dependency from) {
      to.mergeAttributes(from);
   }

   protected void mergeDependencyReport(DependencyReport to, DependencyReport from) {
      to.mergeAttributes(from);
      to.getDomainNames().addAll(from.getDomainNames());
   }

   protected void mergeIndex(Index to, Index from) {
      to.mergeAttributes(from);
   }

   protected void mergeSegment(Segment to, Segment from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDependency(Dependency from) {
      Dependency to = (Dependency) m_objs.peek();

      mergeDependency(to, from);
      visitDependencyChildren(to, from);
   }

   protected void visitDependencyChildren(Dependency to, Dependency from) {
   }

   @Override
   public void visitDependencyReport(DependencyReport from) {
      DependencyReport to = (DependencyReport) m_objs.peek();

      mergeDependencyReport(to, from);
      visitDependencyReportChildren(to, from);
   }

   protected void visitDependencyReportChildren(DependencyReport to, DependencyReport from) {
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
   public void visitIndex(Index from) {
      Index to = (Index) m_objs.peek();

      mergeIndex(to, from);
      visitIndexChildren(to, from);
   }

   protected void visitIndexChildren(Index to, Index from) {
   }

   @Override
   public void visitSegment(Segment from) {
      Segment to = (Segment) m_objs.peek();

      mergeSegment(to, from);
      visitSegmentChildren(to, from);
   }

   protected void visitSegmentChildren(Segment to, Segment from) {
      for (Index source : from.getIndexs().values()) {
         Index target = to.findIndex(source.getName());

         if (target == null) {
            target = new Index(source.getName());
            to.addIndex(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Dependency source : from.getDependencies().values()) {
         Dependency target = to.findDependency(source.getKey());

         if (target == null) {
            target = new Dependency(source.getKey());
            to.addDependency(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
