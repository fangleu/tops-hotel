package com.dianping.cat.home.heavy.transform;

import java.util.Stack;

import com.dianping.cat.home.heavy.IEntity;
import com.dianping.cat.home.heavy.IVisitor;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private HeavyReport m_heavyReport;

   public DefaultMerger() {
   }

   public DefaultMerger(HeavyReport heavyReport) {
      m_heavyReport = heavyReport;
      m_objs.push(heavyReport);
   }

   public HeavyReport getHeavyReport() {
      return m_heavyReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeHeavyCache(HeavyCache to, HeavyCache from) {
      to.mergeAttributes(from);
   }

   protected void mergeHeavyCall(HeavyCall to, HeavyCall from) {
      to.mergeAttributes(from);
   }

   protected void mergeHeavyReport(HeavyReport to, HeavyReport from) {
      to.mergeAttributes(from);
   }

   protected void mergeHeavySql(HeavySql to, HeavySql from) {
      to.mergeAttributes(from);
   }

   protected void mergeService(Service to, Service from) {
      to.mergeAttributes(from);
   }

   protected void mergeUrl(Url to, Url from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitHeavyCache(HeavyCache from) {
      HeavyCache to = (HeavyCache) m_objs.peek();

      mergeHeavyCache(to, from);
      visitHeavyCacheChildren(to, from);
   }

   protected void visitHeavyCacheChildren(HeavyCache to, HeavyCache from) {
      for (Url source : from.getUrls().values()) {
         Url target = to.findUrl(source.getKey());

         if (target == null) {
            target = new Url(source.getKey());
            to.addUrl(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Service source : from.getServices().values()) {
         Service target = to.findService(source.getKey());

         if (target == null) {
            target = new Service(source.getKey());
            to.addService(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitHeavyCall(HeavyCall from) {
      HeavyCall to = (HeavyCall) m_objs.peek();

      mergeHeavyCall(to, from);
      visitHeavyCallChildren(to, from);
   }

   protected void visitHeavyCallChildren(HeavyCall to, HeavyCall from) {
      for (Url source : from.getUrls().values()) {
         Url target = to.findUrl(source.getKey());

         if (target == null) {
            target = new Url(source.getKey());
            to.addUrl(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Service source : from.getServices().values()) {
         Service target = to.findService(source.getKey());

         if (target == null) {
            target = new Service(source.getKey());
            to.addService(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitHeavyReport(HeavyReport from) {
      HeavyReport to = (HeavyReport) m_objs.peek();

      mergeHeavyReport(to, from);
      visitHeavyReportChildren(to, from);
   }

   protected void visitHeavyReportChildren(HeavyReport to, HeavyReport from) {
      if (from.getHeavySql() != null) {
         HeavySql target = to.getHeavySql();

         if (target == null) {
            target = new HeavySql();
            to.setHeavySql(target);
         }

         m_objs.push(target);
         from.getHeavySql().accept(this);
         m_objs.pop();
      }

      if (from.getHeavyCall() != null) {
         HeavyCall target = to.getHeavyCall();

         if (target == null) {
            target = new HeavyCall();
            to.setHeavyCall(target);
         }

         m_objs.push(target);
         from.getHeavyCall().accept(this);
         m_objs.pop();
      }

      if (from.getHeavyCache() != null) {
         HeavyCache target = to.getHeavyCache();

         if (target == null) {
            target = new HeavyCache();
            to.setHeavyCache(target);
         }

         m_objs.push(target);
         from.getHeavyCache().accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitHeavySql(HeavySql from) {
      HeavySql to = (HeavySql) m_objs.peek();

      mergeHeavySql(to, from);
      visitHeavySqlChildren(to, from);
   }

   protected void visitHeavySqlChildren(HeavySql to, HeavySql from) {
      for (Url source : from.getUrls().values()) {
         Url target = to.findUrl(source.getKey());

         if (target == null) {
            target = new Url(source.getKey());
            to.addUrl(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Service source : from.getServices().values()) {
         Service target = to.findService(source.getKey());

         if (target == null) {
            target = new Service(source.getKey());
            to.addService(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitService(Service from) {
      Service to = (Service) m_objs.peek();

      mergeService(to, from);
      visitServiceChildren(to, from);
   }

   protected void visitServiceChildren(Service to, Service from) {
   }

   @Override
   public void visitUrl(Url from) {
      Url to = (Url) m_objs.peek();

      mergeUrl(to, from);
      visitUrlChildren(to, from);
   }

   protected void visitUrlChildren(Url to, Url from) {
   }
}
