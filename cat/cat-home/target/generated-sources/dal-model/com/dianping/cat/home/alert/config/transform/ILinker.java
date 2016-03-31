package com.dianping.cat.home.alert.config.transform;

import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public interface ILinker {

   public boolean onReceiver(AlertConfig parent, Receiver receiver);
}
