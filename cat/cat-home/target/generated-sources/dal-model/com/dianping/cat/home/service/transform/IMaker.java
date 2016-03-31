package com.dianping.cat.home.service.transform;

import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public interface IMaker<T> {

   public Domain buildDomain(T node);

   public ServiceReport buildServiceReport(T node);
}
