package com.dianping.cat.consumer.all.config;

import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public interface IVisitor {

   public void visitAllConfig(AllConfig allConfig);

   public void visitName(Name name);

   public void visitReport(Report report);

   public void visitType(Type type);
}
