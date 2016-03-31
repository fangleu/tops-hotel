package com.dianping.cat.home.app.transform;

import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public interface ILinker {

   public boolean onCode(Command parent, Code code);

   public boolean onCommand(AppReport parent, Command command);

   public boolean onTransaction(Command parent, Transaction transaction);
}
