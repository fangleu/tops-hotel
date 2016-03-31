package com.dianping.cat.configuration.app.command.transform;

import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public interface IParser<T> {
   public CommandFormat parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForCommand(IMaker<T> maker, ILinker linker, Command parent, T node);

   public void parseForRule(IMaker<T> maker, ILinker linker, Rule parent, T node);
}
