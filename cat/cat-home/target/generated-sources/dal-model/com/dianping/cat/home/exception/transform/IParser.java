package com.dianping.cat.home.exception.transform;

import com.dianping.cat.home.exception.entity.ExceptionExclude;
import com.dianping.cat.home.exception.entity.ExceptionLimit;
import com.dianping.cat.home.exception.entity.ExceptionRuleConfig;

public interface IParser<T> {
   public ExceptionRuleConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForExceptionExclude(IMaker<T> maker, ILinker linker, ExceptionExclude parent, T node);

   public void parseForExceptionLimit(IMaker<T> maker, ILinker linker, ExceptionLimit parent, T node);
}
