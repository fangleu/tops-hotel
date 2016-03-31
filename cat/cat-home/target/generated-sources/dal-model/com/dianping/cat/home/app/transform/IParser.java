package com.dianping.cat.home.app.transform;

import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public interface IParser<T> {
   public AppReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForCode(IMaker<T> maker, ILinker linker, Code parent, T node);

   public void parseForCommand(IMaker<T> maker, ILinker linker, Command parent, T node);

   public void parseForTransaction(IMaker<T> maker, ILinker linker, Transaction parent, T node);
}
