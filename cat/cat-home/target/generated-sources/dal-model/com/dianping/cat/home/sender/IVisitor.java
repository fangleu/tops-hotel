package com.dianping.cat.home.sender;

import com.dianping.cat.home.sender.entity.Par;
import com.dianping.cat.home.sender.entity.Sender;
import com.dianping.cat.home.sender.entity.SenderConfig;

public interface IVisitor {

   public void visitPar(Par par);

   public void visitSender(Sender sender);

   public void visitSenderConfig(SenderConfig senderConfig);
}
