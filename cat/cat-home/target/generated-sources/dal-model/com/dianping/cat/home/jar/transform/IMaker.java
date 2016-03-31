package com.dianping.cat.home.jar.transform;

import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public interface IMaker<T> {

   public Domain buildDomain(T node);

   public Jar buildJar(T node);

   public JarReport buildJarReport(T node);

   public Machine buildMachine(T node);
}
