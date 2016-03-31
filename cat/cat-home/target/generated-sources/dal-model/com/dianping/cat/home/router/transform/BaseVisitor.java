package com.dianping.cat.home.router.transform;

import com.dianping.cat.home.router.IVisitor;
import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDefaultServer(DefaultServer defaultServer) {
   }

   @Override
   public void visitDomain(Domain domain) {
      for (Server server : domain.getServers()) {
         visitServer(server);
      }
   }

   @Override
   public void visitRouterConfig(RouterConfig routerConfig) {
      for (DefaultServer defaultServer : routerConfig.getDefaultServers()) {
         visitDefaultServer(defaultServer);
      }

      for (Domain domain : routerConfig.getDomains().values()) {
         visitDomain(domain);
      }
   }

   @Override
   public void visitServer(Server server) {
   }
}
