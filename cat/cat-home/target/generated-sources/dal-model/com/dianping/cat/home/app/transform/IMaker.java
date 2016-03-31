package com.dianping.cat.home.app.transform;

import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public interface IMaker<T> {

   public AppReport buildAppReport(T node);

   public Code buildCode(T node);

   public Command buildCommand(T node);

   public Transaction buildTransaction(T node);
}
