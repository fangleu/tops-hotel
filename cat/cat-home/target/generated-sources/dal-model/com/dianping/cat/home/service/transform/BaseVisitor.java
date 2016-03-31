package com.dianping.cat.home.service.transform;

import com.dianping.cat.home.service.IVisitor;
import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDomain(Domain domain) {
   }

   @Override
   public void visitServiceReport(ServiceReport serviceReport) {
      for (Domain domain : serviceReport.getDomains().values()) {
         visitDomain(domain);
      }
   }
}
