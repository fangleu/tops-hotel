package com.dianping.cat.configuration.app.command.transform;

import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public interface ILinker {

   public boolean onCommand(Rule parent, Command command);

   public boolean onRule(CommandFormat parent, Rule rule);
}
