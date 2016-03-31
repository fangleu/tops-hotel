package com.dianping.cat.home.group;

import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public interface IVisitor {

   public void visitDomain(Domain domain);

   public void visitDomainGroup(DomainGroup domainGroup);

   public void visitGroup(Group group);
}
