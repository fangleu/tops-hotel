package com.dianping.cat.consumer.company.model.transform;

import com.dianping.cat.consumer.company.model.entity.Company;
import com.dianping.cat.consumer.company.model.entity.Domain;
import com.dianping.cat.consumer.company.model.entity.ProductLine;

public interface IParser<T> {
   public Company parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForDomain(IMaker<T> maker, ILinker linker, Domain parent, T node);

   public void parseForProductLine(IMaker<T> maker, ILinker linker, ProductLine parent, T node);
}
