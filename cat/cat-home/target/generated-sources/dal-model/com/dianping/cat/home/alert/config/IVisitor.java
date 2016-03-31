package com.dianping.cat.home.alert.config;

import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public interface IVisitor {

   public void visitAlertConfig(AlertConfig alertConfig);

   public void visitReceiver(Receiver receiver);
}
