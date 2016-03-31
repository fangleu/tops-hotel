package com.dianping.cat.home.router.transform;

import java.util.Stack;

import com.dianping.cat.home.router.IEntity;
import com.dianping.cat.home.router.IVisitor;
import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private RouterConfig m_routerConfig;

   public DefaultMerger() {
   }

   public DefaultMerger(RouterConfig routerConfig) {
      m_routerConfig = routerConfig;
      m_objs.push(routerConfig);
   }

   public RouterConfig getRouterConfig() {
      return m_routerConfig;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDefaultServer(DefaultServer to, DefaultServer from) {
      to.mergeAttributes(from);
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
   }

   protected void mergeRouterConfig(RouterConfig to, RouterConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeServer(Server to, Server from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDefaultServer(DefaultServer from) {
      DefaultServer to = (DefaultServer) m_objs.peek();

      mergeDefaultServer(to, from);
      visitDefaultServerChildren(to, from);
   }

   protected void visitDefaultServerChildren(DefaultServer to, DefaultServer from) {
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
      for (Server source : from.getServers()) {
         Server target = null;

         if (target == null) {
            target = new Server();
            to.addServer(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitRouterConfig(RouterConfig from) {
      RouterConfig to = (RouterConfig) m_objs.peek();

      mergeRouterConfig(to, from);
      visitRouterConfigChildren(to, from);
   }

   protected void visitRouterConfigChildren(RouterConfig to, RouterConfig from) {
      for (DefaultServer source : from.getDefaultServers()) {
         DefaultServer target = null;

         if (target == null) {
            target = new DefaultServer();
            to.addDefaultServer(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

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

   @Override
   public void visitServer(Server from) {
      Server to = (Server) m_objs.peek();

      mergeServer(to, from);
      visitServerChildren(to, from);
   }

   protected void visitServerChildren(Server to, Server from) {
   }
}
