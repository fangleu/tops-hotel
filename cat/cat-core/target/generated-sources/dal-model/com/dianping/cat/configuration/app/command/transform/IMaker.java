package com.dianping.cat.configuration.app.command.transform;

import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public interface IMaker<T> {

   public Command buildCommand(T node);

   public CommandFormat buildCommandFormat(T node);

   public Rule buildRule(T node);
}
