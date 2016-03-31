package com.dianping.cat.consumer.company.model.transform;

import com.dianping.cat.consumer.company.model.entity.Company;
import com.dianping.cat.consumer.company.model.entity.Domain;
import com.dianping.cat.consumer.company.model.entity.ProductLine;

public interface ILinker {

   public boolean onDomain(ProductLine parent, Domain domain);

   public boolean onProductLine(Company parent, ProductLine productLine);
}
