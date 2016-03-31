package com.dianping.cat.home.system.transform;

import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public interface IMaker<T> {

   public Day buildDay(T node);

   public Domain buildDomain(T node);

   public Entity buildEntity(T node);

   public Machine buildMachine(T node);

   public Rush buildRush(T node);

   public SystemReport buildSystemReport(T node);
}
