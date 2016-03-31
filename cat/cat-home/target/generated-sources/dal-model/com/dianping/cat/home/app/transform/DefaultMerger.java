package com.dianping.cat.home.app.transform;

import java.util.Stack;

import com.dianping.cat.home.app.IEntity;
import com.dianping.cat.home.app.IVisitor;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private AppReport m_appReport;

   public DefaultMerger() {
   }

   public DefaultMerger(AppReport appReport) {
      m_appReport = appReport;
      m_objs.push(appReport);
   }

   public AppReport getAppReport() {
      return m_appReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeAppReport(AppReport to, AppReport from) {
      to.mergeAttributes(from);
   }

   protected void mergeCode(Code to, Code from) {
      to.mergeAttributes(from);
   }

   protected void mergeCommand(Command to, Command from) {
      to.mergeAttributes(from);
   }

   protected void mergeTransaction(Transaction to, Transaction from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitAppReport(AppReport from) {
      AppReport to = (AppReport) m_objs.peek();

      mergeAppReport(to, from);
      visitAppReportChildren(to, from);
   }

   protected void visitAppReportChildren(AppReport to, AppReport from) {
      for (Command source : from.getCommands().values()) {
         Command target = to.findCommand(source.getId());

         if (target == null) {
            target = new Command(source.getId());
            to.addCommand(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitCode(Code from) {
      Code to = (Code) m_objs.peek();

      mergeCode(to, from);
      visitCodeChildren(to, from);
   }

   protected void visitCodeChildren(Code to, Code from) {
   }

   @Override
   public void visitCommand(Command from) {
      Command to = (Command) m_objs.peek();

      mergeCommand(to, from);
      visitCommandChildren(to, from);
   }

   protected void visitCommandChildren(Command to, Command from) {
      if (from.getTransaction() != null) {
         Transaction target = to.getTransaction();

         if (target == null) {
            Transaction source = from.getTransaction();

            target = new Transaction(source.getUrl());
            to.setTransaction(target);
         }

         m_objs.push(target);
         from.getTransaction().accept(this);
         m_objs.pop();
      }

      for (Code source : from.getCodes().values()) {
         Code target = to.findCode(source.getId());

         if (target == null) {
            target = new Code(source.getId());
            to.addCode(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitTransaction(Transaction from) {
      Transaction to = (Transaction) m_objs.peek();

      mergeTransaction(to, from);
      visitTransactionChildren(to, from);
   }

   protected void visitTransactionChildren(Transaction to, Transaction from) {
   }
}
