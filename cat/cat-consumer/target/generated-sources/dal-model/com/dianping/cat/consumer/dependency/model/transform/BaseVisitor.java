package com.dianping.cat.consumer.dependency.model.transform;

import com.dianping.cat.consumer.dependency.model.IVisitor;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDependency(Dependency dependency) {
   }

   @Override
   public void visitDependencyReport(DependencyReport dependencyReport) {
      for (Segment segment : dependencyReport.getSegments().values()) {
         visitSegment(segment);
      }
   }

   @Override
   public void visitIndex(Index index) {
   }

   @Override
   public void visitSegment(Segment segment) {
      for (Index index : segment.getIndexs().values()) {
         visitIndex(index);
      }

      for (Dependency dependency : segment.getDependencies().values()) {
         visitDependency(dependency);
      }
   }
}
