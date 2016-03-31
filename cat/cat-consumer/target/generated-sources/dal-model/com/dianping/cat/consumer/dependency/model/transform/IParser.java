package com.dianping.cat.consumer.dependency.model.transform;

import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public interface IParser<T> {
   public DependencyReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDependency(IMaker<T> maker, ILinker linker, Dependency parent, T node);

   public void parseForIndex(IMaker<T> maker, ILinker linker, Index parent, T node);

   public void parseForSegment(IMaker<T> maker, ILinker linker, Segment parent, T node);
}
