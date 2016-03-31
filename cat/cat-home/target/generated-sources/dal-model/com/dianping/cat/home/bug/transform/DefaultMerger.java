package com.dianping.cat.home.bug.transform;

import java.util.Stack;

import com.dianping.cat.home.bug.IEntity;
import com.dianping.cat.home.bug.IVisitor;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private BugReport m_bugReport;

   public DefaultMerger() {
   }

   public DefaultMerger(BugReport bugReport) {
      m_bugReport = bugReport;
      m_objs.push(bugReport);
   }

   public BugReport getBugReport() {
      return m_bugReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeBugReport(BugReport to, BugReport from) {
      to.mergeAttributes(from);
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
      to.setProblemUrl(from.getProblemUrl());
      to.setExcpetion(from.getExcpetion());
   }

   protected void mergeExceptionItem(ExceptionItem to, ExceptionItem from) {
      to.mergeAttributes(from);
      to.getMessages().addAll(from.getMessages());
   }

   @Override
   public void visitBugReport(BugReport from) {
      BugReport to = (BugReport) m_objs.peek();

      mergeBugReport(to, from);
      visitBugReportChildren(to, from);
   }

   protected void visitBugReportChildren(BugReport to, BugReport from) {
      for (Domain source : from.getDomains().values()) {
         Domain target = to.findDomain(source.getId());

         if (target == null) {
            target = new Domain(source.getId());
            to.addDomain(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
      for (ExceptionItem source : from.getExceptionItems().values()) {
         ExceptionItem target = to.findExceptionItem(source.getId());

         if (target == null) {
            target = new ExceptionItem(source.getId());
            to.addExceptionItem(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitExceptionItem(ExceptionItem from) {
      ExceptionItem to = (ExceptionItem) m_objs.peek();

      mergeExceptionItem(to, from);
      visitExceptionItemChildren(to, from);
   }

   protected void visitExceptionItemChildren(ExceptionItem to, ExceptionItem from) {
   }
}
