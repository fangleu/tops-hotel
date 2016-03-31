package com.dianping.cat.configuration.web.url;

import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public interface IVisitor {

   public void visitCode(Code code);

   public void visitPatternItem(PatternItem patternItem);

   public void visitUrlPattern(UrlPattern urlPattern);
}
