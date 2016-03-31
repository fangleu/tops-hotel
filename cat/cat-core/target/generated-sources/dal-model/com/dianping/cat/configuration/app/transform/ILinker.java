package com.dianping.cat.configuration.app.transform;

import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

public interface ILinker {

   public boolean onCode(AppConfig parent, Code code);

   public boolean onCode(Command parent, Code code);

   public boolean onCommand(AppConfig parent, Command command);

   public boolean onConfigItem(AppConfig parent, ConfigItem configItem);

   public boolean onItem(ConfigItem parent, Item item);
}
