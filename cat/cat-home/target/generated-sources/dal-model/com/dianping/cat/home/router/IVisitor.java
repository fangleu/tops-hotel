package com.dianping.cat.home.router;

import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public interface IVisitor {

   public void visitDefaultServer(DefaultServer defaultServer);

   public void visitDomain(Domain domain);

   public void visitRouterConfig(RouterConfig routerConfig);

   public void visitServer(Server server);
}
