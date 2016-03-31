package com.dianping.cat.consumer.cross.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.cross.model.IEntity;
import com.dianping.cat.consumer.cross.model.IVisitor;
import com.dianping.cat.consumer.cross.model.entity.CrossReport;
import com.dianping.cat.consumer.cross.model.entity.Local;
import com.dianping.cat.consumer.cross.model.entity.Name;
import com.dianping.cat.consumer.cross.model.entity.Remote;
import com.dianping.cat.consumer.cross.model.entity.Type;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private CrossReport m_crossReport;

   public DefaultMerger() {
   }

   public DefaultMerger(CrossReport crossReport) {
      m_crossReport = crossReport;
      m_objs.push(crossReport);
   }

   public CrossReport getCrossReport() {
      return m_crossReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeCrossReport(CrossReport to, CrossReport from) {
      to.mergeAttributes(from);
      to.getDomainNames().addAll(from.getDomainNames());
      to.getIps().addAll(from.getIps());
   }

   protected void mergeLocal(Local to, Local from) {
      to.mergeAttributes(from);
   }

   protected void mergeName(Name to, Name from) {
      to.mergeAttributes(from);
   }

   protected void mergeRemote(Remote to, Remote from) {
      to.mergeAttributes(from);
   }

   protected void mergeType(Type to, Type from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitCrossReport(CrossReport from) {
      CrossReport to = (CrossReport) m_objs.peek();

      mergeCrossReport(to, from);
      visitCrossReportChildren(to, from);
   }

   protected void visitCrossReportChildren(CrossReport to, CrossReport from) {
      for (Local source : from.getLocals().values()) {
         Local target = to.findLocal(source.getId());

         if (target == null) {
            target = new Local(source.getId());
            to.addLocal(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitLocal(Local from) {
      Local to = (Local) m_objs.peek();

      mergeLocal(to, from);
      visitLocalChildren(to, from);
   }

   protected void visitLocalChildren(Local to, Local from) {
      for (Remote source : from.getRemotes().values()) {
         Remote target = to.findRemote(source.getId());

         if (target == null) {
            target = new Remote(source.getId());
            to.addRemote(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitName(Name from) {
      Name to = (Name) m_objs.peek();

      mergeName(to, from);
      visitNameChildren(to, from);
   }

   protected void visitNameChildren(Name to, Name from) {
   }

   @Override
   public void visitRemote(Remote from) {
      Remote to = (Remote) m_objs.peek();

      mergeRemote(to, from);
      visitRemoteChildren(to, from);
   }

   protected void visitRemoteChildren(Remote to, Remote from) {
      if (from.getType() != null) {
         Type target = to.getType();

         if (target == null) {
            target = new Type();
            to.setType(target);
         }

         m_objs.push(target);
         from.getType().accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitType(Type from) {
      Type to = (Type) m_objs.peek();

      mergeType(to, from);
      visitTypeChildren(to, from);
   }

   protected void visitTypeChildren(Type to, Type from) {
      for (Name source : from.getNames().values()) {
         Name target = to.findName(source.getId());

         if (target == null) {
            target = new Name(source.getId());
            to.addName(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
