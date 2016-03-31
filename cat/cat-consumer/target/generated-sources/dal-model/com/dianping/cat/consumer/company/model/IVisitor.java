package com.dianping.cat.consumer.company.model;

import com.dianping.cat.consumer.company.model.entity.Company;
import com.dianping.cat.consumer.company.model.entity.Domain;
import com.dianping.cat.consumer.company.model.entity.ProductLine;

public interface IVisitor {

   public void visitCompany(Company company);

   public void visitDomain(Domain domain);

   public void visitProductLine(ProductLine productLine);
}
