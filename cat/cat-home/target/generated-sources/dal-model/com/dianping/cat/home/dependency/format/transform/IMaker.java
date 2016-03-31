package com.dianping.cat.home.dependency.format.transform;

import com.dianping.cat.home.dependency.format.entity.ProductLine;
import com.dianping.cat.home.dependency.format.entity.TopoGraphFormatConfig;

public interface IMaker<T> {

   public ProductLine buildProductLine(T node);

   public TopoGraphFormatConfig buildTopoGraphFormatConfig(T node);
}
