package com.dianping.cat.consumer.dependency.model;

import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public interface IVisitor {

   public void visitDependency(Dependency dependency);

   public void visitDependencyReport(DependencyReport dependencyReport);

   public void visitIndex(Index index);

   public void visitSegment(Segment segment);
}
