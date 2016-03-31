package com.dianping.cat.home.group.transform;

import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public interface IMaker<T> {

   public Domain buildDomain(T node);

   public DomainGroup buildDomainGroup(T node);

   public Group buildGroup(T node);

   public String buildIp(T node);
}
