package com.dianping.cat.home.alert.thirdparty.transform;

import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public interface IMaker<T> {

   public Http buildHttp(T node);

   public Par buildPar(T node);

   public Socket buildSocket(T node);

   public ThirdPartyConfig buildThirdPartyConfig(T node);
}
