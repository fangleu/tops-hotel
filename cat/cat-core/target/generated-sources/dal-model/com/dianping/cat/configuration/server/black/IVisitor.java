package com.dianping.cat.configuration.server.black;

import com.dianping.cat.configuration.server.black.entity.BlackList;
import com.dianping.cat.configuration.server.black.entity.Property;

public interface IVisitor {

   public void visitBlackList(BlackList blackList);

   public void visitProperty(Property property);
}
