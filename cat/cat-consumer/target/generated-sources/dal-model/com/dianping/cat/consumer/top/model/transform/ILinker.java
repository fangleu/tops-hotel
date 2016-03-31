package com.dianping.cat.consumer.top.model.transform;

import com.dianping.cat.consumer.top.model.entity.Domain;
import com.dianping.cat.consumer.top.model.entity.Error;
import com.dianping.cat.consumer.top.model.entity.Machine;
import com.dianping.cat.consumer.top.model.entity.Segment;
import com.dianping.cat.consumer.top.model.entity.TopReport;

public interface ILinker {

   public boolean onDomain(TopReport parent, Domain domain);

   public boolean onError(Segment parent, Error error);

   public boolean onMachine(Segment parent, Machine machine);

   public boolean onSegment(Domain parent, Segment segment);
}
