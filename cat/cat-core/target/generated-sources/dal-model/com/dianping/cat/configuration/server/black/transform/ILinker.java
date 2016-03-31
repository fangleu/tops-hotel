package com.dianping.cat.configuration.server.black.transform;

import com.dianping.cat.configuration.server.black.entity.BlackList;
import com.dianping.cat.configuration.server.black.entity.Property;

public interface ILinker {

   public boolean onProperty(BlackList parent, Property property);
}
