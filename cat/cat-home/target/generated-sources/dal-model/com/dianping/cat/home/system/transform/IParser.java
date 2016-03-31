package com.dianping.cat.home.system.transform;

import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public interface IParser<T> {
   public SystemReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDay(IMaker<T> maker, ILinker linker, Day parent, T node);

   public void parseForDomain(IMaker<T> maker, ILinker linker, Domain parent, T node);

   public void parseForEntity(IMaker<T> maker, ILinker linker, Entity parent, T node);

   public void parseForMachine(IMaker<T> maker, ILinker linker, Machine parent, T node);

   public void parseForRush(IMaker<T> maker, ILinker linker, Rush parent, T node);
}
