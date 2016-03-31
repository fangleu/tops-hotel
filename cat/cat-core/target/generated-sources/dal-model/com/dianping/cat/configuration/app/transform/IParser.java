package com.dianping.cat.configuration.app.transform;

import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

public interface IParser<T> {
   public AppConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForCode(IMaker<T> maker, ILinker linker, Code parent, T node);

   public void parseForCommand(IMaker<T> maker, ILinker linker, Command parent, T node);

   public void parseForConfigItem(IMaker<T> maker, ILinker linker, ConfigItem parent, T node);

   public void parseForItem(IMaker<T> maker, ILinker linker, Item parent, T node);
}
