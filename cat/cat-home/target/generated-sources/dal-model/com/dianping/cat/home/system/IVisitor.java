package com.dianping.cat.home.system;

import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public interface IVisitor {

   public void visitDay(Day day);

   public void visitDomain(Domain domain);

   public void visitEntity(Entity entity);

   public void visitMachine(Machine machine);

   public void visitRush(Rush rush);

   public void visitSystemReport(SystemReport systemReport);
}
