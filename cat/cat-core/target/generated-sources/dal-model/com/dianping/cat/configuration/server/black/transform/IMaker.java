package com.dianping.cat.configuration.server.black.transform;

import com.dianping.cat.configuration.server.black.entity.BlackList;
import com.dianping.cat.configuration.server.black.entity.Property;

public interface IMaker<T> {

   public BlackList buildBlackList(T node);

   public String buildDomain(T node);

   public String buildIp(T node);

   public Property buildProperty(T node);
}
