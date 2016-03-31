package com.dianping.cat.home.jar;

import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public interface IVisitor {

   public void visitDomain(Domain domain);

   public void visitJar(Jar jar);

   public void visitJarReport(JarReport jarReport);

   public void visitMachine(Machine machine);
}
