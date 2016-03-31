package com.dianping.cat.home.group.transform;

import com.dianping.cat.home.group.IVisitor;
import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitDomain(Domain domain) {
      for (Group group : domain.getGroups().values()) {
         visitGroup(group);
      }
   }

   @Override
   public void visitDomainGroup(DomainGroup domainGroup) {
      for (Domain domain : domainGroup.getDomains().values()) {
         visitDomain(domain);
      }
   }

   @Override
   public void visitGroup(Group group) {
   }
}
