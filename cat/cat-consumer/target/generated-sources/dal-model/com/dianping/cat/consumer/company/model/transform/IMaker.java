package com.dianping.cat.consumer.company.model.transform;

import com.dianping.cat.consumer.company.model.entity.Company;
import com.dianping.cat.consumer.company.model.entity.Domain;
import com.dianping.cat.consumer.company.model.entity.ProductLine;

public interface IMaker<T> {

   public Company buildCompany(T node);

   public Domain buildDomain(T node);

   public ProductLine buildProductLine(T node);
}
