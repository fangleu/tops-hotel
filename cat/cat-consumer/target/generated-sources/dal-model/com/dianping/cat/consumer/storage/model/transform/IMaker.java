package com.dianping.cat.consumer.storage.model.transform;

import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public interface IMaker<T> {

   public Domain buildDomain(T node);

   public String buildId(T node);

   public String buildIp(T node);

   public Machine buildMachine(T node);

   public String buildOp(T node);

   public Operation buildOperation(T node);

   public Segment buildSegment(T node);

   public Sql buildSql(T node);

   public StorageReport buildStorageReport(T node);
}
