package com.dianping.cat.configuration.web.url.transform;

import java.util.Stack;

import com.dianping.cat.configuration.web.url.IEntity;
import com.dianping.cat.configuration.web.url.IVisitor;
import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private UrlPattern m_urlPattern;

   public DefaultMerger() {
   }

   public DefaultMerger(UrlPattern urlPattern) {
      m_urlPattern = urlPattern;
      m_objs.push(urlPattern);
   }

   public UrlPattern getUrlPattern() {
      return m_urlPattern;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeCode(Code to, Code from) {
      to.mergeAttributes(from);
   }

   protected void mergePatternItem(PatternItem to, PatternItem from) {
      to.mergeAttributes(from);
   }

   protected void mergeUrlPattern(UrlPattern to, UrlPattern from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitCode(Code from) {
      Code to = (Code) m_objs.peek();

      mergeCode(to, from);
      visitCodeChildren(to, from);
   }

   protected void visitCodeChildren(Code to, Code from) {
   }

   @Override
   public void visitPatternItem(PatternItem from) {
      PatternItem to = (PatternItem) m_objs.peek();

      mergePatternItem(to, from);
      visitPatternItemChildren(to, from);
   }

   protected void visitPatternItemChildren(PatternItem to, PatternItem from) {
   }

   @Override
   public void visitUrlPattern(UrlPattern from) {
      UrlPattern to = (UrlPattern) m_objs.peek();

      mergeUrlPattern(to, from);
      visitUrlPatternChildren(to, from);
   }

   protected void visitUrlPatternChildren(UrlPattern to, UrlPattern from) {
      for (PatternItem source : from.getPatternItems().values()) {
         PatternItem target = to.findPatternItem(source.getName());

         if (target == null) {
            target = new PatternItem(source.getName());
            to.addPatternItem(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }

      for (Code source : from.getCodes().values()) {
         Code target = to.findCode(source.getId());

         if (target == null) {
            target = new Code(source.getId());
            to.addCode(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
