package com.dianping.cat.home.exception;

import com.dianping.cat.home.exception.entity.ExceptionExclude;
import com.dianping.cat.home.exception.entity.ExceptionLimit;
import com.dianping.cat.home.exception.entity.ExceptionRuleConfig;

public interface IVisitor {

   public void visitExceptionExclude(ExceptionExclude exceptionExclude);

   public void visitExceptionLimit(ExceptionLimit exceptionLimit);

   public void visitExceptionRuleConfig(ExceptionRuleConfig exceptionRuleConfig);
}
