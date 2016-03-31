package com.dianping.cat.home.group.transform;

import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public interface ILinker {

   public boolean onDomain(DomainGroup parent, Domain domain);

   public boolean onGroup(Domain parent, Group group);
}
