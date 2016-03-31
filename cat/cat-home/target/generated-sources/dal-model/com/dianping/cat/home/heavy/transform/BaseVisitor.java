package com.dianping.cat.home.heavy.transform;

import com.dianping.cat.home.heavy.IVisitor;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitHeavyCache(HeavyCache heavyCache) {
      for (Url url : heavyCache.getUrls().values()) {
         visitUrl(url);
      }

      for (Service service : heavyCache.getServices().values()) {
         visitService(service);
      }
   }

   @Override
   public void visitHeavyCall(HeavyCall heavyCall) {
      for (Url url : heavyCall.getUrls().values()) {
         visitUrl(url);
      }

      for (Service service : heavyCall.getServices().values()) {
         visitService(service);
      }
   }

   @Override
   public void visitHeavyReport(HeavyReport heavyReport) {
      if (heavyReport.getHeavySql() != null) {
         visitHeavySql(heavyReport.getHeavySql());
      }

      if (heavyReport.getHeavyCall() != null) {
         visitHeavyCall(heavyReport.getHeavyCall());
      }

      if (heavyReport.getHeavyCache() != null) {
         visitHeavyCache(heavyReport.getHeavyCache());
      }
   }

   @Override
   public void visitHeavySql(HeavySql heavySql) {
      for (Url url : heavySql.getUrls().values()) {
         visitUrl(url);
      }

      for (Service service : heavySql.getServices().values()) {
         visitService(service);
      }
   }

   @Override
   public void visitService(Service service) {
   }

   @Override
   public void visitUrl(Url url) {
   }
}
