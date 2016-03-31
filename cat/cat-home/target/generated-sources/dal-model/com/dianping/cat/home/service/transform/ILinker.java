package com.dianping.cat.home.service.transform;

import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public interface ILinker {

   public boolean onDomain(ServiceReport parent, Domain domain);
}
