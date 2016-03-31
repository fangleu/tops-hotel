package com.dianping.cat.home.alert.config.transform;

import com.dianping.cat.home.alert.config.entity.AlertConfig;
import com.dianping.cat.home.alert.config.entity.Receiver;

public interface IMaker<T> {

   public AlertConfig buildAlertConfig(T node);

   public String buildEmail(T node);

   public String buildPhone(T node);

   public Receiver buildReceiver(T node);

   public String buildWeixin(T node);
}
