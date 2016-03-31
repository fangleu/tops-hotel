package com.dianping.cat.home.jar.transform;

import java.util.Stack;

import com.dianping.cat.home.jar.IEntity;
import com.dianping.cat.home.jar.IVisitor;
import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private JarReport m_jarReport;

   public DefaultMerger() {
   }

   public DefaultMerger(JarReport jarReport) {
      m_jarReport = jarReport;
      m_objs.push(jarReport);
   }

   public JarReport getJarReport() {
      return m_jarReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDomain(Domain to, Domain from) {
      to.mergeAttributes(from);
   }

   protected void mergeJar(Jar to, Jar from) {
      to.mergeAttributes(from);
   }

   protected void mergeJarReport(JarReport to, JarReport from) {
      to.mergeAttributes(from);
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitDomain(Domain from) {
      Domain to = (Domain) m_objs.peek();

      mergeDomain(to, from);
      visitDomainChildren(to, from);
   }

   protected void visitDomainChildren(Domain to, Domain from) {
      for (Machine source : from.getMachines().values()) {
         Machine target = to.findMachine(source.getId());

         if (target == null) {
            target = new Machine(source.getId());
            to.addMachine(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitJar(Jar from) {
      Jar to = (Jar) m_objs.peek();

      mergeJar(to, from);
      visitJarChildren(to, from);
   }

   protected void visitJarChildren(Jar to, Jar from) {
   }

   @Override
   public void visitJarReport(JarReport from) {
      JarReport to = (JarReport) m_objs.peek();

      mergeJarReport(to, from);
      visitJarReportChildren(to, from);
   }

   protected void visitJarReportChildren(JarReport to, JarReport from) {
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
   public void visitMachine(Machine from) {
      Machine to = (Machine) m_objs.peek();

      mergeMachine(to, from);
      visitMachineChildren(to, from);
   }

   protected void visitMachineChildren(Machine to, Machine from) {
      for (Jar source : from.getJars()) {
         Jar target = null;

         if (target == null) {
            target = new Jar();
            to.addJar(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
