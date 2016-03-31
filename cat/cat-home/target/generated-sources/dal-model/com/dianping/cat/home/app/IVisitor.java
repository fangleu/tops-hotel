package com.dianping.cat.home.app;

import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public interface IVisitor {

   public void visitAppReport(AppReport appReport);

   public void visitCode(Code code);

   public void visitCommand(Command command);

   public void visitTransaction(Transaction transaction);
}
