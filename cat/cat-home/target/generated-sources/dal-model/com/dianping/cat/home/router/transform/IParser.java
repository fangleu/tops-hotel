package com.dianping.cat.home.router.transform;

import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public interface IParser<T> {
   public RouterConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDefaultServer(IMaker<T> maker, ILinker linker, DefaultServer parent, T node);

   public void parseForDomain(IMaker<T> maker, ILinker linker, Domain parent, T node);

   public void parseForServer(IMaker<T> maker, ILinker linker, Server parent, T node);
}
