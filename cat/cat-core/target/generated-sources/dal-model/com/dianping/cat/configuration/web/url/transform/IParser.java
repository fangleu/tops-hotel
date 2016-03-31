package com.dianping.cat.configuration.web.url.transform;

import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public interface IParser<T> {
   public UrlPattern parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForCode(IMaker<T> maker, ILinker linker, Code parent, T node);

   public void parseForPatternItem(IMaker<T> maker, ILinker linker, PatternItem parent, T node);
}
