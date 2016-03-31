package com.dianping.cat.configuration.web.url.transform;

import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public interface ILinker {

   public boolean onCode(UrlPattern parent, Code code);

   public boolean onPatternItem(UrlPattern parent, PatternItem patternItem);
}
