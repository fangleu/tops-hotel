package com.dianping.cat.configuration.web.url.transform;

import com.dianping.cat.configuration.web.url.IVisitor;
import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public abstract class BaseVisitor implements IVisitor {
   @Override
   public void visitCode(Code code) {
   }

   @Override
   public void visitPatternItem(PatternItem patternItem) {
   }

   @Override
   public void visitUrlPattern(UrlPattern urlPattern) {
      for (PatternItem patternItem : urlPattern.getPatternItems().values()) {
         visitPatternItem(patternItem);
      }

      for (Code code : urlPattern.getCodes().values()) {
         visitCode(code);
      }
   }
}
