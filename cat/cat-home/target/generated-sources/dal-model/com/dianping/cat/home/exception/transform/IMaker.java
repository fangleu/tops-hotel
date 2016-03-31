package com.dianping.cat.home.exception.transform;

import com.dianping.cat.home.exception.entity.ExceptionExclude;
import com.dianping.cat.home.exception.entity.ExceptionLimit;
import com.dianping.cat.home.exception.entity.ExceptionRuleConfig;

public interface IMaker<T> {

   public ExceptionExclude buildExceptionExclude(T node);

   public ExceptionLimit buildExceptionLimit(T node);

   public ExceptionRuleConfig buildExceptionRuleConfig(T node);
}
