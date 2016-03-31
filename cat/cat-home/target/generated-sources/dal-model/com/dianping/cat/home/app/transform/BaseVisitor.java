package com.dianping.cat.home.app.transform;

import com.dianping.cat.home.app.IVisitor;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitAppReport(AppReport appReport) {
      for (Command command : appReport.getCommands().values()) {
         visitCommand(command);
      }
   }

   @Override
   public void visitCode(Code code) {
   }

   @Override
   public void visitCommand(Command command) {
      if (command.getTransaction() != null) {
         visitTransaction(command.getTransaction());
      }

      for (Code code : command.getCodes().values()) {
         visitCode(code);
      }
   }

   @Override
   public void visitTransaction(Transaction transaction) {
   }
}
