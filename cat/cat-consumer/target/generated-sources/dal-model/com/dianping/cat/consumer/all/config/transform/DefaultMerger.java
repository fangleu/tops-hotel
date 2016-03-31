package com.dianping.cat.consumer.all.config.transform;

import java.util.Stack;

import com.dianping.cat.consumer.all.config.IEntity;
import com.dianping.cat.consumer.all.config.IVisitor;
import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private AllConfig m_allConfig;

   public DefaultMerger() {
   }

   public DefaultMerger(AllConfig allConfig) {
      m_allConfig = allConfig;
      m_objs.push(allConfig);
   }

   public AllConfig getAllConfig() {
      return m_allConfig;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeAllConfig(AllConfig to, AllConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeName(Name to, Name from) {
      to.mergeAttributes(from);
   }

   protected void mergeReport(Report to, Report from) {
      to.mergeAttributes(from);
   }

   protected void mergeType(Type to, Type from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitAllConfig(AllConfig from) {
      AllConfig to = (AllConfig) m_objs.peek();

      mergeAllConfig(to, from);
      visitAllConfigChildren(to, from);
   }

   protected void visitAllConfigChildren(AllConfig to, AllConfig from) {
      for (Report source : from.getReports().values()) {
         Report target = to.findReport(source.getId());

         if (target == null) {
            target = new Report(source.getId());
            to.addReport(target);
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
   public void visitReport(Report from) {
      Report to = (Report) m_objs.peek();

      mergeReport(to, from);
      visitReportChildren(to, from);
   }

   protected void visitReportChildren(Report to, Report from) {
      for (Type source : from.getTypes().values()) {
         Type target = to.findType(source.getId());

         if (target == null) {
            target = new Type(source.getId());
            to.addType(target);
         }

         m_objs.push(target);
         source.accept(this);
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
      for (Name source : from.getNameList()) {
         Name target = null;

         if (target == null) {
            target = new Name();
            to.addName(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
