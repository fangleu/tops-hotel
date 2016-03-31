package com.dianping.cat.home.jar.transform;

import com.dianping.cat.home.jar.IVisitor;
import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDomain(Domain domain) {
      for (Machine machine : domain.getMachines().values()) {
         visitMachine(machine);
      }
   }

   @Override
   public void visitJar(Jar jar) {
   }

   @Override
   public void visitJarReport(JarReport jarReport) {
      for (Domain domain : jarReport.getDomains().values()) {
         visitDomain(domain);
      }
   }

   @Override
   public void visitMachine(Machine machine) {
      for (Jar jar : machine.getJars()) {
         visitJar(jar);
      }
   }
}
