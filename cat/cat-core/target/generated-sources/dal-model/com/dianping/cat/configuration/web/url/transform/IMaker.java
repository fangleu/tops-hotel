package com.dianping.cat.configuration.web.url.transform;

import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public interface IMaker<T> {

   public Code buildCode(T node);

   public PatternItem buildPatternItem(T node);

   public UrlPattern buildUrlPattern(T node);
}
