package com.dianping.cat.home.dependency.format.transform;

import com.dianping.cat.home.dependency.format.entity.ProductLine;
import com.dianping.cat.home.dependency.format.entity.TopoGraphFormatConfig;

public interface IParser<T> {
   public TopoGraphFormatConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForProductLine(IMaker<T> maker, ILinker linker, ProductLine parent, T node);
}
