package com.dianping.cat.home.alert.thirdparty.transform;

import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public interface IParser<T> {
   public ThirdPartyConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForHttp(IMaker<T> maker, ILinker linker, Http parent, T node);

   public void parseForPar(IMaker<T> maker, ILinker linker, Par parent, T node);

   public void parseForSocket(IMaker<T> maker, ILinker linker, Socket parent, T node);
}
