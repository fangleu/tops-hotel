package com.dianping.cat.home.router.transform;

import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public interface ILinker {

   public boolean onDefaultServer(RouterConfig parent, DefaultServer defaultServer);

   public boolean onDomain(RouterConfig parent, Domain domain);

   public boolean onServer(Domain parent, Server server);
}
