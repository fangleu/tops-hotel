package com.dianping.cat.configuration.server.black.transform;

import com.dianping.cat.configuration.server.black.entity.BlackList;
import com.dianping.cat.configuration.server.black.entity.Property;

public interface IParser<T> {
   public BlackList parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForProperty(IMaker<T> maker, ILinker linker, Property parent, T node);
}
