package com.dianping.cat.home.service;

import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public interface IVisitor {

   public void visitDomain(Domain domain);

   public void visitServiceReport(ServiceReport serviceReport);
}
