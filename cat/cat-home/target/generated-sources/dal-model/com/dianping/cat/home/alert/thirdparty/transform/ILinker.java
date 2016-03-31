package com.dianping.cat.home.alert.thirdparty.transform;

import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public interface ILinker {

   public boolean onHttp(ThirdPartyConfig parent, Http http);

   public boolean onPar(Http parent, Par par);

   public boolean onSocket(ThirdPartyConfig parent, Socket socket);
}
