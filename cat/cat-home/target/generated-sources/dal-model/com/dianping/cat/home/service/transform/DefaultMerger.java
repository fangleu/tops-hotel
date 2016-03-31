package com.dianping.cat.home.service.transform;

import java.util.Stack;

import com.dianping.cat.home.service.IEntity;
import com.dianping.cat.home.service.IVisitor;
import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private ServiceReport m_serviceReport;

   public DefaultMerger() {
   }

   public DefaultMerger(ServiceReport serviceReport) {
      m_serviceReport = serviceReport;
      m_objs.push(serviceReport);
   }

   public ServiceReport getServiceReport() {
      return m_serviceReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
      to.setTotalCount(from.getTotalCount());
      to.setFailureCount(from.getFailureCount());
      to.setFailurePercent(from.getFailurePercent());
      to.setSum(from.getSum());
      to.setAvg(from.getAvg());
      to.setQps(from.getQps());
   }

   protected void mergeServiceReport(ServiceReport to, ServiceReport from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
   }

   @Override
   public void visitServiceReport(ServiceReport from) {
      ServiceReport to = (ServiceReport) m_objs.peek();

      mergeServiceReport(to, from);
      visitServiceReportChildren(to, from);
   }

   protected void visitServiceReportChildren(ServiceReport to, ServiceReport from) {
      for (Domain source : from.getDomains().values()) {
         Domain target = to.findDomain(source.getId());

         if (target == null) {
            target = new Domain(source.getId());
            to.addDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
