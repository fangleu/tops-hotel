package com.dianping.cat.configuration.app.command;

import com.dianping.cat.configuration.app.command.entity.Command;
import com.dianping.cat.configuration.app.command.entity.CommandFormat;
import com.dianping.cat.configuration.app.command.entity.Rule;

public interface IVisitor {

   public void visitCommand(Command command);

   public void visitCommandFormat(CommandFormat commandFormat);

   public void visitRule(Rule rule);
}
