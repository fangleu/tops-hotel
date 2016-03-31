package com.dianping.cat.home.jar.transform;

import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public interface IParser<T> {
   public JarReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDomain(IMaker<T> maker, ILinker linker, Domain parent, T node);

   public void parseForJar(IMaker<T> maker, ILinker linker, Jar parent, T node);

   public void parseForMachine(IMaker<T> maker, ILinker linker, Machine parent, T node);
}
