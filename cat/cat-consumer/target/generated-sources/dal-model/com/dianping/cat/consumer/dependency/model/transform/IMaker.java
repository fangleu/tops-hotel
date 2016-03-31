package com.dianping.cat.consumer.dependency.model.transform;

import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public interface IMaker<T> {

   public Dependency buildDependency(T node);

   public DependencyReport buildDependencyReport(T node);

   public String buildDomainName(T node);

   public Index buildIndex(T node);

   public Segment buildSegment(T node);
}
