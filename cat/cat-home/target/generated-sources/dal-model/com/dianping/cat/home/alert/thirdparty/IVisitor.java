package com.dianping.cat.home.alert.thirdparty;

import com.dianping.cat.home.alert.thirdparty.entity.Http;
import com.dianping.cat.home.alert.thirdparty.entity.Par;
import com.dianping.cat.home.alert.thirdparty.entity.Socket;
import com.dianping.cat.home.alert.thirdparty.entity.ThirdPartyConfig;

public interface IVisitor {

   public void visitHttp(Http http);

   public void visitPar(Par par);

   public void visitSocket(Socket socket);

   public void visitThirdPartyConfig(ThirdPartyConfig thirdPartyConfig);
}
