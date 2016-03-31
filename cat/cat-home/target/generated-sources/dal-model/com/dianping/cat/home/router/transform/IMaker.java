package com.dianping.cat.home.router.transform;

import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public interface IMaker<T> {

   public DefaultServer buildDefaultServer(T node);

   public Domain buildDomain(T node);

   public RouterConfig buildRouterConfig(T node);

   public Server buildServer(T node);
}
