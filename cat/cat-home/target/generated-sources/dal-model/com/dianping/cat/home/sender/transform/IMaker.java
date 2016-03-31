package com.dianping.cat.home.sender.transform;

import com.dianping.cat.home.sender.entity.Par;
import com.dianping.cat.home.sender.entity.Sender;
import com.dianping.cat.home.sender.entity.SenderConfig;

public interface IMaker<T> {

   public Par buildPar(T node);

   public Sender buildSender(T node);

   public SenderConfig buildSenderConfig(T node);
}
