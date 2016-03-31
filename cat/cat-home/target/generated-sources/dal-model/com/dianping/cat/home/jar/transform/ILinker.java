package com.dianping.cat.home.jar.transform;

import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public interface ILinker {

   public boolean onDomain(JarReport parent, Domain domain);

   public boolean onJar(Machine parent, Jar jar);

   public boolean onMachine(Domain parent, Machine machine);
}
