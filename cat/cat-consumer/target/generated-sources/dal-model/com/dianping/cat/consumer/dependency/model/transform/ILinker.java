package com.dianping.cat.consumer.dependency.model.transform;

import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public interface ILinker {

   public boolean onDependency(Segment parent, Dependency dependency);

   public boolean onIndex(Segment parent, Index index);

   public boolean onSegment(DependencyReport parent, Segment segment);
}
