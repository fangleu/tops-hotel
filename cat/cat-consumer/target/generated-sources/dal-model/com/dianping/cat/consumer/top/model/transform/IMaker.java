package com.dianping.cat.consumer.top.model.transform;

import com.dianping.cat.consumer.top.model.entity.Domain;
import com.dianping.cat.consumer.top.model.entity.Error;
import com.dianping.cat.consumer.top.model.entity.Machine;
import com.dianping.cat.consumer.top.model.entity.Segment;
import com.dianping.cat.consumer.top.model.entity.TopReport;

public interface IMaker<T> {

   public Domain buildDomain(T node);

   public Error buildError(T node);

   public Machine buildMachine(T node);

   public Segment buildSegment(T node);

   public TopReport buildTopReport(T node);
}
