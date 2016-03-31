package com.dianping.cat.home.sender.transform;

import com.dianping.cat.home.sender.entity.Par;
import com.dianping.cat.home.sender.entity.Sender;
import com.dianping.cat.home.sender.entity.SenderConfig;

public interface ILinker {

   public boolean onPar(Sender parent, Par par);

   public boolean onSender(SenderConfig parent, Sender sender);
}
