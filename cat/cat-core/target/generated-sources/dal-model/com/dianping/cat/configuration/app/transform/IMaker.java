package com.dianping.cat.configuration.app.transform;

import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

public interface IMaker<T> {

   public AppConfig buildAppConfig(T node);

   public Code buildCode(T node);

   public Command buildCommand(T node);

   public ConfigItem buildConfigItem(T node);

   public Item buildItem(T node);
}
