package com.dianping.cat.home.system.transform;

import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public interface ILinker {

   public boolean onDay(Entity parent, Day day);

   public boolean onDomain(SystemReport parent, Domain domain);

   public boolean onEntity(Domain parent, Entity entity);

   public boolean onMachine(Entity parent, Machine machine);

   public boolean onRush(Entity parent, Rush rush);
}
