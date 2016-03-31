package com.dianping.cat.consumer.all.config.transform;

import com.dianping.cat.consumer.all.config.IVisitor;
import com.dianping.cat.consumer.all.config.entity.AllConfig;
import com.dianping.cat.consumer.all.config.entity.Name;
import com.dianping.cat.consumer.all.config.entity.Report;
import com.dianping.cat.consumer.all.config.entity.Type;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitAllConfig(AllConfig allConfig) {
      for (Report report : allConfig.getReports().values()) {
         visitReport(report);
      }
   }

   @Override
   public void visitName(Name name) {
   }

   @Override
   public void visitReport(Report report) {
      for (Type type : report.getTypes().values()) {
         visitType(type);
      }
   }

   @Override
   public void visitType(Type type) {
      for (Name name : type.getNameList()) {
         visitName(name);
      }
   }
}
