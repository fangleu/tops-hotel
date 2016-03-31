package com.dianping.cat.configuration.app;

import com.dianping.cat.configuration.app.entity.AppConfig;
import com.dianping.cat.configuration.app.entity.Code;
import com.dianping.cat.configuration.app.entity.Command;
import com.dianping.cat.configuration.app.entity.ConfigItem;
import com.dianping.cat.configuration.app.entity.Item;

public interface IVisitor {

   public void visitAppConfig(AppConfig appConfig);

   public void visitCode(Code code);

   public void visitCommand(Command command);

   public void visitConfigItem(ConfigItem configItem);

   public void visitItem(Item item);
}
